package com.ycz.controller;

import com.ycz.pojo.User;
import com.ycz.pojo.UserVo;
import com.ycz.service.RoleService;
import com.ycz.service.UserService;
import com.ycz.utils.Captcha2;
import com.ycz.utils.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
@CrossOrigin("http://localhost:8080")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/login")
    @ResponseBody
    public R loginLogic(@RequestBody UserVo userVo, HttpServletRequest request){

        String captcha = (String) request.getSession().getAttribute("captcha");
        if (!captcha.equalsIgnoreCase(userVo.getCaptcha())){
            return R.error("验证码错误");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(userVo.getUsername(), userVo.getPassword());
        Subject subject = SecurityUtils.getSubject();
        if ("true".equals(userVo.getRememberMe())){
            token.setRememberMe(true);
        }
        subject.login(token);

        return R.ok();
    }

    @RequestMapping("/getCode")
    @ResponseBody
    public String getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Captcha2.generateCaptcha(request, response);
        return "1";
    }

    @RequestMapping("/info")
    @ResponseBody
    public R info(){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        User user = userService.queryUserByName(username);

        return R.ok().put("user", user);
    }

    @RequestMapping("/info/{userId}")
    @ResponseBody
    public R infos(@PathVariable("userId") Integer userId){

        User user=userService.queryUserById(userId);
        List<Integer> roles=roleService.queryRoleByUserId(userId);

        user.setRoles(roles);

        return R.ok().put("user", user);
    }

    @RequestMapping("/update")
    @ResponseBody
    public R update(@RequestBody User user){

        userService.updateUser(user);
        roleService.updateRole(user.getRoles(),user.getUserId());

        return R.ok();
    }

    @RequestMapping("/logout")
    @ResponseBody
    public R logout(){
        SecurityUtils.getSubject().logout();
        return R.ok();
    }

    @RequestMapping("/list")
    @ResponseBody
    public R list(String order, Integer limit, Integer offset){

        List<User> userList = userService.queryAllUser(order,limit,offset);
        Integer total=userService.queryAllCount();

        return R.ok().put("total", total).put("rows", userList);
    }

    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody User user){

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User nowUser = userService.queryUserByName(username);

        //将新增用户的create_user_id设置为当前操作的用户id
        user.setCreateUserId(nowUser.getUserId());

        //将新增用户的添加时间设置为当前时间
        user.setCreateTime(new Date());

        userService.addUser(user);
        //获得新增用户的id
        Integer userId = user.getUserId();

        //为当前用户Id添加角色
        roleService.addRole(user.getRoles(),user.getUserId());

        return R.ok();
    }

    @RequestMapping("/del/{userId}")
    @ResponseBody
    public R del(@PathVariable("userId") Integer userId){

        roleService.deleteRoleByUserId(userId);
        userService.deleteUserByUserId(userId);

        return R.ok();
    }


}
