import request from '@/utils/request'

// 查询设备分类列表
export function listDeviceType(query) {
  return request({
    url: '/device/deviceType/list',
    method: 'get',
    params: query
  })
}
// 删除设备分类
export function delDeviceType(typeId) {
    return request({
      url: '/device/deviceType/' + typeId,
      method: 'delete'
    })
  }
  // 新增设备分类
export function addDeviceType(data) {
  return request({
    url: '/device/deviceType',
    method: 'post',
    data: data
  })
}
// 查询设备分类详细
export function getDeviceType(typeId) {
  return request({
    url: '/device/deviceType/' + typeId,
    method: 'get'
  })
}
// 修改设备分类
export function updateDeviceType(data) {
  return request({
    url: '/device/deviceType',
    method: 'put',
    data: data
  })
}