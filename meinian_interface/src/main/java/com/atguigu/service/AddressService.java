package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.Address;

import java.util.List;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/2/1  10:57
 */
public interface AddressService {
    List<Address> findAllMaps();

    void addAddress(Address address);
    PageResult findPage(QueryPageBean queryPageBean);
    void deleteById(Integer id);
}
