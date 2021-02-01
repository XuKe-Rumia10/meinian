package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelItemDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/22  15:04
 */
@Service(interfaceClass = TravelItemService.class) //发布服务，注册到zookeeper中
@Transactional //声明式事务，所有方法都增加事务
public class TravelItemServiceImpl implements TravelItemService {

    @Autowired
    TravelItemDao travelItemDao;



    @Override
    public void add(TravelItem travelItem) {
        travelItemDao.add(travelItem);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        //分页插件
        //开启分页功能
        //limit (currentPage-1)*pageSize , pageSize
        PageHelper.startPage(currentPage,pageSize); // limit ?,? 第一个问号表示开始索引，第二个问号表示查询的条数
        Page page = travelItemDao.findPage(queryString); //得到当前页的数据
        return new PageResult(page.getTotal(),page.getResult()); //PageResult中封装两个参数，1.总记录数，2.分页数据集合
    }

    @Override
    public void delete(Integer id) {//自由行id
        //查自由行关联表中是否存在关联数据，如果存在，就抛异常，不进行删除
        long count = travelItemDao.findCountByTravelitemId(id);
        if(count > 0){//存在关联数据
            throw new RuntimeException("删除自由行失败，因为存在关联数据。先解除关系再删除");
        }
        travelItemDao.delete(id);
    }

    @Override
    public TravelItem getById(Integer id) {
        return travelItemDao.getById(id);

    }

    @Override
    public void edit(TravelItem travelItem) {
        travelItemDao.edit(travelItem);
    }

    @Override
    public List<TravelItem> findAll() {
        return travelItemDao.findAll();
    }
}
