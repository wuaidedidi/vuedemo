package com.lichen.lichenblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lichen.lichenblog.admin.dto.TagDTO;
import com.lichen.lichenblog.admin.entity.Tag;
import com.lichen.lichenblog.admin.mapper.TagMapper;
import com.lichen.lichenblog.admin.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lichen.lichenblog.admin.util.BeanCopyUtils;
import com.lichen.lichenblog.admin.vo.ConditionVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lichen
 * @since 2022-09-13
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {
    @Autowired
    TagMapper tagMapper;
    @Override
    public List<TagDTO> listTagsBySearch(ConditionVO condition) {
        List<Tag> tagList=tagMapper.selectList(new LambdaQueryWrapper<Tag>()
        .like(StringUtils.isNotBlank(condition.getKeywords()),Tag::getTagName,condition.getKeywords()));
        return BeanCopyUtils.copyList(tagList,TagDTO.class);
    }
}
