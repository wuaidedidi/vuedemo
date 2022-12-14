package com.minzheng.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Markdown文章类型枚举
 *
 * @author linweiyuan
 * @date 2022/07/28
 */
@Getter
@AllArgsConstructor
public enum MarkdownTypeEnum {
    /**
     * 普通文章
     */
    NORMAL("", "normalArticleImportStrategyImpl"),
    /**
     * Hexo文章
     */
    HEXO("hexo", "hexoArticleImportStrategyImpl");

    /**
     * 类型
     */
    private final String type;

    /**
     * 策略
     */
    private final String strategy;

    /**
     * 判断markdown上传的类型
     * @param name
     * @return
     */
    public static String getMarkdownType(String name) {
        //如果没有指定类型，直接返回普通实现
        if (name == null) {
            return NORMAL.getStrategy();
        }
        for (MarkdownTypeEnum value : MarkdownTypeEnum.values()) {
            if (value.getType().equalsIgnoreCase(name)) {
                return value.getStrategy();
            }
        }
        return null;
    }
}
