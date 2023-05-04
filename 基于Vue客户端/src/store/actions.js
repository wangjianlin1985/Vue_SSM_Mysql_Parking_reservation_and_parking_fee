import {
  ADD_TO_CART,
  ADD_COUNTER
} from './mutaion-types'

export default {

  addCart(context, payload) {
    return new Promise((resolve) => {
      let flag = true
      let cartList = context.state.cartList
      let shopId = payload.shopId
      if (!cartList[shopId]) {
        context.commit('newCart', shopId)
      }
      cartList[shopId].list.forEach((el, index) => {
        if (el.iid == payload.iid) {
          context.commit('addCounter', { shopId: shopId, index: index })
          flag = false
          resolve('数量+1')
        }
      })
      if (flag) {
        payload.count = 1
        context.commit('addToCart', { shopId: shopId, food: payload })
        resolve('添加成功')
      }

    })
  },
  reduceCart(context, payload) {
    return new Promise((resolve) => {
      let cartList = context.state.cartList
      let shopId = payload.shopId
      cartList[shopId].list.forEach((el, index) => {
        if (el.iid == payload.iid) {
          if (el.count > 1) {
            context.commit('reduceFromCart', { shopId: shopId, index: index })
            resolve('数量-1')
          } else {
            context.commit('removeFromCart', { shopId: shopId, index: index })
            resolve('删除成功')
          }
        }
      })
    })
  }
}