// 区域信息业务处理
import request from './request'
import qs from 'qs'

/* 根据查询条件分页查询区域信息 */
export function queryRegion(regionFloor, page) {
    return request({
        url: '/region/list',
        method: "POST",
        params: {
            "regionFloor": regionFloor,
            "page": page,
            "rows": 10
        }
    })
}

/* 查询所有的区域信息*/
export function queryAllRegion() {
    return request({
        url: '/region/listAll',
        method: "POST",
    })
}

/* 添加区域信息 */
export function addRegion(region) {
    return request({
        url: '/region/add',
        method: "POST",
        data: qs.stringify(region),
        headers: { //添加请求头
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
}

/* 更新区域信息 */
export function updateRegion(region) {
    return request({
        url: '/region/update',
        method: "POST",
        data: qs.stringify(region),
        headers: { //添加请求头
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
}

/* 根据主键区域id查询区域信息 */
export function getRegion(regionId) {
    return request({
        url: '/region/get/' + regionId,
        method: "POST"
    })
}

/* 根据主键regionId删除区域 */
export function deleteRegion(regionId) {
    return request({
        url: '/region/delete/' + regionId,
        method: "POST",
    })
}
