export default {

  newCart(state, shopId) { //初始化购物车
    state.cartList[shopId] = {
      shopId: shopId,
      list: []
    }
  },
  addCounter(state, payload) {
    state.cartList[payload.shopId].list[payload.index].count++
  },
  addToCart(state, payload) {
    state.cartList[payload.shopId].list.push(payload.food)
  },
  reduceFromCart(state, payload) {
    state.cartList[payload.shopId].list[payload.index].count--
  },
  removeFromCart(state, payload) {
    state.cartList[payload.shopId].list.splice(payload.index, 1)
  },
  setInfo(state, payload) {
    state.userInfo = payload
  }
}