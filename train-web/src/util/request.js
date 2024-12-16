import axios from "axios";
import router from '@/router'

const baseURL = 'http://localhost:9000'

const instance = axios.create({
  //  1. 基础地址，超时时间
  baseURL,
  timeout:10000
})

instance.interceptors.request.use(
  (config) => {
    //  2. 携带token
    const userStore = useUserStore()
    if(userStore.token) {

      config.headers.Authorization = userStore.token
    }
    return config
  },
  (err) => Promise.reject(err)
)

instance.interceptors.response.use(
  (res) => {

    // 4. 摘取核心响应数据
    console.log('请求参数',res)
    if (res.data.code === 1) {
      return res.data
    }
    // 3. 处理业务失败
    console.log(res.data.msg || '服务异常')
    return Promise.reject(res)
  },
  (err) => {
    //  5. 处理401错误
    if (err.response?.data.status === 401) {
      router.push('/login')
    }
    console.log(err.response.data.msg || '服务异常')
    return Promise.reject(err)
  }
)

export default instance
export {baseURL}
