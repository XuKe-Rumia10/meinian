package com.atguigu.service;

import com.atguigu.pojo.OrderSetting;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/26  11:32
 */
public interface OrderSettingService {
    void addBatch(List<OrderSetting> listData);

    void edit(OrderSetting orderSetting);

    List<Map> getOrderSettingByMonth(String date);

    void editNumberByOrderDate(Map map) throws ParseException;
}
