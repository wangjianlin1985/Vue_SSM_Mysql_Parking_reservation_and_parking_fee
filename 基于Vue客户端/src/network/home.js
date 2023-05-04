import request from './request'

export function getShop() {
  return request({
    url: '/shop'
  })
}