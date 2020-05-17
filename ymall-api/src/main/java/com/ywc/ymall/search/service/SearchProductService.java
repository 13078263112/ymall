package com.ywc.ymall.search.service;

import com.ywc.ymall.vo.search.SearchParam;
import com.ywc.ymall.vo.search.SearchResponse;

/**
 * 商品检索服务
 * @author 嘟嘟~
 * @date 2020/5/5 14:39
 */
public interface SearchProductService {
    SearchResponse searchProduct(SearchParam searchParam);
}
