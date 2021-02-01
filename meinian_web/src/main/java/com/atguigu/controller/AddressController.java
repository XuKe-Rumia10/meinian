package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.pojo.Address;
import com.atguigu.service.AddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/2/1  10:56
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    AddressService addressService;

    @RequestMapping("/findAllMaps")
    public Map findAllMaps() {
        Map map = new HashMap();
        List<Address> addressList = addressService.findAllMaps();
        
        List<Map> gridnMaps = new ArrayList<>();//标记地址的经纬度
        List<Map> nameMaps = new ArrayList<>();//标记地址名称
        for (Map address : nameMaps) {
            
        }
        map.put("gridnMaps",gridnMaps);
        map.put("nameMaps",nameMaps);
        return map;
    }
}
