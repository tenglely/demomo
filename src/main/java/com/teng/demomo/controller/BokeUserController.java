package com.teng.demomo.controller;

import com.teng.demomo.entity.BokeUser;
import com.teng.demomo.entity.Msg;
import com.teng.demomo.service.BokeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BokeUserController {

    @Autowired
    private BokeUserService bokeUserService;

    /**
     * 管理员添加管理员
     * @param username
     * @param password
     * @param realName
     * @param sex
     * @param introduce
     * @return
     */
    @PostMapping("/admin/adminegister")
    @ResponseBody
    public Msg adminegister(String username,String password,String realName,String sex,String introduce){
       //添加用户
        BokeUser user=new BokeUser();
        user.setUsername(username);
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(10);
        String encodePassword=encoder.encode(password);
        user.setPassword(encodePassword);
        user.setRealName(realName);
        user.setSex(sex);
        user.setIntroduce(introduce);
        user.setUserPhoto("空");
        user.setEnabled(true);
        user.setLocked(false);
        int i=bokeUserService.register(user);
        System.out.println(i);
        //添加角色
        BokeUser user1=bokeUserService.selectByUserName(username);
        System.out.println(user1);
        bokeUserService.addUserRole(user1.getUid(),1);
        bokeUserService.addUserRole(user1.getUid(),2);
        return Msg.success();
    }

    /**
     * 用户获取用户信息
     * @return
     */
    @RequestMapping("/user/getCurrentUser")
    @ResponseBody
    public BokeUser getCurrentUser(){
        return (BokeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
