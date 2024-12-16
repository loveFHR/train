<script setup>
import {onMounted, ref} from 'vue';
import {PassengerAddService, PassengerListService} from "@/api/passenger.js"
import {notification} from "ant-design-vue";

const passenger = ref({
  name: '',
  idCard: '',
  type: ''
})
const passengerForm = ref([])
const columns = ref([
  {
    title: '姓名',
    dataIndex: 'name',
    key: 'name'
  },
  {
    title: '身份证',
    dataIndex: 'idCard',
    key: 'idCard'
  }, {
    title: '类型',
    dataIndex: 'type',
    key: 'type' //type === 1 ? '成人' : (type === 2 ? '儿童' : '学生')
  }
])
const pagination = ref({
  total: 0,
  current: 1,
  pageSize: 2
})
const pageInfo = ref({
  page: 1,
  size: pagination.value.pageSize
})
const getPassengerList = async () => {
  const res = await PassengerListService(pageInfo.value)
  if (res.code === 200) {
    passengerForm.value = res.data.list
    pagination.value.total = res.data.total
    pagination.value.current = pageInfo.value.page
    loading.value = false
  } else {
    notification.error({description: res.msg})
  }
}
const loading = ref(true)
onMounted(() => {
  getPassengerList()
})

const handleFormChange = (e) => {
  pageInfo.value = {
    page: e.current,
    size: e.pageSize
  }
  getPassengerList()
}
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
    await getPassengerList()
  } else {
    notification.error({description: res.data})
  }
};

</script>

<template>
  <div>
    <p>
      <a-button type="primary" @click="showModal">新增</a-button>
    </p>
    <div>
      <a-table :dataSource="passengerForm" :columns="columns"
               :pagination="pagination"
               @change="handleFormChange"
               :loading="loading"/>
    </div>
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
