<template>
  <div class="main-card">
    <div class="title">{{ this.$route.name }}</div>
    <div class="top-status-panel">
      <span class="status-span">状态</span>
      <span>全部</span>
      <span>公开</span>
      <span>私密</span>
      <span>草稿箱</span>
      <span>回收站</span>
    </div>
    <div class="navbar-panel">
      <el-button disabled type="danger" size="small" icon="el-icon-delete">
        批量删除
      </el-button>
      <el-button
        disabled
        type="success"
        size="small"
        style="margin-right: 1rem"
        icon="el-icon-upload"
      >
        批量导出
      </el-button>
      <el-dropdown>
        <el-button type="primary" size="small" icon="el-icon-upload">
          批量导入
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>
            <el-upload
              action="/api/admin/articles/import"
              multiple
              :limit="9"
              :show-file-list="false"
              :on-success="uploadArticle"
            >
              普通文章
            </el-upload>
          </el-dropdown-item>
          <el-dropdown-item
            ><el-upload
              action="/api/admin/articles/import?type=hexo"
              multiple
              :limit="9"
              :show-file-list="false"
              :on-success="uploadArticle"
            >
              Hexo文章
            </el-upload></el-dropdown-item
          >
        </el-dropdown-menu>
      </el-dropdown>
      <div class="select-panel">
        <el-select
          clearable
          v-model="type"
          placeholder="请选择文章类型"
          size="small"
        >
          <el-option
            v-for="item in typeList"
            :key="item.code"
            :label="item.name"
            :value="item.name"
          >
          </el-option>
        </el-select>
        <el-select
          v-model="categoryId"
          clearable
          filterable
          placeholder="请选择"
          size="small"
        >
          <el-option
            v-for="item in this.categoryList"
            :key="item.id"
            :label="item.categoryName"
            :value="item.categoryName"
          >
          </el-option>
        </el-select>
        <el-select
          v-model="tagId"
          clearable
          filterable
          placeholder="请选择"
          size="small"
        >
          <el-option
            v-for="item in tagList"
            :key="item.id"
            :label="item.tagName"
            :value="item.tagName"
          >
          </el-option>
        </el-select>

        <el-input
          clearable
          placeholder="请输入文章名"
          prefix-icon="el-icon-search"
          v-model="keywords"
          size="small"
          @keyup.enter.native="searchArticles"
        >
        </el-input>
        <el-button
          type="primary"
          @click="onSubmit"
          size="small"
          icon="el-icon-search"
          >搜索</el-button
        >
      </div>
    </div>
    <div class="table-panel">
      <el-table
        :data="articleList"
        style="width: 100%"
        border
        stripe
        ref="multipleTable"
        tooltip-effect="dark"
        @selection-change="handleSelectionChange"
      >
        <el-table-column
          type="selection"
          fixed
          header-align="center"
          align="center"
          width="50 "
        >
        </el-table-column>
        <el-table-column
          prop="articleCover"
          header-align="center"
          align="center"
          label="文章封面"
          width="200"
        >
          <template slot-scope="scope">
            <el-image
              class="article-cover"
              :src="
                scope.row.articleCover
                  ? scope.row.articleCover
                  : 'https://static.talkxj.com/articles/c5cc2b2561bd0e3060a500198a4ad37d.png'
              "
            >
            </el-image>
            <i
              v-if="scope.row.status == 1"
              class="iconfont el-icon-mygongkai article-status-icon"
            />
            <i
              v-if="scope.row.status == 2"
              class="iconfont el-icon-mymima article-status-icon"
            />
            <i
              v-if="scope.row.status == 3"
              class="iconfont el-icon-mycaogaoxiang article-status-icon"
            />
          </template>
        </el-table-column>
        <el-table-column
          prop="articleTitle"
          header-align="center"
          align="center"
          label="标题"
          width="120"
        >
        </el-table-column>
        <el-table-column
          prop="categoryName"
          header-align="center"
          align="center"
          label="分类"
          width="120"
        >
        </el-table-column>
        <el-table-column
          prop="tagDTOList"
          label="标签"
          header-align="center"
          align="center"
          width="120"
        >
          <template slot-scope="scope">
            <el-tag
              v-for="item of scope.row.tagDTOList"
              :key="item.tagId"
              style="margin-right: 0.2rem; margin-top: 0.2rem"
            >
              {{ item.tagName }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          prop="viewsCount"
          label="浏览量"
          header-align="center"
          align="center"
          width="50"
        >
          <template slot-scope="scope">
            <span v-if="scope.row.viewsCount">
              {{ scope.row.viewsCount }}
            </span>
            <span v-else>0</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="likeCount"
          label="点赞量"
          header-align="center"
          align="center"
          width="50"
        >
          <template slot-scope="scope">
            <span v-if="scope.row.likeCount">{{ scope.row.likeCount }}</span>
            <span v-else>0</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="发表时间"
          header-align="center"
          align="center"
          width="150"
        >
          <template slot-scope="scope">
            <i class="el-icon-time" style="margin-right: 5px" />
            {{ $moment(scope.row.createTime).format("YYYY-MM-DD") || date }}
          </template>
        </el-table-column>
        <el-table-column
          prop="status"
          label="类型"
          header-align="center"
          align="center"
          width="80"
        >
          <template slot-scope="scope">
            <el-tag :type="articleType(scope.row.type).tagType">
              {{ articleType(scope.row.type).name }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          prop="isTop"
          label="置顶"
          header-align="center"
          align="center"
          width="50"
        >
        </el-table-column>
        <!-- 列操作 -->
        <el-table-column label="操作" align="center" width="150">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="mini"
              @click="editArticle(scope.row.id)"
              v-if="scope.row.isDelete == 0"
            >
              编辑
            </el-button>
            <el-popconfirm
              title="确定删除吗？"
              style="margin-left: 10px"
              @confirm="updateArticleDelete(scope.row.id)"
              v-if="scope.row.isDelete == 0"
            >
              <el-button size="mini" type="danger" slot="reference">
                删除
              </el-button>
            </el-popconfirm>
            <el-popconfirm
              title="确定恢复吗？"
              v-if="scope.row.isDelete == 1"
              @confirm="updateArticleDelete(scope.row.id)"
            >
              <el-button size="mini" type="success" slot="reference">
                恢复
              </el-button>
            </el-popconfirm>
            <el-popconfirm
              style="margin-left: 10px"
              v-if="scope.row.isDelete == 1"
              title="确定彻底删除吗？"
              @confirm="deleteArticles(scope.row.id)"
            >
              <el-button size="mini" type="danger" slot="reference">
                删除
              </el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <el-pagination
        class="pagination-container"
        background
        @size-change="sizeChange"
        @current-change="currentChange"
        :current-page="current"
        :page-size="size"
        :total="count"
        :page-sizes="[10, 20]"
        layout="total, sizes, prev, pager, next, jumper"
      />
    </div>
  </div>
</template>

<script>
export default {
  created() {
    this.listArticles();
    this.listCategories();
    this.listTags();
  },
  data() {
    return {
      loading: true,
      count: 0,
      current: 1,
      size: 10,
      keywords: null,
      categoryId: null,
      status: null,
      tagId: null,
      type: null,
      isDelete: 0,
      articleList: [],
      articleIdList: [],
      categoryList: [],
      tagList: [],
      formInline: {
        user: "222",
        region: "222",
      },
      input2: "",
      tableData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          province: "上海",
          city: "普陀区",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333,
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          province: "上海",
          city: "普陀区",
          address: "上海市普陀区金沙江路 1517 弄",
          zip: 200333,
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          province: "上海",
          city: "普陀区",
          address: "上海市普陀区金沙江路 1519 弄",
          zip: 200333,
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          province: "上海",
          city: "普陀区",
          address: "上海市普陀区金沙江路 1516 弄",
          zip: 200333,
        },
      ],
      typeList: [
        {
          name: "原创",
          code: "1",
        },
        {
          name: "转载",
          code: "2",
        },
        {
          name: "翻译",
          code: "3",
        },
      ],
      value: "",
    };
  },
  computed: {
    articleType() {
      return function (status) {
        let name = "";
        let tagType = "";
        switch (status) {
          case 1:
            tagType = "danger";
            name = "原创";
            break;
          case 2:
            tagType = "success";
            name = "转载";
            break;
          case 3:
            tagType = "primary";
            name = "翻译";
            break;
        }
        return {
          name: name,
          tagType: tagType,
        };
      };
    },
  },
  methods: {
    sizeChange(size) {
      this.size = size;
      this.listArticles();
    },
    currentChange(current) {
      this.current = current;
      this.listArticles();
    },
    searchArticles() {
      this.current = 1;
      this.listArticles();
    },
    updateArticleDelete(id) {
      let param = {};
      if (id != null) {
        param.idList = [id];
      } else {
        param.idList = this.articleIdList;
      }
      param.isDelete = this.isDelete == 0 ? 1 : 0;
      this.axios.put("/api/admin/articles", param).then(({ data }) => {
        if (data.flag) {
          this.$notify.success({
            title: "成功",
            message: data.message,
          });
          this.listArticles();
        } else {
          this.$notify.error({
            title: "失败",
            message: data.message,
          });
        }
        this.updateIsDelete = false;
      });
    },
    editArticle(id) {
      this.$router.push({ path: "/articles/" + id });
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    onSubmit() {
      console.log("submit!");
    },
    uploadArticle(data) {
      if (data.flag) {
        this.$notify.success({
          title: "成功",
          message: "导入成功",
        });
        this.listArticles();
      } else {
        this.$notify.error({
          title: "失败",
          message: data.message,
        });
      }
    },
    listArticles() {
      this.axios
        .get("/api/admin/articles", {
          params: {
            current: this.current,
            size: this.size,
            keywords: this.keywords,
            categoryId: this.categoryId,
            status: this.status,
            tagId: this.tagId,
            type: this.type,
            isDelete: this.isDelete,
          },
        })
        .then(({ data }) => {
          this.articleList = data.data.recordList;

          this.count = data.data.count;
          this.loading = false;
        });
    },
    listCategories() {
      this.axios.get("/api/admin/categories/search").then(({ data }) => {
        console.log(data.data);
        this.categoryList = data.data;
      });
    },
    listTags() {
      this.axios.get("/api/admin/tags/search").then(({ data }) => {
        this.tagList = data.data;
      });
    },
  },
};
</script>
<style scoped>
.select-panel .el-select {
  margin-right: 10px;
  width: 10rem;
}
.select-panel .el-input {
  margin-right: 10px;
  width: 10rem;
}

.select-panel {
  flex: 1;
  display: flex;
  justify-content: right;
}
.navbar-panel > el-button {
}
.navbar-panel {
  margin-top: 1.5rem;
  height: 4rem;
  display: flex;
  align-items: center;
}
.top-status-panel {
  height: 18px;
}
.top-status-panel > span {
  margin-right: 24px;
  font-size: 14px;
  margin-top: 40px;
  color: #999;
}
.top-status-panel > span:not(.status-span) {
  cursor: pointer;
}
.status-span:hover {
  cursor: none;
}
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.article-cover {
  position: relative;
  width: 100%;
  height: 90px;
  border-radius: 4px;
}
.article-status-icon {
  color: #fff;
  font-size: 1.5rem;
  position: absolute;
  right: 1rem;
  bottom: 1.4rem;
}
</style>
