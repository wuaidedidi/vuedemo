import Vue from "vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import store from "./store";
import "./assets/css/index.css";
import "./assets/css/iconfont.css";
import dayjs from "dayjs";
//富文本编辑器
import mavonEditor from "mavon-editor";
import "mavon-editor/dist/css/index.css";

// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-ignore
//加载效果条
import NProgress from "nprogress";
import "nprogress/nprogress.css";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import axios from "axios";
import VueAxios from "vue-axios";

Vue.config.productionTip = false;
//让请求携带上浏览器的cookie
axios.defaults.withCredentials = true;

Vue.use(ElementUI);
Vue.use(VueAxios, axios);
Vue.use(mavonEditor);
Vue.prototype.$moment = dayjs;

router.beforeEach((to, form, next) => {
  NProgress.start();

  if (to.path == "/login") {
    next();
  } else if (!store.state.userId) {
    //登陆成功之后跳转”/" 又做一次判断 现在还是没有userId 然后跳转了/login
    next({ path: "/login" });
  } else {
    next();
  }
});
router.afterEach(() => {
  NProgress.done();
});

// 响应拦截器
axios.interceptors.response.use(
  function (response) {
    switch (response.data.code) {
      case 40001:
        Vue.prototype.$message({
          type: "error",
          message: response.data.message,
        });
        router.push({ path: "/login" });
        break;
      case 50000:
        Vue.prototype.$message({
          type: "error",
          message: response.data.message,
        });
        break;
    }
    return response;
  },
  function (error) {
    return Promise.reject(error);
  }
);

NProgress.configure({
  easing: "ease", // 动画方式
  speed: 500, // 递增进度条的速度
  showSpinner: false, // 是否显示加载ico
  trickleSpeed: 200, // 自动递增间隔
  minimum: 0.3, // 初始化时的最小百分比})
});

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
