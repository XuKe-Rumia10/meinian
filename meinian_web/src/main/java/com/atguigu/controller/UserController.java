package com.atguigu.controller;

import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/29  18:01
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @RequestMapping(value = "/getUsername")
    public Result getUsername(){
        try {
            //框架认证成功，会将用户信息放在session中，并同时将用户信息绑定到当前线程上。
            //可通过securityContext来获取认证信息
            //Authentication 认证对象
            //Principar 认证主体，其实就是自己的User对象

            // 从SpringSecurity中获取认证用户的信息
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);
        }
    }
}
