package com.ywc.ymall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.pms.entity.AlbumPic;
import com.ywc.ymall.pms.mapper.AlbumPicMapper;
import com.ywc.ymall.pms.service.AlbumPicService;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 画册图片表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class AlbumPicServiceImpl extends ServiceImpl<AlbumPicMapper, AlbumPic> implements AlbumPicService {

}
