package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.List;
import java.util.Map;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/27  15:52
 */
public interface OrderDao {
    List<Order> findCountOrderByCondition(Order orderParam);

    void add(Order order);

    Order findById(Integer id);
    int getTodayOrderNumber(String date);
    int getTodayVisitsNumber(String date);
    int getThisWeekAndMonthOrderNumber(Map<String, Object> map);
    int getThisWeekAndMonthVisitsNumber(Map<String, Object> map);
    List<Map<String,Object>> findHotSetmeal();
}
