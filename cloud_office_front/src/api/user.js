import request from '@/utils/request'
import axios from 'axios'

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
// 获取所有部门
export function getDeptAll() {
  return request({
    url: '/dept/getDeptAll',
    method: 'get'
  })
}
// 新增部门
export function insertDept(data) {
  return request({
    url: '/dept/insertDept',
    method: 'post',
    data
  })
}
// 修改部门
export function updateDept(data) {
  return request({
    url: '/dept/updateDept',
    method: 'post',
    data
  })
}
// 删除部门
export function deleteDept(data) {
  return request({
    url: '/dept/deleteDept',
    method: 'get',
    params: data
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
export function updateUserInfo(data) {
  return request({
    url: '/user/updateUserInfo',
    method: 'post',
    data
  })
}

// 表单下载
export function download(data) {
  // var baseURL = process.env.VUE_APP_BASE_API
  // return axios.post(baseURL + '/user/download', data, {
  //   headers: {
  //     'Content-type': 'multipart/form-data'
  //   }
  // })
  return request({
    url: '/user/download',
    method: 'post',
    data
  })
}
