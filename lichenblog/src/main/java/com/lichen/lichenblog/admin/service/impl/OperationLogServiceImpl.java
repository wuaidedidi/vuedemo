package com.lichen.lichenblog.admin.service.impl;

import com.lichen.lichenblog.admin.entity.OperationLog;
import com.lichen.lichenblog.admin.mapper.OperationLogMapper;
import com.lichen.lichenblog.admin.service.IOperationLogService;
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
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

}
