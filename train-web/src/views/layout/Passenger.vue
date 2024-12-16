<script setup>
import {ref} from 'vue';
import {PassengerAddService} from "@/api/passenger.js"
import {notification} from "ant-design-vue";

const passenger = ref({
  name: '',
  idCard: '',
  type: ''
})

const open = ref(false);
const showModal = () => {
  //清空数据
  passenger.value = {
    name: '',
    idCard: '',
    type: ''
  }
  open.value = true;
};
const handleOk = async e => {
  const res = await PassengerAddService(passenger.value)
  if (res.code === 200) {
    notification.success({description: "添加成功！"})
    open.value = false;
  } else {
    notification.error({description: res.data})
  }
};

</script>

<template>
  <div>
    <a-button type="primary" @click="showModal">新增</a-button>
    <a-modal v-model:open="open" title="新增乘客" @ok="handleOk"
             ok-text="添加" cancel-text="取消">
      <a-form :model="passenger" :label-col="{span:4}" :wrapper-col="{ span: 16 }">
        <a-form-item label="姓名">
          <a-input v-model:value="passenger.name"></a-input>
        </a-form-item>
        <a-form-item label="身份证">
          <a-input v-model:value="passenger.idCard"></a-input>
        </a-form-item>
        <a-form-item label="类型">
          <a-select v-model:value="passenger.type">
            <a-select-option value="1">成人</a-select-option>
            <a-select-option value="2">儿童</a-select-option>
            <a-select-option value="3">学生</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<style scoped></style>
