package com.ywc.ymall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.pms.entity.Product;
import com.ywc.ymall.pms.entity.ProductAttribute;
import com.ywc.ymall.pms.mapper.ProductAttributeMapper;
import com.ywc.ymall.pms.service.ProductAttributeService;
import com.ywc.ymall.vo.PageInfoVo;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements ProductAttributeService {

    @Override
    public PageInfoVo getCategoryAttributes(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        Page<ProductAttribute> page = new Page<>(pageNum, pageSize);
        QueryWrapper<ProductAttribute> eq = new QueryWrapper<ProductAttribute>()
                .eq("product_attribute_category_id", cid)
                .eq("type", type);
        IPage<ProductAttribute> productAttributeIPage = this.baseMapper.selectPage(page, eq);

        return PageInfoVo.getVo(productAttributeIPage,pageSize.longValue());
    }
}
