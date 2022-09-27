function cb(val) {
  /* 渲染视图 */
  console.log("视图更新啦～");
}

function defineReactive(obj, key, val) {
  Object.defineProperty(obj, key, {
    enumerable: true /* 属性可枚举 */,
    configurable: true /* 属性可被修改或删除 */,
    get: function reactiveGetter() {
      return val; /* 实际上会依赖收集，下一小节会讲 */
    },
    set: function reactiveSetter(newVal) {
      if (newVal === val) return;
      cb(newVal);
    },
  });
}

function observer(watchValue) {
  if (!watchValue || typeof watchValue !== "object") {
    return;
  }
  console.log(Object.keys(watchValue));
  Object.keys(watchValue).forEach((key) => {
    defineReactive(watchValue, key, watchValue[key]);
  });
}

class Vue {
  constructor(options) {
    this._data = options.data;
    observer(this._data);
  }
}

let demo = new Vue({
  data: {
    test: "大白猪测试",
  },
});

demo._data.test = "大白豬住";
