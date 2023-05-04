//文件上传业务处理
import request from './request'

/* 图片文件上传 */
export function uploadImage(formData) {
    return request({
        url: '/upload/image',
        method: "POST",
        data: formData,
        headers: { //添加请求头
            'Content-Type': 'multipart/form-data'
        }
    })
}

/* 普通文件上传处理 */
export function uploadFile(formData) {
    return request({
        url: '/upload/file',
        method: "POST",
        data: formData,
        headers: { //添加请求头
            'Content-Type': 'multipart/form-data'
        }
    })
}


