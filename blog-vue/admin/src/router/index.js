import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/login",
    name: "登录",
    hidden: true,
    component: () => import("../views/login/Login.vue")
  }
];

const createRouter = () =>
  new VueRouter({
    mode: "history",
    routes: routes
  });

const router = createRouter();

export function resetRouter() {
  const newRouter = createRouter();
  //实际中路由会用match进行匹配，所以新的router需要启用新的match
  router.matcher = newRouter.matcher;
}

export default router;
