import request from '@/utils/request'

export function addRecord(data) {
  return request({
    url: '/record/addRecord',
    method: 'post',
    data
  })
}
export function getRecordAllByName(data) {
  return request({
    url: '/record/queryAllByName',
    method: 'post',
    data
  })
}
export function getRecordAll() {
  return request({
    url: '/record/queryAll',
    method: 'post'

  })
}
