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

export function getAttendanceAll(data) {
  return request({
    url: '/clock/getAttendanceAll',
    method: 'get',
    params: data
  })
}

export function leave(data) {
  return request({
    url: '/clock/leave',
    method: 'post',
    data
  })
}

export function isApprove(data) {
  return request({
    url: '/clock/isApprove',
    method: 'post',
    data
  })
}

export function getLeaveHistory(data) {
  return request({
    url: '/clock/getLeaveHistory',
    method: 'post',
    data
  })
}

