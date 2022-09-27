package com.lichen.lichenblog.admin.mapper;


import com.lichen.lichenblog.admin.dto.ArticleBackDTO;
import com.lichen.lichenblog.admin.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lichen.lichenblog.admin.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lichen
 * @since 2022-09-13
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 查询后台博客个数
     * @param condition
     * @return Integer 个数
     */
    Integer countArticleBacks(@Param("condition") ConditionVO condition);

    List<ArticleBackDTO> listArticleBacks(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);
}
