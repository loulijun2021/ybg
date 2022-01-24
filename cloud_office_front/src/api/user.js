import request from '@/utils/request'

export function login(data) {
  return request({
    url: 'user/login',
    // url: '/vue-admin-template/user/login',
    method: 'post',
    data
  })
}

// export function getInfo() {
//   return request({
//     url: '/user/getInfo',
//     method: 'get'
//   })
// }

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}

export function register(data) {
  return request({
    url: '/user/register',
    method: 'post',
    data
  })
}
export function getUserInfoAll(data) {
  return request({
    url: '/user/getUserInfoAll',
    method: 'get',
    params: data
  })
}

export function getDeptAll() {
  return request({
    url: '/dept/getDeptAll',
    method: 'get'
  })
}

export function insertUserInfo(data) {
  return request({
    url: '/user/insertUserInfo',
    method: 'post',
    data
  })
}

export function deleteUserInfo(data) {
  return request({
    url: '/user/deleteUserInfo',
    method: 'get',
    params: data
  })
}
export function resetPassword(data) {
  return request({
    url: '/user/resetPassword',
    method: 'get',
    params: data
  })
}

export function passwordChange(data) {
  return request({
    url: '/user/passwordChange',
    method: 'post',
    data
  })
}
