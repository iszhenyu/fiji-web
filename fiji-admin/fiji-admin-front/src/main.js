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
import {default as api} from './utils/api'
import {hasPermission} from "./utils/auth";

Vue.use(ElementUI, {locale})

Vue.prototype.$api = api
//全局的常量
Vue.prototype.$hasPerm = hasPermission
Vue.config.productionTip = (process.env.NODE_ENV != 'production')

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
