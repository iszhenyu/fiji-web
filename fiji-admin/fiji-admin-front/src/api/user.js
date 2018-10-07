import {default as api} from '@/api'

export function getUserInfo (userId) {
  return api({
    url: '/user/' + userId,
    method: 'get'
  })
}
