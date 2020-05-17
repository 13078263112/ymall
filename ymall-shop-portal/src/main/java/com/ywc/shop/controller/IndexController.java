package com.ywc.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.pms.entity.Product;
import com.ywc.ymall.pms.entity.ProductCategory;
import com.ywc.ymall.pms.service.ProductCategoryService;
import com.ywc.ymall.pms.service.ProductService;
import com.ywc.ymall.sms.entity.HomeAdvertise;
import com.ywc.ymall.sms.service.HomeAdvertiseService;
import com.ywc.ymall.to.PmsProductCategoryWithChildrenItem;
import com.ywc.ymall.to.ResultParam;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

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
        List<ProductCategory> productCategory =  productCategoryService.queryByParentId(0L);

        return new ResultParam().success(productCategory);
    }

    /**
     * 查询下级分类
     * @param parentId
     * @return
     */
    @GetMapping("/cates/{parentId}")
    public  ResultParam catesNext(@PathVariable Integer parentId){
        System.out.println(1);
        List<PmsProductCategoryWithChildrenItem> pmsProductCategoryWithChildrenItem =  productCategoryService.listWithChildrenById(parentId);
        return new ResultParam().success(pmsProductCategoryWithChildrenItem);
    }

    /**
     * 最受欢迎的
     *
     * @return
     */
    @GetMapping("/miaosha")
    public  ResultParam hotProduct(){
       //recommand_status
      List<Product> products= productService.queryAllByRecommand();
        return new ResultParam().success(products);
    }
}
