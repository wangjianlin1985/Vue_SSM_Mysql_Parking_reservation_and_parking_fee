import Overlay from './Overlay'

const obj = {}

obj.install = function(Vue) {

  //1. 创建组件构造器
  const overlayConstructor = Vue.extend(Overlay)

  //2. 通过new的方式，根据组件构造器，可以创建出一个组件对象
  const overlay = new overlayConstructor()

  //3. 将组件对象，手动挂载到某一元素上
  overlay.$mount(document.createElement('div'))

  //4. overlay.$el对应的就是div, 把div添加到body上
  document.body.appendChild(overlay.$el)

  Vue.prototype.$overlay = overlay
}

export default obj