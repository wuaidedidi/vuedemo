<template>
  <div class="main-card">
    <el-dialog
      title="发布文章"
      :visible.sync="dialogVisible"
      :before-close="handleClose"
      center
    >
      <el-form v-model="article.categoryName">
        <div class="popoverdiv">
          <strong style="margin-right: 10px">文章分类</strong>

          <el-tag
            type="success"
            closable
            @close="tagCategoryClose"
            v-if="article.categoryName !== ''"
            >{{ article.categoryName }}</el-tag
          >
          <el-popover
            placement="bottom-start"
            title="分类"
            trigger="click"
            v-if="showPopover"
          >
            <el-select
              style="width: 400px"
              v-model="article.categoryName"
              allow-create
              default-first-option
              placeholder="请输入分类名选择，enter可添加自定义分类"
              @change="selectCategory"
              filterable
            >
              <el-option
                v-for="category in this.categoryList"
                :key="category.id"
                :label="category.categoryName"
                :value="category.categoryName"
              >
              </el-option>
            </el-select>
            <el-button
              slot="reference"
              class="el-button--success el-button--small is-plain el-popover__reference"
              >添加分类</el-button
            >
          </el-popover>
        </div>

        <div class="popoverdiv">
          <strong style="margin-right: 10px">文章标签</strong>
          <el-tag
            v-model="article.tagNameList"
            type="primary"
            v-for="tagName in article.tagNameList"
            closable
            @close="blogtagClose(tagName)"
            :key="tagName"
            style="margin-right: 10px"
            >{{ tagName }}</el-tag
          >

          <el-popover
            placement="bottom-start"
            title="标签"
            trigger="click"
            v-if="showTagPopover"
          >
            <el-select
              style="width: 400px"
              v-model="article.tagNameList"
              multiple
              filterable
              :multiple-limit="3"
              allow-create
              placeholder="请输入标签名选择，enter可添加自定义分类"
              ref="el_tag_select"
              @keyup.enter.native="pushtag"
            >
              <el-option
                v-for="item in tagList"
                :key="item.id"
                :label="item.tagName"
                :value="item.tagName"
              >
              </el-option>
            </el-select>
            <el-button
              slot="reference"
              class="el-button--primary el-button--small is-plain el-popover__reference"
            >
              添加标签
            </el-button>
          </el-popover>
        </div>

        <div class="popoverdiv">
          <strong style="margin-right: 10px">文章类型</strong>
          <el-select v-model="article.type" placeholder="请选择类型">
            <el-option
              v-for="item in typeList"
              :key="item.type"
              :label="item.desc"
              :value="item.type"
            />
          </el-select>
        </div>
        <!-- 文章类型 -->
        <div class="popoverdiv" v-if="article.type !== 1">
          <strong style="margin-right: 10px">原文地址</strong>
          <div style="display: inline-block; width: 70%">
            <el-input
              size="medium"
              v-model="article.originalUrl"
              placeholder="请填写原文链接"
            />
          </div>
        </div>

        <div class="popoverdiv">
          <div style="display: flex; align-items: start">
            <strong style="margin-right: 10px">上传封面</strong>
            <el-upload
              class="upload-cover"
              drag
              action="/api/admin/articles/images"
              multiple
              :before-upload="beforeUpload"
              :on-success="uploadCover"
            >
              <i class="el-icon-upload" v-if="article.articleCover == ''" />
              <div class="el-upload__text" v-if="article.articleCover == ''">
                将文件拖到此处，或<em>点击上传</em>
              </div>
              <img
                v-else
                :src="article.articleCover"
                width="360px"
                height="180px"
              />
            </el-upload>
          </div>
        </div>

        <div class="popoverdiv">
          <strong style="margin-right: 10px">&nbsp &nbsp置顶&nbsp&nbsp</strong>
          <el-switch
            v-model="article.isTop"
            active-color="#13ce66"
            inactive-color="#ff4949"
            :active-value="1"
            :inactive-value="0"
          >
          </el-switch>
        </div>

        <div class="popoverdiv">
          <strong style="margin-right: 10px">发布形式</strong>

          <el-radio-group v-model="article.status">
            <el-radio :label="1">公开</el-radio>
            <el-radio :label="2">私密</el-radio>
          </el-radio-group>
        </div>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdateArticle">发表</el-button>
      </span>
    </el-dialog>
    <div class="title">{{ this.$route.name }}</div>

    <div class="aritcles-panel-main">
      <div class="articles-title">
        <el-input
          v-model="article.articleTitle"
          placeholder="请输入文章标题"
          style="height: 34px !important"
        ></el-input>
        <el-button type="danger" class="sendbutton" @click="saveSimpleblog"
          >保存草稿</el-button
        >
        <el-button type="danger" @click="sendblog">发布文章</el-button>
      </div>
      <mavon-editor
        v-model="article.articleContent"
        ref="md"
        @change="change"
        @imgAdd="imgAdd"
        style="height: calc(100vh - 260px)"
      />
    </div>
  </div>
