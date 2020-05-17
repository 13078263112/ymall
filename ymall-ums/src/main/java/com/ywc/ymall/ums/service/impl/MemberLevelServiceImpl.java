package com.ywc.ymall.ums.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.ums.entity.MemberLevel;
import com.ywc.ymall.ums.mapper.MemberLevelMapper;
import com.ywc.ymall.ums.service.MemberLevelService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 会员等级表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper, MemberLevel> implements MemberLevelService {

    @Override
    public List<MemberLevel> getMemberLevelByStatus(Integer defaultStatus) {
        return this.baseMapper.selectList(new QueryWrapper<MemberLevel>().eq("default_status", defaultStatus));
    }
}
