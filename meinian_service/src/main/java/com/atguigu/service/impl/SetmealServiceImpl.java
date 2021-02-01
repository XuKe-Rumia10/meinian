package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.constant.RedisConstant;
import com.atguigu.dao.SetmealDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.Setmeal;
import com.atguigu.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/25  10:19
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    SetmealDao setmealDao;
    @Autowired
    JedisPool jedisPool;

    @Override
    public void add(Integer[] travelgroupIds, Setmeal setmeal) {
        //1.保存套餐
        setmealDao.add(setmeal);
        //2.保存关联数据
        setSetmealAndTravelGrop(travelgroupIds, setmeal.getId());

        //****************************补充代码 解决七牛云上垃圾图片的回收问题***********************
        //将上传图片名称存入redis，基于Redis的set集合存储
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
        //**************************************************************************************
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);
        Page page = setmealDao.findPage(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void edit(Integer[] travelGroupIds, Setmeal setmeal) {
        setmealDao.edit(setmeal);
        Integer id = setmeal.getId();
        //先删除中间表的关联数据
        setmealDao.delete(id);
        //重新再添加关联数据
        setSetmealAndTravelGrop(travelGroupIds, id);
    }

    @Override
    public Setmeal getById(Integer id) {
        return setmealDao.getById(id);
    }

    @Override
    public List<Integer> getTravelGropsBySetmealId(Integer setmealId) {
        return setmealDao.getTravelGropsBySetmealId(setmealId);
    }

    @Override
    public void deleteById(Integer id) {
        setmealDao.deleteById(id);
    }

    @Override
    public void delete(Integer id) {
        setmealDao.delete(id);
    }

    @Override
    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }

    @Override
    public List<Setmeal> findById(Integer id) {
        return setmealDao.findById(id);
    }

    @Override
    public Setmeal getSetmealById(Integer id) {
        return setmealDao.getSetmealById(id);
    }

    @Override
    public List<Map> getSetmealReport() {
        return setmealDao.getSetmealReport();
    }


    private void setSetmealAndTravelGrop(Integer[] travelgroupIds, Integer id) {
        if (travelgroupIds != null && travelgroupIds.length > 0) {
            Map<Integer, Integer> map = new HashMap<>();
            for (Integer travelgroupId : travelgroupIds) {
                map.put(travelgroupId, id);
            }
            setmealDao.setSetmealAndTravelGrop(map);
        }
    }
}
