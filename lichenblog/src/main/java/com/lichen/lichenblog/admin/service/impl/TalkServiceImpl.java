package com.lichen.lichenblog.admin.service.impl;

import com.lichen.lichenblog.admin.entity.Talk;
import com.lichen.lichenblog.admin.mapper.TalkMapper;
import com.lichen.lichenblog.admin.service.ITalkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lichen
 * @since 2022-09-13
 */
@Service
public class TalkServiceImpl extends ServiceImpl<TalkMapper, Talk> implements ITalkService {

}
