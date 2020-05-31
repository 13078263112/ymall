package com.ywc.ymall.sms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.sms.entity.HomeRecommendSubject;
import com.ywc.ymall.sms.mapper.HomeRecommendSubjectMapper;
import com.ywc.ymall.sms.service.HomeRecommendSubjectService;
import com.ywc.ymall.vo.PageInfoVo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 首页推荐专题表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class HomeRecommendSubjectServiceImpl extends ServiceImpl<HomeRecommendSubjectMapper, HomeRecommendSubject> implements HomeRecommendSubjectService {

    @Override
    public void create(List<HomeRecommendSubject> homeBrandList) {
        homeBrandList.forEach(homeRecommendSubject ->{
            homeRecommendSubject.setRecommendStatus(0);
            homeRecommendSubject.setSort(0);
            this.baseMapper.insert(homeRecommendSubject);
        });
    }

    @Override
    public void updateSort(Long id, Integer sort) {
        HomeRecommendSubject subject = new HomeRecommendSubject();
        subject.setId(id);subject.setSort(sort);
        this.baseMapper.updateById(subject);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        ids.forEach(id->{
            this.baseMapper.deleteById(id);
        });
    }

    @Override
    public void updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        ids.forEach((id)->{
            HomeRecommendSubject subject = new HomeRecommendSubject();
            subject.setId(id);subject.setRecommendStatus(recommendStatus);
            this.baseMapper.updateById(subject);
        });
    }

    @Override
    public PageInfoVo listForPage(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        QueryWrapper<HomeRecommendSubject> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(subjectName)){
            wrapper.like("subject_name",subjectName);
        }
        if(recommendStatus!=null){
            wrapper.eq("recommend_status",recommendStatus);
        }
        IPage<HomeRecommendSubject> iPage = this.baseMapper.selectPage(new Page<HomeRecommendSubject>(pageNum, pageSize), wrapper);
        return  PageInfoVo.getVo(iPage,pageSize.longValue());
    }
}
