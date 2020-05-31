package com.ywc.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.pms.entity.Product;
import com.ywc.ymall.pms.entity.ProductCategory;
import com.ywc.ymall.pms.service.ProductCategoryService;
import com.ywc.ymall.pms.service.ProductService;
import com.ywc.ymall.sms.entity.HomeAdvertise;
import com.ywc.ymall.sms.entity.HomeRecommendProduct;
import com.ywc.ymall.sms.service.HomeAdvertiseService;
import com.ywc.ymall.sms.service.HomeRecommendProductService;
import com.ywc.ymall.to.PmsProductCategoryWithChildrenItem;
import com.ywc.ymall.to.ResultParam;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 嘟嘟~
 * @date 2020/5/6 20:59
 */
@Api(tags = "主页数据")
@RestController
@RequestMapping("/index")
public class IndexController {

    @Reference
    HomeAdvertiseService homeAdvertiseService;
    @Reference
    ProductCategoryService productCategoryService;
    @Reference
    ProductService  productService;

    /**
     * 查询导航条
     * @return
     */
    @GetMapping("/adv")
    public  ResultParam adv(){
    List<HomeAdvertise>   homeAdvertises=homeAdvertiseService.queryHomeAdvertiseAll();
        return new ResultParam().success(homeAdvertises);
    }

    /**
     * 查询一级分类
     * @return
     */
    @GetMapping("/cates")
    public  ResultParam cates(){
        List<PmsProductCategoryWithChildrenItem> productCategory =  productCategoryService.listWithChildren(0);

        return new ResultParam().success(productCategory);
    }

    /**
     * 查询下级分类
     * @param parentId
     * @return
     */
    @GetMapping("/cates/{parentId}")
    public  ResultParam catesNext(@PathVariable long parentId){
        System.out.println(parentId);
        List<PmsProductCategoryWithChildrenItem> pmsProductCategoryWithChildrenItem =  productCategoryService.listWithChildrenById((int) parentId);
        return new ResultParam().success(pmsProductCategoryWithChildrenItem);
    }

    /**
     * 最受欢迎的
     *
     * @return
     */
    @GetMapping("/hot_product")
    public  ResultParam hotProduct(){
        List<Map> list=new ArrayList<>();
        Map<String,String> map=new HashMap<>();
        map.put("pic","https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/35a2239e10e392af73b6b7a737a039d6.jpg?w=632&h=340");
        list.add(map);
        Map<String,String> map1=new HashMap<>();
        map1.put("pic","https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/fbff319c7dd00e75c9758acf248d3784.jpg?w=632&h=340");
        list.add(map1);
        Map<String,String> map2=new HashMap<>();
        map2.put("pic","https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/816a66edef10673b4768128b41804cae.jpg?w=632&h=340");
        list.add(map2);
        return new ResultParam().success(list);
    }
}
