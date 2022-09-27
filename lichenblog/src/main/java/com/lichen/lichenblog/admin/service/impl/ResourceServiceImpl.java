package com.lichen.lichenblog.admin.service.impl;

import com.lichen.lichenblog.admin.entity.Resource;
import com.lichen.lichenblog.admin.mapper.ResourceMapper;
import com.lichen.lichenblog.admin.service.IResourceService;
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
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

}
