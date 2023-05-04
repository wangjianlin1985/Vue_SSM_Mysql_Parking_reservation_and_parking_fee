import Vue from 'vue'
import Vuex from 'vuex'

import mutations from './mutations'
import actions from './actions'
import getters from './getters'
import modules from './modules'

Vue.use(Vuex)

const state = {
  cartList: {}
}

const store = new Vuex.Store({
  state,
  mutations,
  actions,
  getters,
  modules
})

export default store