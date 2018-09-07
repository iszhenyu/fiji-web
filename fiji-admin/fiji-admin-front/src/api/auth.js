import {default as api} from '@/api'

export function login (username, password) {
  return api({
    url: 'auth/login',
    method: 'post',
    data: {
      username: username,
      password: password
    }
  })
}

export function logout () {
  return api({
    url: 'auth/logout',
    method: 'post'
  })
}
