<script setup>
import {ref, watch} from "vue";
import {CoffeeOutlined,UserOutlined} from "@ant-design/icons-vue";
import router from "@/router/index.js";

const selectedKeys = ref([]);
watch(()=> router.currentRoute.value.path,
  (newValue) =>{
    selectedKeys.value = []
    selectedKeys.value.push(newValue)
  },{immediate:true})
</script>
<template>
  <a-layout-header class="header">
    <div class="logo" />
    <a-menu
      v-model:selectedKeys="selectedKeys"
      theme="dark"
      mode="horizontal"
      :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="/welcome">
        <router-link to="/welcome">
          <coffee-outlined/> &nbsp;欢迎
        </router-link>
      </a-menu-item>
      <a-menu-item key="/passenger">
        <router-link to="/passenger">
          <user-outlined/> &nbsp;乘车人管理
        </router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<style scoped>
.logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}
</style>
