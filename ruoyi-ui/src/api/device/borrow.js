import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listBorrow(query) {
  return request({
    url: '/device/borrow/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getBorrow(borrowId) {
  return request({
    url: '/device/borrow/' + borrowId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addBorrow(data) {
  return request({
    url: '/device/borrow',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateBorrow(data) {
  return request({
    url: '/deivce/borrow',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delBorrow(borrowId) {
  return request({
    url: '/device/borrow/' + borrowId,
    method: 'delete'
  })
}
