import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import admin_user from './modules/admin_user'
import permission from './modules/permission'
import getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    admin_user,
    permission
  },
  getters
})

export default store
