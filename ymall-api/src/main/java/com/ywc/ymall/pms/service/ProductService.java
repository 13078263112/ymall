package com.ywc.ymall.pms.service;

import com.ywc.ymall.pms.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.PmsProductQueryParam;

import java.util.Map;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface ProductService extends IService<Product> {

    PageInfoVo pageProduct(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum);
}
