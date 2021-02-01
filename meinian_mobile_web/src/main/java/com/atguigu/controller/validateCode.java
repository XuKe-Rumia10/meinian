package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.constant.RedisMessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.utils.SMSUtils;
import com.atguigu.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.print.attribute.standard.MediaSize;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/27  15:22
 */
@RequestMapping("/validateCode")
@RestController
public class validateCode {

    @Autowired
    JedisPool jedisPool;

    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        //1.生成4位验证码
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        try {
            //2.发送验证码到手机号
            SMSUtils.sendShortMessage(telephone,code.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //3.将验证码保存到redis中，进行后期验证
        jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_LOGIN,300,code.toString());
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }

    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        try {
            //1.生成4位验证码
            Integer code = ValidateCodeUtils.generateValidateCode(4);
            //2.发送验证码到手机号
            SMSUtils.sendShortMessage(telephone,code.toString());
            //3.将验证码保存到redis中，进行后期验证
            jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_ORDER,300,code.toString());


            return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }

    }
}
