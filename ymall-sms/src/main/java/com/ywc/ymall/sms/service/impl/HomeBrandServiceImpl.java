package com.ywc.ymall.sms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.sms.entity.HomeBrand;
import com.ywc.ymall.sms.mapper.HomeBrandMapper;
import com.ywc.ymall.sms.service.HomeBrandService;
import com.ywc.ymall.vo.PageInfoVo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 首页推荐品牌表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class HomeBrandServiceImpl extends ServiceImpl<HomeBrandMapper, HomeBrand> implements HomeBrandService {

    @Override
    public PageInfoVo listBrandForPage(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        QueryWrapper<HomeBrand> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(brandName)){
            wrapper.like("brand_name",brandName);
        }

        if(recommendStatus!=null){
            wrapper.eq("recommend_status",recommendStatus);
        }
        IPage<HomeBrand> iPage = this.baseMapper
                .selectPage(new Page<HomeBrand>(pageNum, pageSize), wrapper);
        return PageInfoVo.getVo(iPage,pageSize.longValue());
    }

    @Override
    public void updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        ids.forEach((id)->{
            HomeBrand homeBrand = new HomeBrand();
            homeBrand.setId(id);
            homeBrand.setRecommendStatus(recommendStatus);
            this.baseMapper.updateById(homeBrand);
        });
    }

    @Override
    public void updateSort(Long id, Integer sort) {
        HomeBrand homeBrand = new HomeBrand();
        homeBrand.setId(id);
        homeBrand.setSort(sort);
       this.baseMapper.updateById(homeBrand);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        ids.forEach((id)->{
            this.baseMapper.deleteById(id);
        });
    }

    @Override
    public void create(List<HomeBrand> homeBrandList) {
        homeBrandList.forEach(homeBrand ->{
            homeBrand.setRecommendStatus(0);
            homeBrand.setSort(0);
            this.baseMapper.insert(homeBrand);
        });
    }
}
