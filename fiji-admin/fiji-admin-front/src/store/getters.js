const getters = {
  sidebar: state => state.app.sidebar,
  visitedViews: state => state.app.visitedViews,

  nickname: state => state.admin_user.nickname,
  userId: state => state.admin_user.userId,
  avatar: state => state.admin_user.avatar,
  roles: state => state.admin_user.roles,
  menus: state => state.admin_user.menus,
  permissions: state => state.admin_user.permissions,

  permission_routers: state => state.permission.routers,
  addRouters: state => state.permission.addRouters
}
export default getters
