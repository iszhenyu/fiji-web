import Vue from 'vue'
import 'normalize.css/normalize.css'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN'
import App from './App'
import router from './router'
import store from './store'
import '@/icons'
import '@/permission'
import {default as api} from './api'
import {hasPermission} from './utils/auth'

Vue.use(ElementUI, {locale})

// 全局的常量
Vue.prototype.$http = api
Vue.prototype.$hasPerm = hasPermission

// 生产环境时自动设置为 false 以阻止 vue 在启动时生成生产提示。
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
