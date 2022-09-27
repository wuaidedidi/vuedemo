package com.lichen.lichenblog.admin.controller;


import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONObject;
import com.lichen.lichenblog.admin.annotation.OptLog;

import com.lichen.lichenblog.admin.service.IArticleService;
import com.lichen.lichenblog.admin.strategy.context.ArticleImportStrategyContext;
import com.lichen.lichenblog.admin.strategy.context.UploadStrategyContext;
import com.lichen.lichenblog.admin.util.DabaituResult;
import com.lichen.lichenblog.admin.vo.ArticleVO;
import com.lichen.lichenblog.admin.vo.ConditionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static com.lichen.lichenblog.admin.constant.OptTypeConst.SAVE_OR_UPDATE;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lichen
 * @since 2022-09-13
 */

@RestController
@RequestMapping("/admin/articles")
public class ArticleController extends BaseController {
    @Value("${upload.path}")
    private String baseFolderPath;
    @Value("${upload.url}")
    private String baseUrlPath;
    @Autowired
    private UploadStrategyContext uploadStrategyContext;
    @Autowired
    IArticleService articleService;
    @Autowired
    private ArticleImportStrategyContext articleImportStrategyContext;
    // /**
    //  * 上传文章图片
    //  *
    //  * @param file 文件
    //  * @return {@link DabaituResult<String>} 文章图片地址
    //  */
    // @ApiOperation(value = "上传文章图片")
    // @ApiImplicitParam(name = "file", value = "文章图片", required = true, dataType = "MultipartFile")
    // @PostMapping("/images")
    // public DabaituResult saveArticleImages(MultipartFile file) {
    //     System.out.println();
    //     return new DabaituResult().ok(uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.ARTICLE.getPath()));
    // }


    /**
     * 添加或修改文章
     *
     * @param articleVO 文章信息
     * @return {@link DabaituResult<>}
     */
    @OptLog(optType = SAVE_OR_UPDATE)
    @ApiOperation(value = "添加或修改文章")
    @PostMapping("")
    public DabaituResult saveOrUpdateArticle(@Valid @RequestBody ArticleVO articleVO) {
        articleService.saveOrUpdateArticle(articleVO);
        return new DabaituResult().ok();
    }

    /**
     * 查看后台文章
     * @param condition
     * @return {@link DabaituResult} 后台文章列表
     */
    @GetMapping("")
    @ApiOperation(value = "查看后台文章")
    public DabaituResult listArticleBacks(ConditionVO condition){
        Object arrlist=  articleService.listArticleBacks(condition);
        System.out.println(arrlist);
        return new DabaituResult().ok(articleService.listArticleBacks(condition));
    }
    /**
     * 导入文章
     *
     * @param file 文件
     * @param type 文章类型
     * @return {@link Result<>}
     */
    @ApiOperation(value = "导入文章")
    @PostMapping("/import")
    public DabaituResult importArticles(MultipartFile file, @RequestParam(required = false) String type) {
        articleImportStrategyContext.importArticles(file, type);
        return new DabaituResult().ok();
    }
    /**
     * 上传文章图片
     *
     * @param image 文件
     * @return {@link DabaituResult<String>} 文章图片地址
     */
    @ApiOperation(value = "上传文章图片")
    @ApiImplicitParam(name = "file", value = "文章图片", required = true, dataType = "MultipartFile")
    @PostMapping("/images")
    public DabaituResult upload(HttpServletRequest request, MultipartFile image) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String filePath = sdf.format(new Date());

        File baseFolder = new File(baseFolderPath + filePath);
        if (!baseFolder.exists()) {
            baseFolder.mkdirs();
        }

        StringBuffer url = new StringBuffer();
        url.append(request.getScheme())
                .append("://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort())
                .append(request.getContextPath())
                .append(baseUrlPath)
                .append(filePath);

        String imgName = UUID.randomUUID().toString().replace("_", "") + "_" + image.getOriginalFilename().replaceAll(" ", "");
        try {
            File dest = new File(baseFolder, imgName);
            FileCopyUtils.copy(image.getBytes(), dest);
            url.append("/").append(imgName);

            JSONObject object = new JSONObject();
            object.put("url", url);

            return new DabaituResult().ok(object);
        } catch (IOException e) {
            return new DabaituResult().isFail().Message("文件上传错误");
        }
    }
}
