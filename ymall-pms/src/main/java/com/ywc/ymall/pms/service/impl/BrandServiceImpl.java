package com.ywc.ymall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.pms.entity.Brand;
import com.ywc.ymall.pms.mapper.BrandMapper;
import com.ywc.ymall.pms.service.BrandService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Override
    public Map<String, Object> pageBrand(String keyword, Integer pageNum, Integer pageSize) {
        QueryWrapper<Brand> eq = null;
        //keyword 按照品牌名或者首字母模糊匹配
        if(!StringUtils.isEmpty(keyword)){
            eq = new QueryWrapper<Brand>().like("name", keyword)
                    .eq("first_letter", keyword);
        }
        IPage<Brand> selectPage = this.baseMapper.selectPage(new Page<Brand>(pageNum, pageSize), eq);
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("pageSize",pageSize);
        map.put("totalPage",selectPage.getPages());
        map.put("total",selectPage.getTotal());
        map.put("pageNum",selectPage.getCurrent());
        map.put("list",selectPage.getRecords());
        return map;
    }
}
