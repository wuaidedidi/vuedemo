package com.lichen.lichenblog.admin.strategy.context;

import com.lichen.lichenblog.admin.enums.MarkdownTypeEnum;
import com.lichen.lichenblog.admin.strategy.ArticleImportStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 文章导入策略上下文
 *
 * @author linweiyuan
 * @date 2022/07/28
 */
@Service
public class ArticleImportStrategyContext {
    @Autowired
    private Map<String, ArticleImportStrategy> articleImportStrategyMap;

    public void importArticles(MultipartFile file, String type) {
        articleImportStrategyMap.get(MarkdownTypeEnum.getMarkdownType(type)).importArticles(file);
    }
}