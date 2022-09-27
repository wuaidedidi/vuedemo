import axios from "axios";
import Layout from "@/layout/index.vue";
import router from "@/router";
import store from "@/store";

export function generaMenu() {
  axios.get("/api/admin/menu/user").then(({ data }) => {
    let userMenuList = data.data;
    userMenuList.forEach((item) => {
      if (item.icon != null) {
        item.icon = "iconfont " + item.icon;
      }
      if (item.component == "Layout") {
        item.component = Layout;
      }
      if (item.children && item.children.length > 0) {
        item.children.forEach((route) => {
          route.icon = "iconfont " + route.icon;
          route.component = loadView(route.component);
        });
      }
    });
    store.commit("saveUserMenuList", userMenuList);
    router.addRoutes(userMenuList);
  });
}
export const loadView = (view) => {
  // 路由懒加载
  return (resolve) => require([`@/views${view}`], resolve);
};
