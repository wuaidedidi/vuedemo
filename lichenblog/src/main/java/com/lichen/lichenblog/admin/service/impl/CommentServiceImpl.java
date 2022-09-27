package com.lichen.lichenblog.admin.service.impl;

import com.lichen.lichenblog.admin.entity.Comment;
import com.lichen.lichenblog.admin.mapper.CommentMapper;
import com.lichen.lichenblog.admin.service.ICommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
