import request from '@/utils/request'

// 查询app用户注册记录列表
export function listRegister(query) {
    return request({
        url: '/device/deviceUserRegister/list',
        method: 'get',
        params: query
    })
}

// 查询app用户注册记录详细
export function getRegister(regId) {
    return request({
        url: '/device/deviceUserRegister/' + regId,
        method: 'get'
    })
}

// 新增app用户注册记录
export function addRegister(data) {
    return request({
        url: '/device/deviceUserRegister',
        method: 'post',
        data: data
    })
}

// 修改app用户注册记录
export function updateRegister(data) {
    return request({
        url: '/device/deviceUserRegister',
        method: 'put',
        data: data
    })
}

// 删除app用户注册记录
export function delRegister(regId) {
    return request({
        url: '/device/deviceUserRegister/' + regId,
        method: 'delete'
    })
}