package com.ywc.ymall.oms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.oms.entity.CompanyAddress;
import com.ywc.ymall.oms.entity.OrderReturnApply;
import com.ywc.ymall.oms.mapper.CompanyAddressMapper;
import com.ywc.ymall.oms.service.CompanyAddressService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 公司收发货地址表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class CompanyAddressServiceImpl extends ServiceImpl<CompanyAddressMapper, CompanyAddress> implements CompanyAddressService {

    @Override
    public List<CompanyAddress> getList() {
        QueryWrapper<CompanyAddress> wrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(wrapper);
    }
}
