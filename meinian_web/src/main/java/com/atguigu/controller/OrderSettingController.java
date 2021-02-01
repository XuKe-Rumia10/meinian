package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import com.atguigu.utils.POIUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/26  11:31
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    OrderSettingService orderSettingService;

    //接前台的param={value:value,orderDate:date}
    @RequestMapping("/editNumberByOrderDate")
    @PreAuthorize("hasAuthority('ORDERSETTING')")//权限校验
    public Result editNumberByOrderDate(@RequestBody Map map){
        try {
            orderSettingService.editNumberByOrderDate(map);
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/getOrderSettingByMonth")
    @PreAuthorize("hasAuthority('ORDERSETTING')")//权限校验
    public Result getOrderSettingByMonth(String date){
        try {
            List<Map> list = orderSettingService.getOrderSettingByMonth(date);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,MessageConstant.QUERY_ORDER_FAIL);
        }
    }

    @RequestMapping("/upload")
//    @PreAuthorize("hasAuthority('ORDERSETTING')")//权限校验
    @PreAuthorize("hasRole('admin')")
    public Result upload(MultipartFile excelFile){
        try {
            List<String[]> list = POIUtils.readExcel(excelFile);
            List<OrderSetting> listData = new ArrayList<>();
            for (String[] strings : list) {
                String dataStr = strings[0];//日期
                String numStr = strings[1];//可预约数量
                OrderSetting orderSetting = new OrderSetting();
                orderSetting.setOrderDate(new Date(dataStr));
                orderSetting.setNumber(Integer.parseInt(numStr));
                listData.add(orderSetting);
            }
            orderSettingService.addBatch(listData);
            return new Result(true, MessageConstant.UPLOAD_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.UPLOAD_FAIL);
        }
    }
}
