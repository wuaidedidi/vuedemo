package com.minzheng.blog.strategy.context;

import com.minzheng.blog.enums.MarkdownTypeEnum;
import com.minzheng.blog.strategy.ArticleImportStrategy;
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
       //用map装载 key和策略  根据前端选择上传类型  给予相对应的策略实现类
        //获取ArticleImportStrategy实现类,并使用导入articles方法
        String demo=MarkdownTypeEnum.getMarkdownType(type);
        System.out.println(demo);
        articleImportStrategyMap.get(MarkdownTypeEnum.getMarkdownType(type)).importArticles(file);
    }
}
