package com.atguigu.service;

import com.atguigu.pojo.User;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/29  16:45
 */
public interface UserService {
    User findUserByUsername(String username);
}