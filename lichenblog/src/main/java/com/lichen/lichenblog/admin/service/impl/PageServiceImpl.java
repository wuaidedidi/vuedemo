package com.lichen.lichenblog.admin.service.impl;

import com.lichen.lichenblog.admin.entity.Page;
import com.lichen.lichenblog.admin.mapper.PageMapper;
import com.lichen.lichenblog.admin.service.IPageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 页面 服务实现类
 * </p>
 *
 * @author lichen
 * @since 2022-09-13
 */
@Service
public class PageServiceImpl extends ServiceImpl<PageMapper, Page> implements IPageService {

}
