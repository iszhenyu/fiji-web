import axios from 'axios'
import Qs from 'qs'
import { Message } from 'element-ui'
import { getToken } from '@/utils/auth'
import store from '@/store'

// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_API,
  timeout: 10000,
  /**
   * `validateStatus` 定义对于给定的HTTP 响应状态码是 resolve 或 reject  promise 。
   * 如果 `validateStatus` 返回 `true` (或者设置为 `null` 或 `undefined`)，promise 将被 resolve; 否则，promise 将被 rejecte
   */
  validateStatus: function (status) {
    return true
  },
  withCredentials: true   //跨域
})

// request拦截器
service.interceptors.request.use(
  config => {
    // 采用表单的格式提交数据，默认是json格式
    if (config.method === 'post') {
      config.data = Qs.stringify(config.data)
      config.headers['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8'
    }
    if (store.getters.token) {
      config.headers['FJ-Token'] = getToken() // 让每个请求携带token
    }
    return config
  },
  error => {
    console.error(error)
    Promise.reject(error)
  }
)

// respone拦截器
service.interceptors.response.use(
  response => {
    console.log(response)
    if (response.status === 401) {
      Message({
        showClose: true,
        message: meta.message,
        type: 'error',
        duration: 500,
        onClose: () => {
          store.dispatch('admin_user/fedLogout').then(() => {
            location.reload() // 为了重新实例化vue-router对象 避免bug
          })
        }
      })
      return Promise.reject(res)
    }

    const res = response.data
    const data = response.data.data
    const meta = response.data.meta
    if (meta.success) {
      return data
    } else {
      Message({
        message: meta.message,
        type: 'error',
        duration: 3 * 1000
      })
      return Promise.reject(res)
    }
  },
  error => {
    console.log('something wrong: ' + error)// for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 3 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
