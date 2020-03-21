package com.ywc.ymall.ums.service.impl;

import com.ywc.ymall.ums.entity.Member;
import com.ywc.ymall.ums.mapper.MemberMapper;
import com.ywc.ymall.ums.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

}
