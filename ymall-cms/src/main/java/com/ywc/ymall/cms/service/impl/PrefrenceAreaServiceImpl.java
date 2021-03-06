package com.ywc.ymall.cms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.cms.entity.PrefrenceArea;
import com.ywc.ymall.cms.mapper.PrefrenceAreaMapper;
import com.ywc.ymall.cms.service.PrefrenceAreaService;
import com.ywc.ymall.pms.entity.ProductAttributeCategory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * <p>
 * 优选专区 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class PrefrenceAreaServiceImpl extends ServiceImpl<PrefrenceAreaMapper, PrefrenceArea> implements PrefrenceAreaService {

    @Override
    public List<PrefrenceArea> listAll() {
        return this.baseMapper.selectList(new QueryWrapper<>());
    }
}
