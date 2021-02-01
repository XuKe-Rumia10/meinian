package com.atguigu.dao;

import com.atguigu.pojo.TravelGroup;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/23  11:41
 */
public interface TravelGroupDao {
    void add(TravelGroup travelGroup);

    void setTravelGroupAndTravelItem(@Param("ids") Map<Integer, Integer> paramData);

    Page<TravelGroup> findPage(String queryString);

    TravelGroup getById(Integer id);

    List<Integer> getTravelItemIdsByTravelGroupId(Integer travelGroupId);

    void edit(TravelGroup travelGroup);

    void delete(Integer id);

    void deleteById(Integer id);

    List<TravelGroup> findAll();

    //帮助套餐查询关联跟团游数据方法
    List<TravelGroup> getTravelGroupById(Integer id);
}
