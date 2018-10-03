import {removeToken, setToken, removeIdentityId, setIdentityId} from '@/utils/auth'
import store from '../../store'
import router from '../../router'
import {login, logout} from '@/api/auth'
import {getAdminUserInfo} from '@/api/admin_user'

const state = {
  username: '',
  userId: '',
  avatarUrl: 'https://www.gravatar.com/avatar/6560ed55e62396e40b34aac1e5041028',
  role: '',
  menus: [],
  permissions: []
}

const mutations = {
  SET_USER: (state, userInfo) => {
    state.username = userInfo.username
    state.userId = userInfo.userId
    state.avatarUrl = userInfo.avatarUrl
    state.role = userInfo.roleName
    state.menus = userInfo.menuList
    state.permissions = userInfo.permissionList
  },
  RESET_USER: (state) => {
    state.username = ''
    state.userId = ''
    state.avatarUrl = ''
    state.role = ''
    state.menus = []
    state.permissions = []
  }
}

const actions = {
  // 登录
  login ({commit, state}, loginForm) {
    return new Promise((resolve, reject) => {
      login(loginForm.username, loginForm.password).then(data => {
        // cookie中保存前端登录状态
        setToken(data.token)
        setIdentityId(data.userId)

        commit('SET_USER', data)
        resolve(data)
      }).catch(err => {
        reject(err)
      })
    })
  },
  // 获取用户信息
  getInfo ({commit, state}, userId) {
    return new Promise((resolve, reject) => {
      getAdminUserInfo(userId).then(data => {
        // 储存用户信息
        commit('SET_USER', data)
        // 生成路由
        let userPermission = data.permissions
        store.dispatch('permission/GenerateRoutes', userPermission).then(() => {
          // 生成该用户的新路由json操作完毕之后,调用vue-router的动态新增路由方法,将新路由添加
          router.addRoutes(store.getters.addRouters)
        })
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // 登出
  logout ({commit}) {
    return new Promise((resolve) => {
      logout().then(data => {
        commit('RESET_USER')
        removeToken()
        resolve(data)
      }).catch(() => {
        commit('RESET_USER')
        removeToken()
      })
    })
  },
  // 前端 登出
  fedLogout ({commit}) {
    return new Promise(resolve => {
      commit('RESET_USER')
      removeToken()
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
