package com.atguigu.service;

import com.atguigu.entity.Result;
import com.atguigu.pojo.Order;

import java.util.Map;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/27  15:51
 */
public interface OrderService {
    Result saveOrder(Map map) throws Exception;

    Map findById(Integer id);
}
