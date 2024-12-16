import request from "@/util/request.js";

export const PassengerAddService = (data) => request.post('/passenger',data)
