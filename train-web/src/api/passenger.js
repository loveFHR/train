import request from "@/util/request.js";

export const PassengerAddService = (data) => request.post('/passenger',data)

export const PassengerListService = (pageInfo) => {
  return request.get('/passenger/query/list',{
    params:pageInfo
  })
}
