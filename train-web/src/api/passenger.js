import request from "@/util/request.js";

/**
 * 添加乘客
 */
export const PassengerAddService =
  (data)=> request.post('/passenger',data)

/**
 * 添加乘客
 */
export const PassengerModifyService =
  (data)=> request.put('/passenger',data)

/**
 * 分页查询乘客列表
 */
export const PassengerListService = (pageInfo) => {
  return request.get('/passenger/query/list',{
    params:pageInfo
  })
}

/**
 * 删除乘客
 */
export const PassengerDeleteService =
  (id) => request.delete(`/passenger/${id}`)
