//前端路由代码
import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

/**
 * 重写路由的push方法
 */
// const routerPush = Router.prototype.push
// Router.prototype.push = function push(location) {
//     return routerPush.call(this, location).catch(error => error)
// }

const Home = () => import ('../views/home/Home') //引入首页组件页
const UserInfoList = () => import('../views/userInfo/UserInfoList.vue') //引入用户列表组件页
const UserInfoAdd = () => import('../views/userInfo/UserInfoAdd') //引入用户添加组件页
const UserInfoModify = () => import('../views/userInfo/UserInfoModify') //引入用户修改组件页
const UserInfoDetail = () => import('../views/userInfo/UserInfoDetail') //引入用户详情组件页

const RegionList = () => import('../views/region/RegionList.vue') //引入区域列表组件页
const RegionAdd = () => import('../views/region/RegionAdd') //引入区域添加组件页
const RegionModify = () => import('../views/region/RegionModify') //引入区域修改组件页
const RegionDetail = () => import('../views/region/RegionDetail') //引入区域详情组件页

const SpaceInfoList = () => import('../views/spaceInfo/SpaceInfoList.vue') //引入停车位列表组件页
const SpaceInfoAdd = () => import('../views/spaceInfo/SpaceInfoAdd') //引入停车位添加组件页
const SpaceInfoModify = () => import('../views/spaceInfo/SpaceInfoModify') //引入停车位修改组件页
const SpaceInfoDetail = () => import('../views/spaceInfo/SpaceInfoDetail') //引入停车位详情组件页

const CarList = () => import('../views/car/CarList.vue') //引入车辆列表组件页
const CarAdd = () => import('../views/car/CarAdd') //引入车辆添加组件页
const CarModify = () => import('../views/car/CarModify') //引入车辆修改组件页
const CarDetail = () => import('../views/car/CarDetail') //引入车辆详情组件页

const CarModelList = () => import('../views/carModel/CarModelList.vue') //引入车型列表组件页
const CarModelAdd = () => import('../views/carModel/CarModelAdd') //引入车型添加组件页
const CarModelModify = () => import('../views/carModel/CarModelModify') //引入车型修改组件页
const CarModelDetail = () => import('../views/carModel/CarModelDetail') //引入车型详情组件页

const ParkList = () => import('../views/park/ParkList.vue') //引入车辆停车列表组件页
const ParkAdd = () => import('../views/park/ParkAdd') //引入车辆停车添加组件页
const ParkModify = () => import('../views/park/ParkModify') //引入车辆停车修改组件页
const ParkDetail = () => import('../views/park/ParkDetail') //引入车辆停车详情组件页

const LeavewordList = () => import('../views/leaveword/LeavewordList.vue') //引入留言列表组件页
const LeavewordAdd = () => import('../views/leaveword/LeavewordAdd') //引入留言添加组件页
const LeavewordModify = () => import('../views/leaveword/LeavewordModify') //引入留言修改组件页
const LeavewordDetail = () => import('../views/leaveword/LeavewordDetail') //引入留言详情组件页

const NoticeList = () => import('../views/notice/NoticeList.vue') //引入新闻公告列表组件页
const NoticeAdd = () => import('../views/notice/NoticeAdd') //引入新闻公告添加组件页
const NoticeModify = () => import('../views/notice/NoticeModify') //引入新闻公告修改组件页
const NoticeDetail = () => import('../views/notice/NoticeDetail') //引入新闻公告详情组件页

const SpaceOrderList = () => import('../views/spaceOrder/SpaceOrderList.vue') //引入车位预约列表组件页
const SpaceOrderAdd = () => import('../views/spaceOrder/SpaceOrderAdd') //引入车位预约添加组件页
const SpaceOrderModify = () => import('../views/spaceOrder/SpaceOrderModify') //引入车位预约修改组件页
const SpaceOrderDetail = () => import('../views/spaceOrder/SpaceOrderDetail') //引入车位预约详情组件页

const Profile = () => import ('../views/profile/Profile') //引入个人中心组件页
const Login = () => import ('../views/login/Login') //引入登录组件页
const CreateAccount = () => import ('../views/login/CreateAccount') //引入创建账号组件页
const Coupon = () => import ('../views/profile/CouponExchange') //引入优惠卷组件页
const About = () => import ('../views/profile/About') //引入关于我们组件页

