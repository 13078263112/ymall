package com.ywc.ymall.sms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.oms.entity.OrderReturnReason;
import com.ywc.ymall.sms.entity.FlashPromotion;
import com.ywc.ymall.sms.mapper.FlashPromotionMapper;
import com.ywc.ymall.sms.service.FlashPromotionService;
import com.ywc.ymall.vo.PageInfoVo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 限时购表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class FlashPromotionServiceImpl extends ServiceImpl<FlashPromotionMapper, FlashPromotion> implements FlashPromotionService {

    @Override
    public PageInfoVo listflashPromotionForPage(String keyword, Integer pageSize, Integer pageNum) {
        Page<FlashPromotion> page = new Page<>(pageNum, pageSize);
        QueryWrapper<FlashPromotion> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(keyword)){
            wrapper.like("title",keyword);
        }
        IPage<FlashPromotion> selectPage = this.baseMapper.selectPage(page, wrapper);
        PageInfoVo pageInfoVo = new PageInfoVo(selectPage.getTotal(),selectPage.getPages(),selectPage.getSize(),
                selectPage.getRecords(),selectPage.getCurrent());
        return pageInfoVo;
    }

    @Override
    public void createFlashPromotion(FlashPromotion flashPromotion) {
        this.baseMapper.insert(flashPromotion);
    }

    @Override
    public void deleteById(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        FlashPromotion flashPromotion = new FlashPromotion();
        flashPromotion.setId(id);
        flashPromotion.setStatus(status);
        this.baseMapper.updateById(flashPromotion);
    }
}
