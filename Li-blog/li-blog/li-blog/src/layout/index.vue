<template>
  <div>
    <el-container class="el-panel">
      <el-aside width="auto">
        <SideBar />
      </el-aside>
      <el-container
        :class="
          this.$store.state.isAsideScoller
            ? 'main-container noscoller'
            : 'main-container scoller'
        "
      >
        <el-header class="header-panel">
          <NavBar :key="$route.fullPath" />
        </el-header>
        <el-main style="background: #f7f9fb">
          <div class="fade-transform-box">
            <transition name="fade-transform" mode="out-in">
              Main<router-view :key="$route.fullPath" />
            </transition>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import SideBar from "@/layout/components/SideBar";
import NavBar from "@/layout/components/NavBar";
export default {
  components: { NavBar, SideBar },
};
</script>

<style scoped>
.header-panel {
  color: #333;
  padding: 0px !important;
  height: 84px !important;
}
.el-aside {
  background-color: white !important;
}

.el-main {
  background-color: #e9eef3;
}
.scoller {
  margin-left: 210px;
}
.noscoller {
  margin-left: 64px;
}
.main-container {
  transition: margin-left 0.45s;
  min-height: 100vh;
}
/*转场动画的时间*/
.fade-transform-enter-active,
.fade-transform-leave-active {
  transition: all 0.5s ease 0s;
}
/*加载路由页面时候的闪缩现象*/
.fade-transform-enter {
  opacity: 0;
  transform: translateX(-30px);
}
.fade-transform-leave-to {
  opacity: 0;
  transform: translateX(30px);
}
.fade-transform-box {
  position: relative;
  top: 0px;
  bottom: 0px;
  width: 100%;
  overflow: hidden;
}
</style>
