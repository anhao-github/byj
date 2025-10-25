import request from '@/utils/request'

// 查询设备信息列表
export function listDeviceInfo(query) {
  return request({
    url: '/device/deviceInfo/list',
    method: 'get',
    params: query
  })
}

//查询设备列表
export function queryDevice(deviceName){
  return request({
    url: '/device/deviceInfo/queryDevice/' + deviceName,
    method: 'get'
  })
}

// 查询设备信息详细
export function getDeviceInfo(deviceId) {
  return request({
    url: '/device/deviceInfo/' + deviceId,
    method: 'get'
  })
}

// 新增设备信息
export function addDeviceInfo(data) {
  return request({
    url: '/device/deviceInfo',
    method: 'post',
    data: data
  })
}

// 修改设备信息
export function updateDeviceInfo(data) {
  return request({
    url: '/device/deviceInfo',
    method: 'put',
    data: data
  })
}

// 删除设备信息
export function delDeviceInfo(deviceId) {
  return request({
    url: '/device/deviceInfo/' + deviceId,
    method: 'delete'
  })
}