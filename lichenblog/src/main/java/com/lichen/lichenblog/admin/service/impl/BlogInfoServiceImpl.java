package com.lichen.lichenblog.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.lichen.lichenblog.admin.dto.ArticleRankDTO;
import com.lichen.lichenblog.admin.dto.BlogBackInfoDTO;
import com.lichen.lichenblog.admin.dto.BlogHomeInfoDTO;
import com.lichen.lichenblog.admin.entity.WebsiteConfig;
import com.lichen.lichenblog.admin.mapper.WebsiteConfigMapper;
import com.lichen.lichenblog.admin.service.BlogInfoService;
import com.lichen.lichenblog.admin.service.RedisService;
import com.lichen.lichenblog.admin.vo.BlogInfoVO;
import com.lichen.lichenblog.admin.vo.WebsiteConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.lichen.lichenblog.admin.constant.CommonConst.DEFAULT_CONFIG_ID;
import static com.lichen.lichenblog.admin.constant.RedisPrefixConst.WEBSITE_CONFIG;

@Service
public class BlogInfoServiceImpl implements BlogInfoService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private WebsiteConfigMapper websiteConfigDao;

    @Override
    public BlogHomeInfoDTO getBlogHomeInfo() {
        return null;
    }

    @Override
    public BlogBackInfoDTO getBlogBackInfo() {
        return null;
    }

    @Override
    public void updateWebsiteConfig(WebsiteConfigVO websiteConfigVO) {

    }

    @Override
    public WebsiteConfigVO getWebsiteConfig() {
        WebsiteConfigVO websiteConfigVO;
        // 获取缓存数据
        Object websiteConfig = redisService.get(WEBSITE_CONFIG);
        if (Objects.nonNull(websiteConfig)) {
            websiteConfigVO = JSON.parseObject(websiteConfig.toString(), WebsiteConfigVO.class);
        } else {
            // 从数据库中加载
            String config = websiteConfigDao.selectById(DEFAULT_CONFIG_ID).getConfig();
            websiteConfigVO = JSON.parseObject(config, WebsiteConfigVO.class);
            redisService.set(WEBSITE_CONFIG, config);
        }
        return websiteConfigVO;
    }

    @Override
    public String getAbout() {
        return "";
    }

    @Override
    public void updateAbout(BlogInfoVO blogInfoVO) {

    }

    @Override
    public void report() {

    }

    /**
     * 查询文章排行
     *
     * @param articleMap 文章信息
     * @return {@link List <ArticleRankDTO>} 文章排行
     */
    private List<ArticleRankDTO> listArticleRank(Map<Object, Double> articleMap) {
            return null;
    }
}