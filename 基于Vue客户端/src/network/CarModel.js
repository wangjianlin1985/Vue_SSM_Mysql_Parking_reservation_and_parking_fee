// 车型信息业务处理
import request from './request'
import qs from 'qs'

/* 根据查询条件分页查询车型信息 */
export function queryCarModel(page) {
    return request({
        url: '/carModel/list',
        method: "POST",
        params: {
            "page": page,
            "rows": 10
        }
    })
}

/* 查询所有的车型信息*/
export function queryAllCarModel() {
    return request({
        url: '/carModel/listAll',
        method: "POST",
    })
}

/* 添加车型信息 */
export function addCarModel(carModel) {
    return request({
        url: '/carModel/add',
        method: "POST",
        data: qs.stringify(carModel),
        headers: { //添加请求头
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
}

/* 更新车型信息 */
export function updateCarModel(carModel) {
    return request({
        url: '/carModel/update',
        method: "POST",
        data: qs.stringify(carModel),
        headers: { //添加请求头
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
}

/* 根据主键车型id查询车型信息 */
export function getCarModel(modelId) {
    return request({
        url: '/carModel/get/' + modelId,
        method: "POST"
    })
}

/* 根据主键modelId删除车型 */
export function deleteCarModel(modelId) {
    return request({
        url: '/carModel/delete/' + modelId,
        method: "POST",
    })
}
