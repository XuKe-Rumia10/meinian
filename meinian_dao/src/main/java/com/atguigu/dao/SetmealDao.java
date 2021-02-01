package com.atguigu.dao;

import com.atguigu.pojo.Setmeal;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/25  10:20
 */
public interface SetmealDao {
    void setSetmealAndTravelGrop(@Param("ids") Map<Integer, Integer> ids);

    void add(Setmeal setmeal);

    Page<Setmeal> findPage(String queryString);

    void edit(Setmeal setmeal);

    void delete(Integer id);

    Setmeal getById(Integer id);

    List<Integer> getTravelGropsBySetmealId(Integer setmealId);

    void deleteById(Integer id);

    List<Setmeal> findAll();

    List<Setmeal> findById(Integer id);

    Setmeal getSetmealById(Integer id);

    List<Map> getSetmealReport();
}
