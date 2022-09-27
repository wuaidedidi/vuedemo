<template>
  <div id="app">
    <router-view />
  </div>
</template>
<script>
import { generaMenu } from "@/assets/js/menu";

export default {
  created() {
    if (this.$store.state.userId !== "") {
      generaMenu();
    }
    //上传访客信息
  },
};
</script>
<style lang="scss"></style>
