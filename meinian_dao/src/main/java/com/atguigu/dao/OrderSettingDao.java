package com.atguigu.dao;

import com.atguigu.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/26  11:33
 */
public interface OrderSettingDao {
    void add(OrderSetting listData);

    int getOrderSettingByOrderDate(Date orderDate);

    void edit(OrderSetting listDatum);

    List<OrderSetting> getOrderSettingByMonth(String startDate, String endDate);//★

    void editNumberByOrderDate(Map map);

    OrderSetting findOrderSettingByOrderDate(Date date);

    void editReservationsByOrderDate(OrderSetting orderSetting);
}
