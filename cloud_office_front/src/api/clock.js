import request from '@/utils/request'

export function singIn(data) {
  return request({
    url: '/clock/singIn',
    method: 'post',
    data
  })
}

export function singOut(data) {
  return request({
    url: '/clock/singOut',
    method: 'post',
    data
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
export function updateUserInfo(data) {
  return request({
    url: '/user/updateUserInfo',
    method: 'post',
    data
  })
}
