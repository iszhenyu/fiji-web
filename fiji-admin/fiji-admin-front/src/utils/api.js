import axios from 'axios'
import { Message } from 'element-ui'
import { getToken } from '@/utils/auth'
import store from '@/store'

// 创建axios实例
const service = axios.create({
  baseURL: '/',
  timeout: 5000
})

// request拦截器
service.interceptors.request.use(config => {
  if (store.getters.token) {
    config.headers['X-Token'] = getToken() // 让每个请求携带token
  }
  return config
}, error => {
  // Do something with request error
  console.error(error) // for debug
  Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(
  response => response,
  // {
  //   const res = response.data;
  //   if (res.returnCode == '1000') {
  //     return res;
  //   }
  //   if (res.returnCode == '100') {
  //     return res.returnData;
  //   } else if (res.returnCode == "20011") {
  //     Message({
  //       showClose: true,
  //       message: res.returnMsg,
  //       type: 'error',
  //       duration: 500,
  //       onClose: () => {
  //         store.dispatch('FedLogOut').then(() => {
  //           location.reload()// 为了重新实例化vue-router对象 避免bug
  //         })
  //       }
  //     });
  //     return Promise.reject("未登录")
  //   } else {
  //     Message({
  //       message: res.returnMsg,
  //       type: 'error',
  //       duration: 3 * 1000
  //     })
  //     return Promise.reject(res)
  //   }
  // },
  error => {
    console.log('err' + error)// for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service

