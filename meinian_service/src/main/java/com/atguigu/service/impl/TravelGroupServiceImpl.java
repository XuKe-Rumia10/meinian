package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelGroupDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.TravelGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/23  11:40
 */
@Service(interfaceClass = TravelGroupService.class)
@Transactional
public class TravelGroupServiceImpl implements TravelGroupService {

    @Autowired
    TravelGroupDao travelGroupDao;

    @Override
    public void add(Integer[] travelItemIds, TravelGroup travelGroup) {
        travelGroupDao.add(travelGroup);//数据库分配ID
        Integer id = travelGroup.getId();//必须进行主键回填操作才能拿到id
        setTravelGroupAndTravelItem(id,travelItemIds);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page page = travelGroupDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());//数据封装
    }

    @Override
    public TravelGroup getById(Integer id) {
        return travelGroupDao.getById(id);
    }

    @Override
    public List<Integer> getTravelItemIdsByTravelGroupId(Integer travelGroupId) {
        return travelGroupDao.getTravelItemIdsByTravelGroupId(travelGroupId);
    }

    @Override
    public void edit(Integer[] travelItemIds, TravelGroup travelGroup) {
        travelGroupDao.edit(travelGroup);
        Integer id = travelGroup.getId();
        //先删除中间表的关联数据
        travelGroupDao.delete(id);
        //重新再添加关联数据
        setTravelGroupAndTravelItem(id,travelItemIds);
    }

    @Override
    public void delete(Integer id) {
        travelGroupDao.deleteById(id);
    }

    @Override
    public List<TravelGroup> findAll() {
        return travelGroupDao.findAll();
    }

    private void setTravelGroupAndTravelItem(Integer id, Integer[] travelItemIds) {
        if(travelItemIds!=null && travelItemIds.length>0){
            //准备dao层需要的参数，利用Map集合作为参数传递数据
            Map<Integer,Integer> paramData = new HashMap<>();
            for (Integer travelItemId : travelItemIds) {
                paramData.put(travelItemId,id);
            }
            travelGroupDao.setTravelGroupAndTravelItem(paramData);
        }
    }


}
