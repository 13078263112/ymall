package com.ywc.ymall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.pms.entity.Product;
import com.ywc.ymall.pms.entity.SkuStock;
import com.ywc.ymall.pms.mapper.SkuStockMapper;
import com.ywc.ymall.pms.service.SkuStockService;
import com.ywc.ymall.vo.PageInfoVo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * sku的库存 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class SkuStockServiceImpl extends ServiceImpl<SkuStockMapper, SkuStock> implements SkuStockService {

    @Override
    public List<SkuStock> querySkuByIdByName(Long pid, String keyword) {
        QueryWrapper<SkuStock> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id",pid);
        if(!StringUtils.isEmpty(keyword)){
            wrapper.like("sku_code",keyword);
        }
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public int updateSkuByPid(List<SkuStock> skuStockList) {
        for (SkuStock skuStock : skuStockList) {
            this.baseMapper.updateById(skuStock);
        }
        return 0;
    }
}
