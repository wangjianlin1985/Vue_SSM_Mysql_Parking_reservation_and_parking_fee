// 新闻公告信息业务处理
import request from './request'
import qs from 'qs'

/* 根据查询条件分页查询新闻公告信息 */
export function queryNotice(title, publishDate, page) {
    return request({
        url: '/notice/list',
        method: "POST",
        params: {
            "title": title,
            "publishDate": publishDate,
            "page": page,
            "rows": 10
        }
    })
}

/* 查询所有的新闻公告信息*/
export function queryAllNotice() {
    return request({
        url: '/notice/listAll',
        method: "POST",
    })
}

/* 添加新闻公告信息 */
export function addNotice(notice) {
    return request({
        url: '/notice/add',
        method: "POST",
        data: qs.stringify(notice),
        headers: { //添加请求头
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
}

/* 更新新闻公告信息 */
export function updateNotice(notice) {
    return request({
        url: '/notice/update',
        method: "POST",
        data: qs.stringify(notice),
        headers: { //添加请求头
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    })
}

/* 根据主键公告id查询新闻公告信息 */
export function getNotice(noticeId) {
    return request({
        url: '/notice/get/' + noticeId,
        method: "POST"
    })
}

/* 根据主键noticeId删除新闻公告 */
export function deleteNotice(noticeId) {
    return request({
        url: '/notice/delete/' + noticeId,
        method: "POST",
    })
}
