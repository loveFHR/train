<script setup>
import {ref, watch} from "vue";
import {CoffeeOutlined, UserOutlined} from "@ant-design/icons-vue";
import router from "@/router/index.js";

const selectedKeys = ref([]);
watch(()=> router.currentRoute.value.path,
  (newValue) =>{
  selectedKeys.value = []
  selectedKeys.value.push(newValue)
},{immediate:true})
const openKeys = ref([]);
</script>

<template>
  <a-layout-sider width="200" style="background: #fff">
    <a-menu
      v-model:selectedKeys="selectedKeys"
      v-model:openKeys="openKeys"
      mode="inline"
      :style="{ height: '100%', borderRight: 0 }"
    >
      <a-sub-menu key="sub1">
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
      </a-sub-menu>

    </a-menu>
  </a-layout-sider>
</template>

<style scoped>

</style>
