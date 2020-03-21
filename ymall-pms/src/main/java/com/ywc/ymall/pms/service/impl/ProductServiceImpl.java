package com.ywc.ymall.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.pms.entity.Product;
import com.ywc.ymall.pms.mapper.ProductMapper;
import com.ywc.ymall.pms.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
