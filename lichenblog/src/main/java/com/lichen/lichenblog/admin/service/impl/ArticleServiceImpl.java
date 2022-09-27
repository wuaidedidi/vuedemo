package com.lichen.lichenblog.admin.service.impl;

import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import com.lichen.lichenblog.admin.dto.ArticleBackDTO;
import com.lichen.lichenblog.admin.entity.Article;
import com.lichen.lichenblog.admin.entity.ArticleTag;
import com.lichen.lichenblog.admin.entity.Category;
import com.lichen.lichenblog.admin.entity.Tag;
import com.lichen.lichenblog.admin.exception.BizException;
import com.lichen.lichenblog.admin.mapper.ArticleMapper;
import com.lichen.lichenblog.admin.mapper.ArticleTagMapper;
import com.lichen.lichenblog.admin.mapper.CategoryMapper;
import com.lichen.lichenblog.admin.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lichen.lichenblog.admin.util.BeanCopyUtils;
import com.lichen.lichenblog.admin.util.PageUtils;
import com.lichen.lichenblog.admin.util.UserUtils;
import com.lichen.lichenblog.admin.vo.ArticleVO;
import com.lichen.lichenblog.admin.vo.ConditionVO;
import com.lichen.lichenblog.admin.vo.PageResult;
import com.lichen.lichenblog.admin.vo.WebsiteConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.lichen.lichenblog.admin.constant.RedisPrefixConst.ARTICLE_LIKE_COUNT;
import static com.lichen.lichenblog.admin.constant.RedisPrefixConst.ARTICLE_VIEWS_COUNT;
import static com.lichen.lichenblog.admin.enums.ArticleStatusEnum.DRAFT;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lichen
 * @since 2022-09-13
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Autowired
    BlogInfoService blogInfoService;
    @Autowired
    ITagService tagService;
    @Autowired
    IArticleTagService articleTagService;
    @Autowired
    ArticleMapper articleDao;
    @Autowired
    CategoryMapper categoryDao;
    @Autowired
    ArticleTagMapper articleTagDao;
    @Autowired
    private RedisService redisService;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateArticle(ArticleVO articleVO) {
        // 查询博客配置信息
        CompletableFuture<WebsiteConfigVO> webConfig = CompletableFuture.supplyAsync(() -> blogInfoService.getWebsiteConfig());

        // 保存文章分类
        Category category = saveArticleCategory(articleVO);
        // 保存或修改文章
        Article article = BeanCopyUtils.copyObject(articleVO, Article.class);
        if (Objects.nonNull(category)) {
            article.setCategoryId(category.getId());
        }
        // 设定默认文章封面
        if (StrUtil.isBlank(article.getArticleCover())){
            try {
                article.setArticleCover(webConfig.get().getArticleCover());
            } catch (Exception e) {
                throw new BizException("设定默认文章封面失败");
            }
        }
        article.setUserId(UserUtils.getLoginUser().getUserInfoId());
        this.saveOrUpdate(article);
        // 保存文章标签
        saveArticleTag(articleVO, article.getId());
    }

    @Override
    public PageResult<ArticleBackDTO> listArticleBacks(ConditionVO condition) {
        Integer count=articleDao.countArticleBacks(condition);
        if(count==0){
            return new PageResult<>();
        }
        // 查询后台文章
        List<ArticleBackDTO> articleBackDTOList = articleDao.listArticleBacks(PageUtils.getLimitCurrent(), PageUtils.getSize(), condition);
        // 查询文章点赞量和浏览量
        Map<Object, Double> viewsCountMap = redisService.zAllScore(ARTICLE_VIEWS_COUNT);
        Map<String, Object> likeCountMap = redisService.hGetAll(ARTICLE_LIKE_COUNT);
        // 封装点赞量和浏览量
        articleBackDTOList.forEach(item -> {
            Double viewsCount = viewsCountMap.get(item.getId());
            if (Objects.nonNull(viewsCount)) {
                item.setViewsCount(viewsCount.intValue());
            }
            item.setLikeCount((Integer) likeCountMap.get(item.getId().toString()));
        });
        return new PageResult<>(articleBackDTOList, count);

    }

    /**
     * 保存文章分类
     *
     * @param articleVO 文章信息
     * @return {@link Category} 文章分类
     */
    private Category saveArticleCategory(ArticleVO articleVO) {
        // 判断分类是否存在
        Category category = categoryDao.selectOne(new LambdaQueryWrapper<Category>().eq(Category::getCategoryName, articleVO.getCategoryName()));
        if (Objects.isNull(category) && !articleVO.getStatus().equals(DRAFT.getStatus())) {
            category = Category.builder().categoryName(articleVO.getCategoryName()).build();
            categoryDao.insert(category);
        }
        return category;
    }

    /**
     * 保存文章标签
     *
     * @param articleVO 文章信息
     */
    private void saveArticleTag(ArticleVO articleVO, Integer articleId) {
        // 编辑文章则删除文章所有标签
        new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getArticleId, articleVO.getId());
        if (Objects.nonNull(articleVO.getId())) {
            articleTagDao.delete( new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getArticleId, articleVO.getId()));
        }
        // 添加文章标签
        List<String> tagNameList = articleVO.getTagNameList();
        if (CollectionUtils.isNotEmpty(tagNameList)) {
            // 查询已存在的标签
            List<Tag> existTagList = tagService.list(new LambdaQueryWrapper<Tag>().in(Tag::getTagName, tagNameList));
            List<String> existTagNameList = existTagList.stream().map(Tag::getTagName).collect(Collectors.toList());
            List<Integer> existTagIdList = existTagList.stream().map(Tag::getId).collect(Collectors.toList());
            // 对比新增不存在的标签
            tagNameList.removeAll(existTagNameList);
            if (CollectionUtils.isNotEmpty(tagNameList)) {
                List<Tag> tagList = tagNameList.stream().map(item -> Tag.builder().tagName(item).build()).collect(Collectors.toList());
                tagService.saveBatch(tagList);
                List<Integer> tagIdList = tagList.stream().map(Tag::getId).collect(Collectors.toList());
                existTagIdList.addAll(tagIdList);
            }
            // 提取标签id绑定文章
            List<ArticleTag> articleTagList = existTagIdList.stream().map(item -> ArticleTag.builder()
                    .articleId(articleId)
                    .tagId(item)
                    .build())
                    .collect(Collectors.toList());
            articleTagService.saveBatch(articleTagList);
        }
    }
}
