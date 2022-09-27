package com.lichen.lichenblog.admin.service;

import com.lichen.lichenblog.admin.dto.ArticleBackDTO;
import com.lichen.lichenblog.admin.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lichen.lichenblog.admin.vo.ArticleVO;
import com.lichen.lichenblog.admin.vo.ConditionVO;
import com.lichen.lichenblog.admin.vo.PageResult;

import javax.validation.Valid;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lichen
 * @since 2022-09-13
 */
public interface IArticleService extends IService<Article> {

    /**
     * 添加或修改文章
     *
     * @param articleVO 文章信息
     */
    void saveOrUpdateArticle(ArticleVO articleVO);

    PageResult<ArticleBackDTO> listArticleBacks(ConditionVO condition);
}
