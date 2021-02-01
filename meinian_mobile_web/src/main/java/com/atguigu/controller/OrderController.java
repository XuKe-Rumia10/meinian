package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.constant.RedisMessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Order;
import com.atguigu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/27  15:51
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    OrderService orderService;
    @Autowired
    JedisPool jedisPool;

    @RequestMapping("/saveOrder")
    public Result saveOrder(@RequestBody Map map){//由于页面表单数据无法封装进pojo对象，所以使用map来进行数据接受
        try {
            String telephone = (String) map.get("telephone");
            String validateCode = (String) map.get("validateCode");
            String redisCode = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
            if(redisCode == null || !redisCode.equals(validateCode)){
                return new Result(false,MessageConstant.VALIDATECODE_ERROR);
            }
            return orderService.saveOrder(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDER_FAIL);
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            Map map = orderService.findById(id);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
