package com.lichen.lichenblog.admin.service.impl;

import com.lichen.lichenblog.admin.entity.UserAuth;
import com.lichen.lichenblog.admin.mapper.UserAuthMapper;
import com.lichen.lichenblog.admin.service.IUserAuthService;
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
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements IUserAuthService {

}
