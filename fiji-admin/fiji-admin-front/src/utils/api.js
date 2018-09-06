import axios from 'axios'
import { Message } from 'element-ui'
import { getToken } from '@/utils/auth'
import store from '@/store'

// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_API,
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
  response => {
    const res = response.data
    const data = response.data.data
    const meta = response.data.meta
    if (meta.success) {
      return data
    } else {
      if (meta.code === 401) {
        Message({
          showClose: true,
          message: meta.message,
          type: 'error',
          duration: 500,
          onClose: () => {
            store.dispatch('FedLogOut').then(() => {
              location.reload()// 为了重新实例化vue-router对象 避免bug
            })
          }
        })
        return Promise.reject(res)
      } else {
        Message({
          message: meta.message,
          type: 'error',
          duration: 3 * 1000
        })
        return Promise.reject(res)
      }
    }
  },
  error => {
    console.log('something wrong: ' + error.status)// for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 3 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
