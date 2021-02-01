package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.TravelGroupService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/23  11:39
 */
@RestController
@RequestMapping("/travelGroup")
public class TravelGroupController {

    @Reference //调用zookeeper中的服务
    TravelGroupService travelGroupService;

    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<TravelGroup> listAll = travelGroupService.findAll();
            return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,listAll);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }

    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('TRAVELGROUP_DELETE')")//权限校验
    public Result delete(Integer id){
        try {
            travelGroupService.delete(id);
            return new Result(true,MessageConstant.DELETE_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_TRAVELGROUP_FAIL+"，请移除关联的自由行后再操作");
        }
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('TRAVELGROUP_EDIT')")//权限校验
    public Result edit(Integer[] travelItemIds,@RequestBody TravelGroup travelGroup){//接收两部分数据
        try {
            travelGroupService.edit(travelItemIds,travelGroup);
            return new Result(true, MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_TRAVELGROUP_FAIL);
        }
    }

    @RequestMapping("/getTravelItemIdsByTravelGroupId")
    @PreAuthorize("hasAuthority('TRAVELGROUP_QUERY')")//权限校验
    public Result getTravelItemIdsByTravelGroupId(Integer travelGroupId){
        try {
            List<Integer> list =  travelGroupService.getTravelItemIdsByTravelGroupId(travelGroupId);
            return new Result(true,"根据跟团游查询自由行成功",list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"根据跟团游查询自由行失败");
        }

    }

    @RequestMapping("/getById")
    @PreAuthorize("hasAuthority('TRAVELGROUP_QUERY')")//权限校验
    public Result getById(Integer id){
        try {
            TravelGroup travelGroup = travelGroupService.getById(id);
            return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }

    @RequestMapping("/findPage")
    @PreAuthorize("hasAuthority('TRAVELGROUP_QUERY')")//权限校验
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return travelGroupService.findPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(), queryPageBean.getQueryString());
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('TRAVELGROUP_ADD')")//权限校验
    public Result add(Integer[] travelItemIds,@RequestBody TravelGroup travelGroup){//接收两部分数据
        try {
            travelGroupService.add(travelItemIds,travelGroup);
            return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_TRAVELGROUP_FAIL);
        }
    }
}
