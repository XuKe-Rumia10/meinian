package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.constant.RedisConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Setmeal;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.SetmealService;
import com.atguigu.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.rmi.MarshalException;
import java.util.List;
import java.util.UUID;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/25  10:18
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    SetmealService setmealService;

    @Autowired
    JedisPool jedisPool;

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('SETMEAL_DELETE')")//权限校验
    public Result delete(Integer id){
        try {
            setmealService.deleteById(id);
            return new Result(true,MessageConstant.DELETE_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_SETMEAL_FAIL+"，请移除关联的跟团游后再操作");
        }
    }

    @RequestMapping("/getTravelGropsBySetmealId")
    @PreAuthorize("hasAuthority('SETMEAL_QUERY')")//权限校验
    public Result getTravelGropsBySetmealId(Integer setmealId){
        try {
            List<Integer> list =  setmealService.getTravelGropsBySetmealId(setmealId);
            return new Result(true,"根据套餐查询跟团游成功",list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"根据套餐查询跟团游失败");
        }
    }

    @RequestMapping("/getById")
    @PreAuthorize("hasAuthority('SETMEAL_QUERY')")//权限校验
    public Result getById(Integer id){
        try {
            Setmeal setmeal = setmealService.getById(id);
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('SETMEAL_EDIT')")//权限校验
    public Result edit(Integer[] travelGroupIds,@RequestBody Setmeal setmeal){
        try {
            setmealService.edit(travelGroupIds,setmeal);
            return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_SETMEAL_FAIL);
        }
    }

    @RequestMapping("/findPage")
    @PreAuthorize("hasAuthority('SETMEAL_QUERY')")//权限校验
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return setmealService.findPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('SETMEAL_ADD')")//权限校验
    public Result add(Integer[] travelgroupIds,@RequestBody Setmeal setmeal){
        try {
            setmealService.add(travelgroupIds,setmeal);
            return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    @RequestMapping("/upload")
    @PreAuthorize("hasAuthority('SETMEAL_EDIT')")//权限校验
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile){
        try {
            //1.获取原始文件名称
            String originalFilename = imgFile.getOriginalFilename();
            //2.生成新的文件名称（防止上传同名文件被覆盖）
            int index = originalFilename.lastIndexOf(".");//名称.扩展名，以.分割，前边为名称
            String substring = originalFilename.substring(index);
            String filename = UUID.randomUUID().toString() + substring;
            //3.调用工具类，上传图片到七牛云
            QiniuUtils.upload2Qiniu(imgFile.getBytes(), filename);

            //****************************补充代码 解决七牛云上垃圾图片的回收问题***********************
            //将上传图片名称存入redis，基于Redis的set集合存储
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,filename);
            //**************************************************************************************

            //4.返回结果
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,filename);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }

    }
}
