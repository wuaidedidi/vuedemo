package com.lichen.lichenblog.admin.service;

import com.lichen.lichenblog.admin.dto.TagDTO;
import com.lichen.lichenblog.admin.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lichen.lichenblog.admin.vo.ConditionVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lichen
 * @since 2022-09-13
 */
public interface ITagService extends IService<Tag> {
    /**
     * 搜索文章标签
     *
     * @param condition 条件
     * @return {@link List <TagDTO>} 标签列表
     */
    List<TagDTO> listTagsBySearch(ConditionVO condition);
}
