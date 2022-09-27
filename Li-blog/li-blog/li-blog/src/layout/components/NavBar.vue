<template>
  <div>
    <div class="nav-bar">
      <div class="el-icon-s-fold" @click="clickscoller()"></div>
      <el-breadcrumb separator="/">
        <template></template>
        <el-breadcrumb-item
          :to="{ path: item.path }"
          v-for="item of breadcrumbList"
          :key="item.path"
          >{{ item.name }}</el-breadcrumb-item
        >
      </el-breadcrumb>
      <div class="right-menu">
        <!-- 全屏按钮 -->
        <div class="screen-full" @click="fullScreen">
          <i class="el-icon-full-screen" />
        </div>
        <div class="drop-panel">
          <el-dropdown @command="handleCommand">
            <span class="el-dropdown-link">
              <el-avatar
                fit="fill"
                :size="40"
                class="user-avatar"
                src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
              ></el-avatar>
              <i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="userinfo">
                <i class="el-icon-s-custom" />个人中心</el-dropdown-item
              >
              <el-dropdown-item command="logout">
                <i
                  class="iconfont el-icon-mytuichu"
                />退出登录</el-dropdown-item
              >
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>

    <div class="spanlist-panel">
      <div class="spanlist">
        <span
          v-for="tab of this.$store.state.tabList"
          :class="isActive(tab)"
          :key="tab.path"
          @click="clickTab(tab)"
          >{{ tab.name }}
          <li class="el-icon-close" @click="closetab(tab)"></li>
        </span>
        <span class="allclose" @click="closeAll()"> 全部关闭</span>
      </div>
    </div>
  </div>
</template>

<script>
import { resetRouter } from "../../router";
export default {
  //每一次路由变更相当于重新创建了一次，所以只要路由变更这里定义的方法会执行
  created() {
    let matched = this.$route.matched.filter((item) => item.name);
    const first = matched[0];
    if (first && first.name !== "首页") {
      matched = [{ path: "/", name: "首页" }].concat(matched);
    }
    this.breadcrumbList = matched;
    //保存标签
    this.$store.commit("saveTab", this.$route);
  },
  data: function () {
    return {
      breadcrumbList: [],
    };
  },
  computed: {
    //标签是否处于当前页
    isActive() {
      return function (tab) {
        if (tab.path === this.$route.path) {
          return "tabs-view-item active";
        }
        return "tabs-view-item";
      };
    },
  },

  methods: {
    clickscoller() {
      this.$store.commit("changescoller");
    },
    handleCommand(command) {
      if (command === "logout") {
        // 调用注销接口
        this.axios.post("/api/logout");
        // 清空用户信息
        this.$store.commit("logout");
        this.$store.commit("resetTab");
        // 清空用户菜单
        resetRouter();
        if (this.$route.path === command) {
          this.$router.push(command);
        }
        this.$router.push({ path: "/login" });
      }
    },
    fullScreen() {
      let element = document.documentElement;
      if (this.fullscreen) {
        if (document.exitFullscreen) {
          document.exitFullscreen();
        } else if (document.webkitCancelFullScreen) {
          document.webkitCancelFullScreen();
        } else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen();
        } else if (document.msExitFullscreen) {
          document.msExitFullscreen();
        }
      } else {
        if (element.requestFullscreen) {
          element.requestFullscreen();
        } else if (element.webkitRequestFullScreen) {
          element.webkitRequestFullScreen();
        } else if (element.mozRequestFullScreen) {
          element.mozRequestFullScreen();
        } else if (element.msRequestFullscreen) {
          element.msRequestFullscreen();
        }
      }
      this.fullscreen = !this.fullscreen;
    },
    clickTab(tab) {
      this.$router.push(tab.path);
    },
    closetab(tab) {
      //保存标签
      this.$store.commit("deleteTab", tab);
      if (tab.path === this.$route.path) {
        let tabList = this.$store.state.tabList;
        this.$router.push(tabList[tabList.length - 1].path);
      }
    },
    closeAll() {
      this.$store.commit("closeAllTab", this.$router);
    },
  },
};
</script>

<style scoped>
.el-icon-close:hover {
  background: #ec8e8e;
  border-radius: 50%;
}
.allclose {
  position: absolute;
  right: 2px;
  display: inline-block;
  cursor: pointer;
  height: 25px;
  line-height: 25px;
  border: 1px solid #d8dce5;
  color: #495060;
  background: #fff;
  padding: 0 8px;
  font-size: 12px;
  margin-top: 4px;
  margin-left: 5px;
}
.active:before {
  content: "";
  background: #fff;
  display: inline-block;
  width: 8px;
  height: 8px;
  border-radius: 50%;
  position: relative;
  margin-right: 2px;
}
.active {
  background: #42b983 !important;
  color: white !important;
}

.tabs-view-item {
  display: inline-block;
  cursor: pointer;
  height: 25px;
  line-height: 25px;
  border: 1px solid #d8dce5;
  color: #495060;
  background: #fff;
  padding: 0 8px;
  font-size: 12px;
  margin-top: 4px;
  margin-left: 5px;
}
.right-menu {
  display: flex;
  margin-left: auto;
  align-items: center;
}
.spanlist-panel {
}
.spanlist {
  overflow-x: auto;
  white-space: nowrap;
  display: flex;
  align-items: center;
  font-size: 0.8rem;
  height: 34px;
  border-bottom: 1px solid #d8dce5;
  box-shadow: 0 1px 3px 0 rgb(0 0 0 / 12%), 0 0 3px 0 rgb(0 0 0 / 4%);
}
.drop-panel {
  display: inline-block;
}
.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}
.screen-full {
  display: inline-block;
  cursor: pointer;
  font-size: 1.25rem;
}
.user-avatar {
}

.el-icon-arrow-down {
  font-size: 0.75rem;
}

.el-icon-s-fold {
  cursor: pointer;
  margin-right: 20px;
}
.nav-bar {
  padding: 0px 30px 0px 15px;
  display: flex;
  height: 50px;
  align-items: center;
}

*::-webkit-scrollbar {
  width: 0.5rem;
  height: 6px;
}
*::-webkit-scrollbar-thumb {
  border-radius: 0.5rem;
  background-color: rgba(144, 147, 153, 0.3);
}
</style>
