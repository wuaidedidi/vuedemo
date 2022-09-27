import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
//vuetify vuetify作为ui系统
import vuetify from "./plugins/vuetify";
//使用anmiated动画
import animated from "animate.css";
import "./assets/css/index.css";
import "./assets/css/iconfont.css";
import "./assets/css/markdown.css";
import "./assets/css/vue-social-share/client.css";
import config from "./assets/js/config";
//vue分享图标的插件,比如说qq转发 之类的
import Share from "vue-social-share";
//日期vue插件
import dayjs from "dayjs";
//弹幕插件
import { vueBaberrage } from "vue-baberrage";
import axios from "axios";
import VueAxios from "vue-axios";
//下拉加载，往下拉才加载多余的页面
import InfiniteLoading from "vue-infinite-loading";
//代码高亮显示
import "highlight.js/styles/atom-one-dark.css";
//图片预览插件
import VueImageSwipe from "vue-image-swipe";
import "vue-image-swipe/dist/vue-image-swipe.css";
//vuetify 的消息弹出框
import Toast from "./components/toast/index";
//加载栏插件
import NProgress from "nprogress";
import "nprogress/nprogress.css";

Vue.prototype.config = config;
Vue.config.productionTip = false;
Vue.use(animated);
Vue.use(Share);
Vue.use(vueBaberrage);
Vue.use(InfiniteLoading);
Vue.use(VueAxios, axios);
Vue.use(VueImageSwipe);
Vue.use(Toast);

Vue.filter("date", function(value) {
  return dayjs(value).format("YYYY-MM-DD");
});

Vue.filter("year", function(value) {
  return dayjs(value).format("YYYY");
});

Vue.filter("hour", function(value) {
  return dayjs(value).format("HH:mm:ss");
});

Vue.filter("time", function(value) {
  return dayjs(value).format("YYYY-MM-DD HH:mm:ss");
});

Vue.filter("num", function(value) {
  if (value >= 1000) {
    return (value / 1000).toFixed(1) + "k";
  }
  return value;
});

router.beforeEach((to, from, next) => {
  NProgress.start();
  if (to.meta.title) {
    document.title = to.meta.title;
  }
  next();
});

router.afterEach(() => {
  window.scrollTo({
    top: 0,
    behavior: "instant"
  });
  NProgress.done();
});

axios.interceptors.response.use(
  function(response) {
    switch (response.data.code) {
      case 50000:
        Vue.prototype.$toast({ type: "error", message: "系统异常" });
    }
    return response;
  },
  function(error) {
    return Promise.reject(error);
  }
);

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");
