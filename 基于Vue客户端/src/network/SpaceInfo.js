// 停车位信息业务处理
import request from './request'
import qs from 'qs'

/* 根据查询条件分页查询停车位信息 */
export function querySpaceInfo(regionObj_regionId, spaceNo, spaceState, page) {
    return request({
        url: '/spaceInfo/list',
        method: "POST",
        params: {
            "regionObj.regionId": regionObj_regionId,
            "spaceNo": spaceNo,
            "spaceState": spaceState,
            "page": page,
            "rows": 10
        }
    })
}

/* 查询所有的停车位信息*/
export function queryAllSpaceInfo() {
    return request({
        url: '/spaceInfo/listAll',
        method: "POST",
    })
}

/* 添加停车位信息 */
export function addSpaceInfo(spaceInfo) {
    return request({
        url: '/spaceInfo/add',
        method: "POST",
        data: qs.stringify(spaceInfo),
        headers: { //添加请求头
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
}

/* 更新停车位信息 */
export function updateSpaceInfo(spaceInfo) {
    return request({
        url: '/spaceInfo/update',
        method: "POST",
        data: qs.stringify(spaceInfo),
        headers: { //添加请求头
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
}

/* 根据主键记录id查询停车位信息 */
export function getSpaceInfo(spaceId) {
    return request({
        url: '/spaceInfo/get/' + spaceId,
        method: "POST"
    })
}

/* 根据主键spaceId删除停车位 */
export function deleteSpaceInfo(spaceId) {
    return request({
        url: '/spaceInfo/delete/' + spaceId,
        method: "POST",
    })
}
