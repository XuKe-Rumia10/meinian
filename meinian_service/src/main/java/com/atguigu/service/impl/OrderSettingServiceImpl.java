package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.OrderSettingDao;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/26  11:33
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    OrderSettingDao orderSettingDao;

    @Override
    public void addBatch(List<OrderSetting> listData) {
        //如果日期对应的设置存在，否则就添加
        for (OrderSetting listDatum : listData) {
            int count = orderSettingDao.getOrderSettingByOrderDate(listDatum.getOrderDate());
            if(count>0){
                orderSettingDao.edit(listDatum);
            }else{
                orderSettingDao.add(listDatum);
            }
        }
    }

    @Override
    public void edit(OrderSetting orderSetting) {

    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {


        String startDate = date + "-1";
        String endDate = date + "-31";
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(startDate,endDate);

        List<Map> listData = new ArrayList<Map>();
        for (OrderSetting orderSetting : list) {
            Map map = new HashMap();
            map.put("date",orderSetting.getOrderDate().getDate());
            map.put("number",orderSetting.getNumber());
            map.put("reservations",orderSetting.getReservations());
            listData.add(map);

        }
        return listData;
    }

    @Override
    public void editNumberByOrderDate(Map map) throws ParseException {
        String date = (String) map.get("orderDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(date);

        OrderSetting orderSetting = new OrderSetting();
        orderSetting.setNumber(Integer.parseInt((String)map.get("value")));
        orderSetting.setOrderDate(date1);
        int count = orderSettingDao.getOrderSettingByOrderDate(date1);
        if(count>0){
            orderSettingDao.edit(orderSetting);
        }else{
            orderSettingDao.add(orderSetting);
        }
    }
}
