package com.atguigu.dao;

import com.atguigu.pojo.Address;

import java.util.List;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/2/1  10:59
 */
public interface AddressDao {

    List<Address> findAllMaps();
}
