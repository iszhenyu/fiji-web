import Vue from 'vue'
import Router from 'vue-router'
import Layout from '../pages/layout/Layout'

const _import = require('./_import_' + process.env.NODE_ENV)

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/404',
      component: _import('404'),
      hidden: true
    },
    {
      path: '/',
      name: '首页',
      component: Layout,
      hidden: true,
      redirect: '/dashboard',
      children: [{
        path: 'dashboard',
        component: _import('dashboard/index')
      }]
    }
  ]
})
