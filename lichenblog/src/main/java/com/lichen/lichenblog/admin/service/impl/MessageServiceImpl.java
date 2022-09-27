package com.lichen.lichenblog.admin.service.impl;

import com.lichen.lichenblog.admin.entity.Message;
import com.lichen.lichenblog.admin.mapper.MessageMapper;
import com.lichen.lichenblog.admin.service.IMessageService;
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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