const routes = [{
    path: '',  // 根路径跳到登录页路由
    redirect: '/login'
  },
  {
    path: '/login', // 系统登录页路由
    component: Login
  },
  {
    path: '/register', // 创建新账号页路由
    component: CreateAccount
  },
  {
    path: '/home',   // 首页路由
    component: Home
  },

  {
    path: '/userInfo/add', // 添加用户页路由
    component: UserInfoAdd
  },
  {
    path: '/userInfo/modify/:user_name', // 修改用户页路由
    component: UserInfoModify
  },
  {
    path: '/userInfo/list', // 用户查询页路由
    component: UserInfoList
  },
  {
    path: '/userInfo/detail/:user_name', // 用户详情页路由
    component: UserInfoDetail
  },
  {
    path: '/region/add', // 添加区域页路由
    component: RegionAdd
  },
  {
    path: '/region/modify/:regionId', // 修改区域页路由
    component: RegionModify
  },
  {
    path: '/region/list', // 区域查询页路由
    component: RegionList
  },
  {
    path: '/region/detail/:regionId', // 区域详情页路由
    component: RegionDetail
  },
  {
    path: '/spaceInfo/add', // 添加停车位页路由
    component: SpaceInfoAdd
  },
  {
    path: '/spaceInfo/modify/:spaceId', // 修改停车位页路由
    component: SpaceInfoModify
  },
  {
    path: '/spaceInfo/list', // 停车位查询页路由
    component: SpaceInfoList
  },
  {
    path: '/spaceInfo/detail/:spaceId', // 停车位详情页路由
    component: SpaceInfoDetail
  },
  {
    path: '/car/add', // 添加车辆页路由
    component: CarAdd
  },
  {
    path: '/car/modify/:carId', // 修改车辆页路由
    component: CarModify
  },
  {
    path: '/car/list', // 车辆查询页路由
    component: CarList
  },
  {
    path: '/car/detail/:carId', // 车辆详情页路由
    component: CarDetail
  },
  {
    path: '/carModel/add', // 添加车型页路由
    component: CarModelAdd
  },
  {
    path: '/carModel/modify/:modelId', // 修改车型页路由
    component: CarModelModify
  },
  {
    path: '/carModel/list', // 车型查询页路由
    component: CarModelList
  },
  {
    path: '/carModel/detail/:modelId', // 车型详情页路由
    component: CarModelDetail
  },
  {
    path: '/park/add', // 添加车辆停车页路由
    component: ParkAdd
  },
  {
    path: '/park/modify/:parkId', // 修改车辆停车页路由
    component: ParkModify
  },
  {
    path: '/park/list', // 车辆停车查询页路由
    component: ParkList
  },
  {
    path: '/park/detail/:parkId', // 车辆停车详情页路由
    component: ParkDetail
  },
  {
    path: '/leaveword/add', // 添加留言页路由
    component: LeavewordAdd
  },
  {
    path: '/leaveword/modify/:leaveWordId', // 修改留言页路由
    component: LeavewordModify
  },
  {
    path: '/leaveword/list', // 留言查询页路由
    component: LeavewordList
  },
  {
    path: '/leaveword/detail/:leaveWordId', // 留言详情页路由
    component: LeavewordDetail
  },
  {
    path: '/notice/add', // 添加新闻公告页路由
    component: NoticeAdd
  },
  {
    path: '/notice/modify/:noticeId', // 修改新闻公告页路由
    component: NoticeModify
  },
  {
    path: '/notice/list', // 新闻公告查询页路由
    component: NoticeList
  },
  {
    path: '/notice/detail/:noticeId', // 新闻公告详情页路由
    component: NoticeDetail
  },
  {
    path: '/spaceOrder/add', // 添加车位预约页路由
    name: 'spaceOrderAdd',
    component: SpaceOrderAdd
  },

  {
    path: '/spaceOrder/modify/:orderId', // 修改车位预约页路由
    component: SpaceOrderModify
  },
  {
    path: '/spaceOrder/list', // 车位预约查询页路由
    component: SpaceOrderList
  },
  {
    path: '/spaceOrder/detail/:orderId', // 车位预约详情页路由
    component: SpaceOrderDetail
  },

  {
    path: '/Profile', // 个人中心页路由
    component: Profile,
  },
  {
    path: '/about', //关于我们页路由
    component: About
  },
  {
    path: '/coupon', // 优惠卷页路由
    component: Coupon
  }
]

const router = new Router({
  routes,
  mode: 'history'
})

export default router

