import {asyncRouterMap, constantRouterMap} from '@/router/index'

/**
 * 判断用户是否拥有此菜单
 * @param menus
 * @param route
 */
function hasPermission (menus, route) {
  if (route.menu) {
    // 如果这个路由有menu属性,就需要判断用户是否拥有此menu权限
    return menus.indexOf(route.menu) > -1
  } else {
    return true
  }
}

function hasRole(roles, role) {
  return roles.indexOf(role) > -1
}

/**
 * 递归过滤异步路由表，返回符合用户菜单权限的路由表
 * @param asyncRouterMap
 * @param menus
 */
function filterAsyncRouter (asyncRouterMap, menus) {
  return asyncRouterMap.filter(route => {
    // filter,js语法里数组的过滤筛选方法
    if (hasPermission(menus, route)) {
      if (route.children && route.children.length) {
        // 如果这个路由下面还有下一级的话,就递归调用
        route.children = filterAsyncRouter(route.children, menus)
        // 如果过滤一圈后,没有子元素了,这个父级菜单就也不显示了
        return (route.children && route.children.length)
      }
      return true
    }
    return false
  })
}

const state = {
  routers: constantRouterMap, // 本用户所有的路由,包括了固定的路由和下面的addRouters
  addRouters: [] // 本用户的角色赋予的新增的动态路由
}

const mutations = {
  SET_ROUTERS: (state, routers) => {
    state.addRouters = routers
    state.routers = constantRouterMap.concat(routers) // 将固定路由和新增路由进行合并, 成为本用户最终的全部路由信息
  }
}

const actions = {
  GenerateRoutes ({commit}, userPermission) {
    return new Promise(resolve => {
      const roleList = userPermission.roles
      const menuList = userPermission.menus
      let accessedRouters
      if (hasRole(roleList, 'admin')) {
        // 如果角色里包含'管理员',那么所有的路由都可以用
        // 其实管理员也拥有全部菜单,这里主要是利用角色判断,节省加载时间
        accessedRouters = asyncRouterMap
      } else {
        // 否则需要通过以下方法来筛选出本角色可用的路由
        accessedRouters = filterAsyncRouter(asyncRouterMap, menuList)
      }
      // 执行设置路由的方法
      commit('SET_ROUTERS', accessedRouters)
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
