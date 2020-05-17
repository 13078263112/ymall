package com.ywc.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.search.service.SearchProductService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.search.SearchParam;
import com.ywc.ymall.vo.search.SearchResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 嘟嘟~
 * @date 2020/5/5 14:42
 */
@Api(tags = "检索功能")
@RestController
public class ProductSearchController {
    @Reference
    SearchProductService searchProductService;

    @ApiOperation("商品检索")
    @GetMapping("/search")
    public SearchResponse productSearchResponse(SearchParam searchParam){
        /**
         * 检索商品
         */
        SearchResponse searchResponse = searchProductService.searchProduct(searchParam);
        return searchResponse;
    }

}
