import {default as api} from '@/api'

export function getUserInfo (userId) {
  return api({
    url: 'admin_user/' + userId,
    method: 'get'
  })
}
