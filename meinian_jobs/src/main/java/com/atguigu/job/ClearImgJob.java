package com.atguigu.job;

import com.atguigu.constant.RedisConstant;
import com.atguigu.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/25  22:13
 */
public class ClearImgJob {
    @Autowired
    JedisPool jedisPool;

    //用于清理图片
    public void clearImg(){
        //计算redis中两个集合的差值，获取垃圾图片名称
        //需要注意：在比较的时候，数据多的放前面
        Set<String> pics = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        for (String pic : pics) {
            //删除七牛云中的垃圾图片
            QiniuUtils.deleteFileFromQiniu(pic);
            //删除redis中记录的垃圾图片信息
            jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,pic);
        }
    }
}
