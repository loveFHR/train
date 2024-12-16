import request from "@/util/request.js";

export const memberLoginService = (data) => {
  return request.post("/member/login",data)
}

export const memberSendCodeService = (data) => {
  return request.post("/member/send/code",data)
}
