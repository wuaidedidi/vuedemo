package com.lichen.lichenblog.admin.strategy.impl;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import com.lichen.lichenblog.admin.exception.BizException;
import com.lichen.lichenblog.admin.service.IArticleService;
import com.lichen.lichenblog.admin.strategy.ArticleImportStrategy;
import com.lichen.lichenblog.admin.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import static com.lichen.lichenblog.admin.enums.ArticleStatusEnum.DRAFT;


/**
 * 普通文章导入策略
 *
 * @author linweiyuan
 * @date 2022/07/28
 */
@Slf4j
@Service("normalArticleImportStrategyImpl")
public class NormalArticleImportStrategyImpl implements ArticleImportStrategy {
    @Autowired
    private IArticleService articleService;

    @Override
    public void importArticles(MultipartFile file) {
        // 获取文件名作为文章标题
        String articleTitle = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[0];
        // 获取文章内容
        StringBuilder articleContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            while (reader.ready()) {
                articleContent.append((char) reader.read());
            }
        } catch (IOException e) {
            log.error(StrUtil.format("导入文章失败, 堆栈:{}", ExceptionUtil.stacktraceToString(e)));
            throw new BizException("导入文章失败");
        }
        //读完之后直接保存
        // 保存文章
        ArticleVO articleVO = ArticleVO.builder()
                .articleTitle(articleTitle)
                .articleContent(articleContent.toString())
                .status(DRAFT.getStatus())
                .build();
        articleService.saveOrUpdateArticle(articleVO);
    }
}
