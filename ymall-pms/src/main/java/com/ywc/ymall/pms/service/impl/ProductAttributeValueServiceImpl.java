package com.ywc.ymall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.pms.entity.ProductAttribute;
import com.ywc.ymall.pms.entity.ProductAttributeValue;
import com.ywc.ymall.pms.mapper.ProductAttributeValueMapper;
import com.ywc.ymall.pms.service.ProductAttributeValueService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 存储产品参数信息的表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class ProductAttributeValueServiceImpl extends ServiceImpl<ProductAttributeValueMapper, ProductAttributeValue> implements ProductAttributeValueService {


}
