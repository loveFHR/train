<script setup>
import {onMounted, ref} from 'vue';
import {
  PassengerAddService,
  PassengerListService,
  PassengerModifyService
} from "@/api/passenger.js"
import {notification} from "ant-design-vue";

const passenger = ref({
  id:undefined,
  name: undefined,
  idCard: undefined,
  type: undefined,
  memberId:undefined,
  createTime:undefined,
  updateTime:undefined
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
  }, {
   title: '操作',
    dataIndex: 'operation'
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
const onAdd = () => {
  //清空数据
  passenger.value = {}
  open.value = true;
};
const handleOk = async e => {
  let res
  if (passenger.value){
    res = await PassengerModifyService(passenger.value)
  }else {
    res = await PassengerAddService(passenger.value)
  }
  if (res.code === 200) {
    notification.success({description: "操作成功！"})
    open.value = false;
    await getPassengerList()
  } else {
    notification.error({description: res.data})
  }
};
const onEdit = (record) => {
  //如果直接把record赋值给passenger.value，那么他们指向同一个地址
  //在表单修改数据时，表格的数据也在同步修改，采用JSON转换的方式创建新的对象进行赋值
  const row = JSON.parse(JSON.stringify(record))
  passenger.value = row
  open.value = true
}
</script>

<template>
  <div>
    <p>
      <a-button type="primary" @click="onAdd">新增</a-button>
    </p>
    <div>
      <a-table :dataSource="passengerForm" :columns="columns"
               :pagination="pagination"
               @change="handleFormChange"
               :loading="loading">
        <template #bodyCell="{ column, record }">
          <template v-if="column.dataIndex === 'operation'">
            <a-space>
              <a @click="onEdit(record)">编辑</a>
            </a-space>
          </template>
        </template>
      </a-table>
    </div>
    <a-modal v-model:open="open" title="乘车人" @ok="handleOk"
             ok-text="确定" cancel-text="取消">
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
