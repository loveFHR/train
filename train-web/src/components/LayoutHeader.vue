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
    <div class="logo" >
      <router-link to="/welcome" style="color: white; font-size: 18px">
        12306控台
      </router-link>
    </div>
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
  height: 31px;
  width: 150px;
  color: white;
  font-size: 20px;
}
</style>
