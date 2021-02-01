package com.atguigu.dao;

import com.atguigu.pojo.Role;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/29  16:45
 */
public interface RoleDao {

    Role findRolesByUserId(Integer userId);
}
