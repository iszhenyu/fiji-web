import Cookies from 'js-cookie'
import store from '../store'

const TOKEN_KEY = 'FJ-TOKEN'
const IDENTITY_ID = 'FJ_IDENTITY'

export function getToken () {
  return Cookies.get(TOKEN_KEY)
}

export function setToken (token) {
  return Cookies.set(TOKEN_KEY, token)
}

export function removeToken () {
  return Cookies.remove(TOKEN_KEY)
}

export function getIdentityId () {
  return Cookies.get(IDENTITY_ID)
}

export function setIdentityId (id) {
  return Cookies.set(IDENTITY_ID, id)
}

export function removeIdentityId () {
  return Cookies.remove(IDENTITY_ID)
}

export function hasPermission (permission) {
  let myPermissions = store.getters.permissions
  return myPermissions.indexOf(permission) > -1
}
