package com.lichen.lichenblog.admin.service.impl;

import com.lichen.lichenblog.admin.entity.Photo;
import com.lichen.lichenblog.admin.mapper.PhotoMapper;
import com.lichen.lichenblog.admin.service.IPhotoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 照片 服务实现类
 * </p>
 *
 * @author lichen
 * @since 2022-09-13
 */
@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements IPhotoService {

}