</template>

<script>
import * as imageConversion from "image-conversion";
export default {
  created() {
    let path = this.$route.path;
    let arr = path.split("/");
    let articleId = arr[2];
    if (articleId) {
      this.axios.get("api/admin/articles/" + articleId).then(({ data }) => {
        this.article = data.data;
      });
    } else {
      let article = sessionStorage.getItem("article");
      //获取到string obj对象
      if (article) {
        this.article = JSON.parse(article);
      }
    }
  },
  destroyed() {
    this.autoSaveArticle();
  },
  computed: {
    showTagPopover: {
      get() {
        return this.article.tagNameList.length < 3;
      },
      // eslint-disable-next-line @typescript-eslint/no-empty-function
      set() {},
    },
    showPopover: {
      get() {
        return this.article.categoryName === "";
      },
      // eslint-disable-next-line @typescript-eslint/no-empty-function
      set() {},
    },
  },
  data() {
    return {
      categoryList: [],
      tagList: [],
      addOrEdit: false,
      radio: "1",
      typeList: [
        {
          type: 1,
          desc: "原创",
        },
        {
          type: 2,
          desc: "转载",
        },
        {
          type: 3,
          desc: "翻译",
        },
      ],
      configs: {},
      autoSave: true,
      // showTagPopover: true,
      formLabelWidth: "120px",
      dialogVisible: false,

      value: "",

      options: [
        {
          value: "选项1",
          label: "黄金糕",
        },
        {
          value: "选项2",
          label: "双皮奶",
        },
        {
          value: "选项3",
          label: "蚵仔煎",
        },
        {
          value: "选项4",
          label: "龙须面",
        },
        {
          value: "选项5",
          label: "北京烤鸭",
        },
      ],

      article: {
        id: null,
        articleTitle: this.$moment(new Date()).format("YYYY-MM-DD"),
        articleContent: "",
        articleCover: "",
        categoryName: "",
        tagNameList: [],
        originalUrl: "",
        isTop: 0,
        type: 1,
        status: 1,
      },
    };
  },
  methods: {
    saveOrUpdateArticle() {
      if (this.article.articleTitle.trim() == "") {
        this.$message.error("文章标题不能为空");
        return false;
      }
      if (this.article.articleContent.trim() == "") {
        this.$message.error("文章内容不能为空");
        return false;
      }
      if (this.article.categoryName == null) {
        this.$message.error("文章分类不能为空");
        return false;
      }
      if (this.article.tagNameList.length == 0) {
        this.$message.error("文章标签不能为空");
        return false;
      }
      if (this.article.articleCover.trim() == "") {
        this.$message.error("文章封面不能为空");
        return false;
      }
      this.axios.post("/api/admin/articles", this.article).then(({ data }) => {
        if (data.flag) {
          if (this.article.id === null) {
            this.$store.commit("removeTab", "发布文章");
          } else {
            this.$store.commit("removeTab", "修改文章");
          }
          sessionStorage.removeItem("article");
          this.$router.push({ path: "/article-list" });
          this.$notify.success({
            title: "成功",
            message: data.message,
          });
        } else {
          this.$notify.error({
            title: "失败",
            message: data.message,
          });
        }
        this.addOrEdit = false;
      });
      //关闭自动保存功能
      this.autoSave = false;
      this.dialogVisible = false;
    },
    autoSaveArticle() {
      if (
        this.autoSave &&
        this.article.articleTitle.trim() !== "" &&
        this.article.articleContent.trim() !== "" &&
        this.article.id !== null
      ) {
        this.axios
          .post("/api/admin/articles", this.article)
          .then(({ data }) => {
            if (data.flag) {
              this.$notify.success({
                title: "成功",
                message: "自动保存成功",
              });
            } else {
              this.$notify.error({
                title: "失败",
                message: "自动保存失败",
              });
            }
          });
      }

      if (this.autoSave && this.article.id == null) {
        console.log(this.article);
        //保存本地对象
        sessionStorage.setItem("article", JSON.stringify(this.article));
      }
    },
    pushtag(event) {
      if (event.target.value === "") {
        this.$message.error("自定义标签名不能为空");
        return false;
      }
      this.article.tagNameList.push(event.target.value);
    },
    tagCategoryClose() {
      this.article.categoryName = "";
    },
    blogtagClose(closeTag) {
      this.article.tagNameList.splice(
        this.article.tagNameList.indexOf(closeTag),
        1
      );
    },
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    selectCategory(event) {},

    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {
          console.log("关闭异常");
        });
    },
    // 将图片上传到服务器，返回地址替换到md中
    imgAdd(pos, file) {
      let formdata = new FormData();
      formdata.append("image", file);
      //访问后台服务器方法

      this.axios
        .post("/api/admin/articles/images", formdata)
        .then(({ data }) => {
          if (data.code === 200) {
            this.$refs.md.$img2Url(pos, data.data.url);
          } else {
            this.$message.error(data.message);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    change(value, render) {
      this.html = render;
    },
    /**-----------------------------发布文章按钮相关方法------------------------------**/

    sendblog() {
      if (this.article.articleTitle.trim() == "") {
        this.$message.error("标题不能为空");
        return false;
      }

      if (this.article.articleContent.trim() == "") {
        this.$message.error("博客内容不能为空");
        return false;
      }

      this.listCategories();
      this.listTags();
      this.addOrEdit = true;
      this.dialogVisible = true;
    },
    listCategories() {
      this.axios.get("/api/admin/categories/search").then(({ data }) => {
        this.categoryList = data.data;
      });
    },
    listTags() {
      this.axios.get("/api/admin/tags/search").then(({ data }) => {
        this.tagList = data.data;
      });
    },
    /**-----------------------------------------------------------**/
    saveSimpleblog() {
      if (this.article.articleTitle.trim() == "") {
        this.$message.error("标题不能为空");
        return false;
      }

      if (this.article.articleContent.trim() == "") {
        this.$message.error("博客内容不能为空");
        return false;
      }
      this.article.status = 3;
      this.axios.post("api/admin/articles", this.article).then(({ data }) => {
        if (data.flag) {
          if (this.article.id === null) {
            this.$store.commit("deleteTab", "发布文章");
          } else {
            this.$store.commit("deleteTab", "修改文章");
          }
          //删除自动保存的数据 跳转到文章列表
          sessionStorage.removeItem("articles");
          this.$router.push({ path: "/article-list" });
          this.$notify.success({ title: "成功", message: "保存草稿成功" });
        } else {
          this.$notify.error({ title: "失败", message: "保存草稿失败" });
        }
      });
    },
    beforeUpload(file) {
      var formData = new FormData();
      formData.append("image", file);
      this.axios
        .post("api/admin/articles/images", formData)
        .then(({ data }) => {
          this.article.articleCover = data.data.url;
        });
      return new Promise((resolve) => {
        if (file.size / 1024 < this.config.UPLOAD_SIZE) {
          resolve(file);
        }
        // 压缩到200KB,这里的200就是要压缩的大小,可自定义
        imageConversion
          .compressAccurately(file, this.config.UPLOAD_SIZE)
          .then((res) => {
            resolve(res);
          });
      });
    },
    uploadCover(response) {
      this.article.articleCover = response.data;
    },
  },
};
</script>

<style scoped>
upload-demo. {
  display: inline-block;
}
.popoverdiv {
  margin-bottom: 2rem;
}
.aritcles-panel-main {
  width: calc(100% - 40px);
  margin: 0 auto;
}
.articles-title {
  margin-bottom: 18px;
  display: flex;
  align-items: center;
}
.articles-title button {
  padding: 10px 20px 10px 20px;
  height: 36px;
}
.sendbutton {
  margin-left: 0.75rem;
  background: #fff;
  color: #f56c6c;
}
>>> .el-input__inner {
  height: 36px;
}
</style>
