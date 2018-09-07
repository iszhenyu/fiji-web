import {default as api} from '@/api'

export function getUserInfo () {
  return api({
    url: 'user/info',
    method: 'get'
  })
}
