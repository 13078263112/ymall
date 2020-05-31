package com.ywc.ymall.sms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.pms.entity.ProductCategory;
import com.ywc.ymall.sms.entity.HomeAdvertise;
import com.ywc.ymall.sms.mapper.HomeAdvertiseMapper;
import com.ywc.ymall.sms.service.HomeAdvertiseService;
import com.ywc.ymall.vo.PageInfoVo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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

    @Override
    public void create(HomeAdvertise advertise) {
        this.baseMapper.insert(advertise);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        ids.forEach(id->{
            this.baseMapper.deleteById(id);
        });
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        HomeAdvertise homeAdvertise = new HomeAdvertise();
        homeAdvertise.setId(id);
        homeAdvertise.setStatus(status);
        this.baseMapper.updateById(homeAdvertise);
    }

    @Override
    public HomeAdvertise seleteHomeAdvertiseById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public void updateHomeAdvertiseById(HomeAdvertise advertise) {
        this.baseMapper.updateById(advertise);
    }

    @Override
    public PageInfoVo listAdvertiseForPage(String name, Integer type, String endTime, Integer pageSize, Integer pageNum) {
        QueryWrapper<HomeAdvertise> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(endTime)) {
            wrapper.apply(!StringUtils.isEmpty(endTime),"DATE_FORMAT( end_time, '%Y-%m-%d' ) " +
                    " >= DATE_FORMAT( '"+endTime+"', '%Y-%m-%d' )");
        }
        if(type!=null){
            wrapper.eq("type",type);
        }
        IPage<HomeAdvertise> iPage = this.baseMapper.selectPage(new Page<HomeAdvertise>(pageNum, pageSize), wrapper);
        return PageInfoVo.getVo(iPage,pageSize.longValue());
    }
}
