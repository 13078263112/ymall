package com.ywc.ymall.sms.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.oms.entity.OrderReturnApply;
import com.ywc.ymall.sms.entity.FlashPromotion;
import com.ywc.ymall.sms.entity.FlashPromotionProductRelation;
import com.ywc.ymall.sms.entity.FlashPromotionSession;
import com.ywc.ymall.sms.mapper.FlashPromotionSessionMapper;
import com.ywc.ymall.sms.service.FlashPromotionProductRelationService;
import com.ywc.ymall.sms.service.FlashPromotionSessionService;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.oms.FlashPromotionSessionParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import sun.font.BidiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 限时购场次表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class FlashPromotionSessionServiceImpl extends ServiceImpl<FlashPromotionSessionMapper, FlashPromotionSession> implements FlashPromotionSessionService {
    @Reference
    FlashPromotionProductRelationService flashPromotionProductRelationService;

    @Override
    public List<FlashPromotionSession> getList() {
        QueryWrapper<FlashPromotionSession> wrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public void create(FlashPromotionSession promotionSession) {
        this.baseMapper.insert(promotionSession);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        FlashPromotionSession session = new FlashPromotionSession();
        session.setId(id);
        session.setStatus(status);
        this.baseMapper.updateById(session);
    }

    @Override
    public void updateFlashPromotionSessionById(FlashPromotionSession promotionSession) {
        this.baseMapper.updateById(promotionSession);
    }

    @Override
    public void deleteById(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public List<FlashPromotionSessionParam> selectListForPage(Long flashPromotionId) {
        QueryWrapper<FlashPromotionSession> wrapper =
                new QueryWrapper<FlashPromotionSession>();
        Page<FlashPromotionSession> page = new Page<>(1, 100);
        IPage<FlashPromotionSession> selectPage = this.baseMapper.selectPage(page, wrapper);
        List<FlashPromotionSessionParam> list=new ArrayList();
        selectPage.getRecords().stream().forEach(flashPromotionSession ->{
        List<FlashPromotionProductRelation>  flashPromotionProductRelation= flashPromotionProductRelationService.selectByflashPromotionId(flashPromotionSession.getId());
            FlashPromotionSessionParam flashPromotionSessionParam = new FlashPromotionSessionParam();
            BeanUtils.copyProperties(flashPromotionSession,flashPromotionSessionParam);
            flashPromotionSessionParam.setProductCount(flashPromotionProductRelation.size());
            list.add(flashPromotionSessionParam);
        });
        return list;
    }

}
