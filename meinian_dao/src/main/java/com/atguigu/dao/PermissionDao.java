package com.atguigu.dao;

import com.atguigu.pojo.Permission;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/29  16:46
 */
public interface PermissionDao {

    Permission findPermissionsByRoleId(int roleId);
}
