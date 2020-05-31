package com.ywc.ymall.sms.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.pms.entity.Product;
import com.ywc.ymall.pms.service.ProductService;
import com.ywc.ymall.sms.entity.FlashPromotionProductRelation;
import com.ywc.ymall.sms.entity.FlashPromotionSession;
import com.ywc.ymall.sms.mapper.FlashPromotionProductRelationMapper;
import com.ywc.ymall.sms.service.FlashPromotionProductRelationService;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.oms.FlashPromotionProductRelationParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class FlashPromotionProductRelationServiceImpl extends ServiceImpl<FlashPromotionProductRelationMapper, FlashPromotionProductRelation> implements FlashPromotionProductRelationService {
     @Reference
    ProductService productService;


    @Override
    public List<FlashPromotionProductRelation> selectByflashPromotionId(Long flashPromotionId) {
        QueryWrapper<FlashPromotionProductRelation> wrapper =
                new QueryWrapper<FlashPromotionProductRelation>().eq("flash_promotion_session_id",flashPromotionId);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public PageInfoVo listRelationForPage(Long flashPromotionId,
                                          Long flashPromotionSessionId,
                                          Integer pageSize, Integer pageNum) {
            QueryWrapper<FlashPromotionProductRelation> wrapper = new QueryWrapper<>();
            if(flashPromotionId!=null){
                wrapper.eq("flash_promotion_id",flashPromotionId);
            }
            if(flashPromotionSessionId!=null){
                wrapper.eq("flash_promotion_session_id",flashPromotionSessionId);
            }
            IPage<FlashPromotionProductRelation> selectPage = this.baseMapper
                    .selectPage(new Page<FlashPromotionProductRelation>(pageNum, pageSize),
                            wrapper);
        List<FlashPromotionProductRelationParam> list = new ArrayList<>();
        selectPage.getRecords().stream().forEach(flashPromotionProductRelation -> {
            FlashPromotionProductRelationParam flashPromotionProductRelationParam = new FlashPromotionProductRelationParam();
            BeanUtils.copyProperties(flashPromotionProductRelation,flashPromotionProductRelationParam);
            Product updateInfo = productService.getUpdateInfo(flashPromotionProductRelation.getProductId());
            flashPromotionProductRelationParam.setProduct(updateInfo);
            list.add(flashPromotionProductRelationParam);
        });
        PageInfoVo pageInfoVo = new PageInfoVo(selectPage.getTotal(),selectPage.getPages(),selectPage.getSize(),
                list,selectPage.getCurrent());
            return pageInfoVo;
    }

    @Override
    public void create(List<FlashPromotionProductRelation> relationList) {
        relationList.stream().forEach(flashPromotionProductRelation -> {
            this.baseMapper.insert(flashPromotionProductRelation);
        });
    }

    @Override
    public void updateFlashPromotionProductRelationById(FlashPromotionProductRelation relation) {
        this.baseMapper.updateById(relation);
    }

    @Override
    public void deleteById(Long id) {
        this.baseMapper.deleteById(id);
    }
}
