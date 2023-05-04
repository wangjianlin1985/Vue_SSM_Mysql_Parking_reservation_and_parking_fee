// 留言信息业务处理
import request from './request'
import qs from 'qs'

/* 根据查询条件分页查询留言信息 */
export function queryLeaveword(leaveTitle, userObj_user_name, leaveTime, page) {
    return request({
        url: '/leaveword/list',
        method: "POST",
        params: {
            "leaveTitle": leaveTitle,
            "userObj.user_name": userObj_user_name,
            "leaveTime": leaveTime,
            "page": page,
            "rows": 10
        }
    })
}

/* 查询所有的留言信息*/
export function queryAllLeaveword() {
    return request({
        url: '/leaveword/listAll',
        method: "POST",
    })
}

/* 添加留言信息 */
export function addLeaveword(leaveword) {
    return request({
        url: '/leaveword/add',
        method: "POST",
        data: qs.stringify(leaveword),
        headers: { //添加请求头
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
}

/* 更新留言信息 */
export function updateLeaveword(leaveword) {
    return request({
        url: '/leaveword/update',
        method: "POST",
        data: qs.stringify(leaveword),
        headers: { //添加请求头
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
}

/* 根据主键留言id查询留言信息 */
export function getLeaveword(leaveWordId) {
    return request({
        url: '/leaveword/get/' + leaveWordId,
        method: "POST"
    })
}

/* 根据主键leaveWordId删除留言 */
export function deleteLeaveword(leaveWordId) {
    return request({
        url: '/leaveword/delete/' + leaveWordId,
        method: "POST",
    })
}
