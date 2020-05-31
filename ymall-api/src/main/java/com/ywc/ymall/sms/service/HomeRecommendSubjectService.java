package com.ywc.ymall.sms.service;

import com.ywc.ymall.sms.entity.HomeRecommendSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.vo.PageInfoVo;

import java.util.List;

/**
 * <p>
 * 首页推荐专题表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface HomeRecommendSubjectService extends IService<HomeRecommendSubject> {

    void create(List<HomeRecommendSubject> homeBrandList);

    void updateSort(Long id, Integer sort);

    void deleteByIds(List<Long> ids);

    void updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    PageInfoVo listForPage(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}
