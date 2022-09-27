import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    userId: "",
    avatar: "",
    nickname: "",
    intro: "",
    webSite: "",
    email: "",
    loginType: "",
    articleLikeSet: [],
    commentLikeSet: [],
    talkLikeSet: [],
    userMenuList: [],
    tabList: [{ name: "首页", path: "/" }],
    isAsideScoller: false,
  },
  getters: {},
  mutations: {
    saveTab(state, tab) {
      if (state.tabList.findIndex((item) => item.path == tab.path) == -1) {
        state.tabList.push({ path: tab.path, name: tab.name });
      }
    },
    deleteTab(state, tab) {
      const index = state.tabList.findIndex((item) => item.path == tab.path);
      if (index != -1) {
        state.tabList.splice(index, 1);
      }
    },
    closeAllTab(state, router) {
      state.tabList = [{ path: "/", name: "首页" }];
      router.push("/");
    },
    changescoller(state) {
      state.isAsideScoller = !state.isAsideScoller;
    },
    saveUserMenuList(state, userMenuList) {
      state.userMenuList = userMenuList;
    },
    logout(state) {
      state.userId = "";
      state.avatar = "";
      state.nickname = "";
      state.intro = "";
      state.webSite = "";
      state.articleLikeSet = [];
      state.commentLikeSet = [];
      state.talkLikeSet = [];
      state.email = "";
      state.loginType = "";
    },
    resetTab(state) {
      state.tabList = [{ name: "首页", path: "/" }];
    },
    login(state, user) {
      state.userId = user.userInfoId;
      state.avatar = user.avatar;
      state.nickname = user.nickname;
      state.intro = user.intro;
      state.webSite = user.webSite;
      state.articleLikeSet = user.articleLikeSet ? user.articleLikeSet : [];
      state.commentLikeSet = user.commentLikeSet ? user.commentLikeSet : [];
      state.talkLikeSet = user.talkLikeSet ? user.talkLikeSet : [];
      state.email = user.email;
      state.loginType = user.loginType;
    },
  },
  actions: {},
  modules: {},
  plugins: [
    createPersistedState({
      storage: window.sessionStorage,
    }),
  ],
});
