/**
 * 监听类 存放所有的watch
 */
class Dep {
  /**
   * 构造函数，构造一个数组对象存放
   * @param {} name
   */
  constructor(name) {
    this.subs = [];
  }
  /**
   * 添加一个watch
   */
  addSub(sub) {
    this.subs.push(sub);
  }
  /**
   * 通知所有的watch进行修改更新
   */
  notify() {
    this.subs.forEach((sub) => sub.update());
  }
}

function cb(val) {
  /* 渲染视图 */
  console.log("视图更新啦～");
}

/**
 * 观察方法
 * @param {} watchValue
 * @returns
 */
function observer(watchValue) {
  if (!watchValue || typeof watchValue !== "object") {
    return;
  }
  console.log(Object.keys(watchValue));
  Object.keys(watchValue).forEach((key) => {
    defineReactive(watchValue, key, watchValue[key]);
  });
}
/**
 * 监视器
 */
class Watch {
  constructor() {
    /* 在new一个Watcher对象时将该对象赋值给Dep.target，在get中会用到 */
    Dep.target = this;
  }
  update() {
    console.log("视图更新了");
  }
}
Dep.target = null;
function defineReactive(obj, key, val) {
  const dep = new Dep();

  Object.defineProperty(obj, key, {
    enumerable: true,
    configurable: true,
    get: function reactiveGetter() {
      console.log("get方法");
      dep.addSub(Dep.target);
      return val;
    },
    set: function reactiveSetter(newVal) {
      if (newVal === val) return;
      dep.notify();
    },
  });
}

class Vue {
  constructor(options) {
    this._data = options.data;
    observer(this._data);
    new Watch();
    console.log("render", this._data.test);
  }
}

let demo = new Vue({
  data: {
    test: "大白猪测试",
  },
});
demo._data.test = "小白猪猪";
