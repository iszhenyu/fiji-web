import Vue from 'vue'
import Router from 'vue-router'
import Layout from '../views/layout/Layout'

const _import = require('./_import_production')

Vue.use(Router)

export const constantRouterMap = [
  {
    path: '/login',
    component: _import('Login'),
    hidden: true
  },
  {
    path: '/404',
    component: _import('404'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: '首页',
    hidden: true,
    children: [{
      path: 'dashboard', component: _import('dashboard/index')
    }]
  }
]

export const asyncRouterMap = [
  {
    path: '/system',
    component: Layout,
    redirect: '/system/article',
    name: '功能模块',
    meta: {title: '功能模块', icon: 'tree'},
    children: [
      {
        path: 'article',
        name: '文章',
        component: _import('article/index'),
        meta: {title: '文章', icon: 'example'},
        menu: 'article'
      },
    ]
  },
  {
    path: '/permission',
    component: Layout,
    redirect: '/permission/',
    name: '',
    meta: {title: '后台用户权限', icon: 'table'},
    children: [
      {
        path: 'admin_users',
        name: '用户列表',
        component: _import('permission/admin_users'),
        meta: {title: '后台用户列表', icon: 'user'},
        menu: 'admin_user'
      },
      {
        path: 'roles',
        name: '权限管理',
        component: _import('permission/roles'),
        meta: {title: '后台角色列表', icon: 'password'},
        menu: 'role'
      },
    ]
  },
  {
    path: '*',
    redirect: '/404',
    hidden: true
  }
]

export default new Router({
  routes: constantRouterMap
})
