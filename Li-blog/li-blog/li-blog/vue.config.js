const path = require("path");

module.exports = {
  //是否ES检查
  lintOnSave: false,
  productionSourceMap: false, //查看打包之后错误提示的工具
  devServer: {
    proxy: {
      //监听那些api
      "/api": {
        target: "http://localhost:8087",
        ws: true, //支持websocket跨域
        //可以设置跨域为
        changeOrigin: true,
        pathRewrite: {
          "^/api": "",
        },
        //关闭请求检查
        disableHostCheck: true,
      },
    },
    historyApiFallback: true,
    allowedHosts: "all",
  },
  chainWebpack: (config) => {
    config.resolve.alias.set("@", resolve("src"));
  },
};

function resolve(dir) {
  return path.join(__dirname, dir);
}
