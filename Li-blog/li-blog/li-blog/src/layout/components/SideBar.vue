<template>
  <!--router启动router default-active 自动高亮当前路径-->

  <el-menu
    :collapse="this.$store.state.isAsideScoller"
    router
    :default-active="this.$route.path"
    class="el-menu-vertical-demo"
    background-color="#304156"
    text-color="#BFCBD9"
    active-text-color="#409EFF"
  >
    <!--循环所有的按钮-->
    <template v-for="route in this.$store.state.userMenuList">
      <!--判断有孩子的按钮-->
      <template v-if="route.name && route.children && !route.hidden">
        <!--有孩子的按钮开始循环-->
        <el-submenu :key="route.path" :index="route.path">
          <!--图片插入到当前级-->
          <template slot="title">
            <i :class="route.icon" />
            <span>{{ route.name }}</span>
          </template>
          <!--循环到第二级按钮 index作为标识不能重复，用来高亮选中路由-->
          <template v-for="(item, index) of route.children">
            <el-menu-item v-if="!item.hidden" :key="index" :index="item.path">
              <!--第二集按钮插入-->
              <i :class="item.icon" />
              <span>{{ item.name }}</span>
            </el-menu-item>
          </template>
        </el-submenu>
      </template>
      <!--没有孩子按钮的处理-->
      <template v-else-if="!route.hidden">
        <el-menu-item :key="route.path" :index="route.path">
          <i :class="route.children[0].icon" />
          <span>{{ route.children[0].name }}</span>
        </el-menu-item>
      </template>
    </template>
  </el-menu>
</template>

<script>
export default {};
</script>

<style scoped>
.el-menu-vertical-demo i {
  margin-right: 1rem;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 210px;
}
.el-menu-vertical-demo {
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  overflow-x: hidden;
  overflow-y: auto;
}

*::-webkit-scrollbar {
  width: 0.5rem;
  height: 1px;
}
*::-webkit-scrollbar-thumb {
  border-radius: 0.5rem;
  background-color: rgba(144, 147, 153, 0.3);
}
</style>
