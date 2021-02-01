package com.atguigu.dao;

import com.atguigu.pojo.TravelItem;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/22  20:18
 */
public interface TravelItemDao {
    void add(TravelItem travelItem);

    Page<TravelItem> findPage(String queryString);

    void delete(Integer id);

    TravelItem getById(Integer id);

    void edit(TravelItem travelItem);

    List<TravelItem> findAll();

    long findCountByTravelitemId(Integer id);

    List<TravelItem> findTravelItemById(Integer id);
}
