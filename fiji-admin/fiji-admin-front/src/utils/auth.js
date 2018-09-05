import Cookies from 'js-cookie'
import store from "../store";

const LoginKey = 'hasLogin'

export function getToken() {
  return Cookies.get(LoginKey);
}

export function setToken() {
  return Cookies.set(LoginKey, "1")
}

export function removeToken() {
  return Cookies.remove(LoginKey)
}

export function hasPermission(permission) {
  let myPermissions = store.getters.permissions;
  return myPermissions.indexOf(permission) > -1;
}
