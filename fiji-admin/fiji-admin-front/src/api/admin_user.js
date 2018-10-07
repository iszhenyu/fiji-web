import {default as api} from '@/api'

export function getAdminUserInfo (userId) {
  return api({
    url: '/admin_user/' + userId,
    method: 'get'
  })
}
