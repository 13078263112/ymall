package com.ywc.ymall.cms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.cms.entity.Subject;
import com.ywc.ymall.cms.mapper.SubjectMapper;
import com.ywc.ymall.cms.service.SubjectService;
import com.ywc.ymall.sms.entity.CouponHistory;
import com.ywc.ymall.vo.PageInfoVo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * <p>
 * 专题表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public List<Subject> listAll() {
        return this.baseMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public PageInfoVo listForPage(String keyword, Integer pageNum, Integer pageSize) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(keyword)){
            wrapper.like("title",keyword);
        }
        IPage<Subject> iPage = this.baseMapper.selectPage(new Page<Subject>(pageNum, pageSize), wrapper);
        return PageInfoVo.getVo(iPage,pageSize.longValue());
    }
}
