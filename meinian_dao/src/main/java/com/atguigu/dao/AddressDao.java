package com.atguigu.dao;

import com.atguigu.pojo.Address;
import com.atguigu.pojo.Member;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/2/1  10:59
 */
public interface AddressDao {

    List<Address> findAllMaps();

    void addAddress(Address address);
    Page<Address> selectByCondition(String queryString);
    void deleteById(Integer id);
}
