//登录注册相关业务处理

import request from './request'

/* 根据手机号发送验证码 */
export function requestCode(tell) {
  return request({
    url: '/auth/sendSMS',
    params: {
      tel: tell
    }
  })
}

/* 客户端输入手机号和验证码注册或者登陆系统 */
export function login(tell, code) {
  return request({
    url: '/auth/login',
    params: {
      tell: tell,
      code: code
    }
  })
}

/* 新用户注册的时候更新用户昵称和头像 */
export function register(nickName, avatar) {
  return request({
    url: '/auth/register',
    params: {
      nickName: nickName,
      avatar: avatar
    }
  })
}


/* 用户重新登录系统，用于以后token过期的预留接口 */
export function appLogin(username) {
  return request({
    url: '/auth/appLogin',
    method: "POST",
    params: {
      username: username
    }
  })
}

/*获取用户个人信息*/
export function getUserInfo() {
  return request({
    url: '/auth/userInfo'
  })
}

/* 退出系统 */
export function logout() {
  return request({
    url: '/auth/logout'
  })
}