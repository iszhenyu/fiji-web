import {default as api} from '@/api'

export function getUserInfo () {
  return api({
    url: 'admin_user/info',
    method: 'get'
  })
}
