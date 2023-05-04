// 车辆信息业务处理
import request from './request'
import qs from 'qs'

/* 根据查询条件分页查询车辆信息 */
export function queryCar(carNo, carModelObj_modelId, pinpai, chexianriqi, userObj_user_name, addTime, page) {
    return request({
        url: '/car/list',
        method: "POST",
        params: {
            "carNo": carNo,
            "carModelObj.modelId": carModelObj_modelId,
            "pinpai": pinpai,
            "chexianriqi": chexianriqi,
            "userObj.user_name": userObj_user_name,
            "addTime": addTime,
            "page": page,
            "rows": 10
        }
    })
}

/* 查询所有的车辆信息*/
export function queryAllCar() {
    return request({
        url: '/car/listAll',
        method: "POST",
    })
}

/* 添加车辆信息 */
export function addCar(car) {
    return request({
        url: '/car/add',
        method: "POST",
        data: qs.stringify(car),
        headers: { //添加请求头
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
}

/* 更新车辆信息 */
export function updateCar(car) {
    return request({
        url: '/car/update',
        method: "POST",
        data: qs.stringify(car),
        headers: { //添加请求头
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
}

/* 根据主键车辆id查询车辆信息 */
export function getCar(carId) {
    return request({
        url: '/car/get/' + carId,
        method: "POST"
    })
}

/* 根据主键carId删除车辆 */
export function deleteCar(carId) {
    return request({
        url: '/car/delete/' + carId,
        method: "POST",
    })
}
