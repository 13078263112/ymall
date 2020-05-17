package com.ywc.ymall.sms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.pms.entity.ProductCategory;
import com.ywc.ymall.sms.entity.HomeAdvertise;
import com.ywc.ymall.sms.mapper.HomeAdvertiseMapper;
import com.ywc.ymall.sms.service.HomeAdvertiseService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class HomeAdvertiseServiceImpl extends ServiceImpl<HomeAdvertiseMapper, HomeAdvertise> implements HomeAdvertiseService {

    @Override
    public List<HomeAdvertise> queryHomeAdvertiseAll() {
        QueryWrapper<HomeAdvertise> wrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(wrapper);
    }
}
