// 车位预约信息业务处理
import request from './request'
import qs from 'qs'

/* 根据查询条件分页查询车位预约信息 */
export function querySpaceOrder(spaceObj_spaceId, userObj_user_name, startTime, endTime, shenHeState, orderTime, page) {
    return request({
        url: '/spaceOrder/list',
        method: "POST",
        params: {
            "spaceObj.spaceId": spaceObj_spaceId,
            "userObj.user_name": userObj_user_name,
            "startTime": startTime,
            "endTime": endTime,
            "shenHeState": shenHeState,
            "orderTime": orderTime,
            "page": page,
            "rows": 10
        }
    })
}

/* 查询所有的车位预约信息*/
export function queryAllSpaceOrder() {
    return request({
        url: '/spaceOrder/listAll',
        method: "POST",
    })
}

/* 添加车位预约信息 */
export function addSpaceOrder(spaceOrder) {
    return request({
        url: '/spaceOrder/add',
        method: "POST",
        data: qs.stringify(spaceOrder),
        headers: { //添加请求头
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
}

/* 更新车位预约信息 */
export function updateSpaceOrder(spaceOrder) {
    return request({
        url: '/spaceOrder/update',
        method: "POST",
        data: qs.stringify(spaceOrder),
        headers: { //添加请求头
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
}

/* 根据主键记录id查询车位预约信息 */
export function getSpaceOrder(orderId) {
    return request({
        url: '/spaceOrder/get/' + orderId,
        method: "POST"
    })
}

/* 根据主键orderId删除车位预约 */
export function deleteSpaceOrder(orderId) {
    return request({
        url: '/spaceOrder/delete/' + orderId,
        method: "POST",
    })
}
