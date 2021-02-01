package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.Setmeal;
import com.atguigu.pojo.TravelGroup;

import java.util.List;
import java.util.Map;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/25  10:19
 */
public interface SetmealService {
    void add(Integer[] travelgroupIds, Setmeal setmeal);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    void edit(Integer[] travelGroupIds, Setmeal setmeal);

    Setmeal getById(Integer id);

    List<Integer> getTravelGropsBySetmealId(Integer setmealId);

    void deleteById(Integer id);

    void delete(Integer id);

    List<Setmeal> findAll();

    List<Setmeal> findById(Integer id);

    Setmeal getSetmealById(Integer id);

    List<Map> getSetmealReport();
}
