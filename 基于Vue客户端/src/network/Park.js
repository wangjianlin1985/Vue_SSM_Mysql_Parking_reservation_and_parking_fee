// 车辆停车信息业务处理
import request from './request'
import qs from 'qs'

/* 根据查询条件分页查询车辆停车信息 */
export function queryPark(carObj_carId, userObj_user_name, spaceObj_spaceId, startTime, endTime, page) {
    return request({
        url: '/park/list',
        method: "POST",
        params: {
            "carObj.carId": carObj_carId,
            "userObj.user_name": userObj_user_name,
            "spaceObj.spaceId": spaceObj_spaceId,
            "startTime": startTime,
            "endTime": endTime,
            "page": page,
            "rows": 10
        }
    })
}

/* 查询所有的车辆停车信息*/
export function queryAllPark() {
    return request({
        url: '/park/listAll',
        method: "POST",
    })
}

/* 添加车辆停车信息 */
export function addPark(park) {
    return request({
        url: '/park/add',
        method: "POST",
        data: qs.stringify(park),
        headers: { //添加请求头
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
}

/* 更新车辆停车信息 */
export function updatePark(park) {
    return request({
        url: '/park/update',
        method: "POST",
        data: qs.stringify(park),
        headers: { //添加请求头
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
}

/* 根据主键记录id查询车辆停车信息 */
export function getPark(parkId) {
    return request({
        url: '/park/get/' + parkId,
        method: "POST"
    })
}

/* 根据主键parkId删除车辆停车 */
export function deletePark(parkId) {
    return request({
        url: '/park/delete/' + parkId,
        method: "POST",
    })
}
