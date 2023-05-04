import Notify from './Notify'

const obj = {}

obj.install = function(Vue) {

    //1. 创建组件构造器
    const notifyConstructor = Vue.extend(Notify)

    //2. 通过new的方式，根据组件构造器，可以创建出一个组件对象
    const notify = new notifyConstructor()

    //3. 将组件对象，手动挂载到某一元素上
    notify.$mount(document.createElement('div'))

    //4. notify.$el对应的就是div, 把div添加到body上
    document.body.appendChild(notify.$el)

    Vue.prototype.$notify = notify
}

export default obj