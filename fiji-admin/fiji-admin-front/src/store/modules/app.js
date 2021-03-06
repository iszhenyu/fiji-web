import Cookies from 'js-cookie'

const state = {
  sidebar: {
    opened: !+Cookies.get('sidebarStatus')
  },
  visitedViews: []
}

const mutations = {
  TOGGLE_SIDEBAR: state => {
    if (state.sidebar.opened) {
      Cookies.set('sidebarStatus', 1)
    } else {
      Cookies.set('sidebarStatus', 0)
    }
    state.sidebar.opened = !state.sidebar.opened
  },
  ADD_VISITED_VIEWS: (state, view) => {
    if (state.visitedViews.some(v => v.path === view.path)) return
    state.visitedViews.push({name: view.name, path: view.path})
  },
  DEL_VISITED_VIEWS: (state, view) => {
    let index
    for (const [i, v] of state.visitedViews.entries()) {
      if (v.path === view.path) {
        index = i
        break
      }
    }
    state.visitedViews.splice(index, 1)
  }
}

const actions = {
  ToggleSideBar({commit}) {
    commit('TOGGLE_SIDEBAR')
  },
  AddVisitedViews({commit}, view) {
    commit('ADD_VISITED_VIEWS', view)
  },
  DelVisitedViews({commit, state}, view) {
    return new Promise((resolve) => {
      commit('DEL_VISITED_VIEWS', view)
      resolve([...state.visitedViews])
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
