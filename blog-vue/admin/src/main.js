import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import "./assets/css/index.css";
import "./assets/css/iconfont.css";
import config from "./assets/js/config";
import axios from "axios";
//vue-axios是将axios集成到vue的库 ，因为axios是单纯的三方库
import VueAxios from "vue-axios";
//echart
import ECharts from "vue-echarts";
import "echarts/lib/chart/line";
import "echarts/lib/chart/pie";
import "echarts/lib/chart/bar";
import "echarts/lib/chart/map";
import "echarts/lib/component/tooltip";
import "echarts/lib/component/legend";
import "echarts/lib/component/title";
//富文本插件
import mavonEditor from "mavon-editor";
import "mavon-editor/dist/css/index.css";
//加载效果条
import NProgress from "nprogress";
import "nprogress/nprogress.css";
//热力图插件
import VueCalendarHeatmap from "vue-calendar-heatmap";
//Day.js 是一个极简的 JavaScript 库，它解析、验证、操作和显示现代浏览器的日期和时间
import dayjs from "dayjs";
//标签云,前端页面特效使用
import tagCloud from "./components/tag-cloud";


Vue.prototype.config = config;
Vue.use(mavonEditor);
Vue.use(tagCloud);
Vue.use(VueCalendarHeatmap);
//预定义的vue实例
Vue.component("v-chart", ECharts);
//vue和axios之间的插件
Vue.use(VueAxios, axios);
Vue.use(ElementUI);
//生产信息是否打印
Vue.config.productionTip = false;
//原型的moment改为dayjs
Vue.prototype.$moment = dayjs;

//全局过滤器
Vue.filter("date", function(value, formatStr = "YYYY-MM-DD") {
  return dayjs(value).format(formatStr);
});

Vue.filter("dateTime", function(value, formatStr = "YYYY-MM-DD HH:mm:ss") {
  return dayjs(value).format(formatStr);
});
//插件进度条
NProgress.configure({
  easing: "ease", // 动画方式
  speed: 500, // 递增进度条的速度
  showSpinner: false, // 是否显示加载ico
  trickleSpeed: 200, // 自动递增间隔
  minimum: 0.3 // 初始化时的最小百分比
});
//每次路由跳转前事件
router.beforeEach((to, from, next) => {
  NProgress.start();
  if (to.path == "/login") {
    next();
  } else if (!store.state.userId) {
    next({ path: "/login" });
  } else {
    next();
  }
});
//进度条插件完成
router.afterEach(() => {
  NProgress.done();
});

// 响应拦截器
axios.interceptors.response.use(
  function(response) {
    switch (response.data.code) {
      case 40001:
        Vue.prototype.$message({
          type: "error",
          message: response.data.message
        });
        router.push({ path: "/login" });
        break;
      case 50000:
        Vue.prototype.$message({
          type: "error",
          message: response.data.message
        });
        break;
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
  render: h => h(App)//挂载到app节点
}).$mount("#app");
