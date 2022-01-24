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

