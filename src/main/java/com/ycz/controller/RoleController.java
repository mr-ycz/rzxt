package com.ycz.controller;

import com.ycz.pojo.Role;
import com.ycz.service.RoleService;
import com.ycz.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin("http://localhost:8080")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/roles")
    @ResponseBody
    public R roles(){
        List<Role> roleList=roleService.queryAllRole();

        return R.ok().put("roles", roleList);
    }

}
