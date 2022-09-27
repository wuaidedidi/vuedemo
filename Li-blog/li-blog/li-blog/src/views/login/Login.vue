<template>
  <div class="login-container">
    <div style="display: flex; height: 100%">
      <div class="login-left"></div>
      <div class="login-right">
        <div class="login-panel">
          <!-- 登录表单 -->
          <el-form
            status-icon
            :model="loginForm"
            :rules="rules"
            ref="ruleForm"
            class="login-form"
          >
            <!-- 用户名输入框 -->
            <el-form-item prop="username">
              <el-input
                v-model="loginForm.username"
                prefix-icon="el-icon-user-solid"
                placeholder="用户名"
                suffix-icon="el-icon-circle-check"
                @keyup.enter.native="login"
              />
            </el-form-item>
            <!-- 密码输入框 -->
            <el-form-item prop="password">
              <el-input
                v-model="loginForm.password"
                prefix-icon="el-icon-lock"
                placeholder="请输入密码"
                suffix-icon="el-icon-circle-check"
                show-password
                @keyup.enter.native="login"
              ></el-input>
            </el-form-item>
          </el-form>
          <!-- 登录按钮 -->
          <el-button class="submitButton" type="primary" @click="login"
            >登录</el-button
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { generaMenu } from "@/assets/js/menu";
export default {
  data() {
    return {
      loginForm: {
        username: "",
        password: "",
      },
      rules: {
        username: {
          required: true,
          message: "用户名不能为空",
          trigger: "blur",
        },
        password: {
          trigger: "blur",
          required: true,
          message: "密码不能为空",
        },
      },
    };
  },
  methods: {
    login() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          //URLSearchParams 定义了一系列的方法
          let param = new URLSearchParams();
          param.append("username", this.loginForm.username);
          param.append("password", this.loginForm.password);
          this.axios.post("/api/login", param).then(({ data }) => {
            if (data.code === 200) {
              // 登录后保存用户信息
              this.$store.commit("login", data.data);
              // 加载用户菜单
              generaMenu();
              this.$message.success("登录成功");
              this.$router.push({ path: "/" });
            } else {
              this.$message.error(data.message);
            }
          });
        } else {
          this.$message.error("回调失败");
          return false;
        }
      });
    },
  },
};
</script>

<style scoped>
.login-container {
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  background: url(https://static.talkxj.com/config/0w3pdr.jpg) center center /
    cover no-repeat;
}
.login-right {
  width: 490px;
  height: 100%;
  background: #fff;
}
.login-panel {
  margin: 300px auto;

  text-align: center;
  height: 70%;
  width: 80%;
}
.login-left {
  flex: 1;
}
.submitButton {
  width: 100%;
}
</style>
