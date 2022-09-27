package com.lichen.lichenblog.admin.service.impl;

import com.lichen.lichenblog.admin.entity.UserInfo;
import com.lichen.lichenblog.admin.mapper.UserInfoMapper;
import com.lichen.lichenblog.admin.service.IUserInfoService;
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
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
