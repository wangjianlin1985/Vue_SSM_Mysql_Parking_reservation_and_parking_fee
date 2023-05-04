import axios from 'axios'

//携带cookie
axios.defaults.withCredentials = true


export default function request(config, success, failure) {

  const instance = axios.create({
    baseURL: "/api",
    timeout: 5000,
  })


  // 拦截
  instance.interceptors.request.use(config => {
    //config 一些信息不符合服务器要求
    //每次发送网络请求过程 有等待图标转动
    //某些网络请求 需要携带 token（登录）信息
    if (localStorage.getItem("x-auth-token")) {
      config.headers['x-auth-token'] = localStorage.getItem("x-auth-token") // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    window.vue.$toast.loading();
    return config;
  }, err => {
    console.log(err)
  })

  // 响应拦截器
  instance.interceptors.response.use(res => {
      setTimeout(()=>{window.vue.$toast.hide();},200);
          var result = res.data;
          if (result && result.code == '0000') {
             return result;
          } else if (result && result.code == '90001') {
              // TODO 提示用户登录
              window.vue.$router.replace('/login')
              return Promise.reject('请先登录系统');
          } else if (result && result.code == "90005") {
              console.info("Token值失效：" + result.msg);
              window.vue.$router.replace('/login')
              return Promise.reject('Token回话过期，需重新登录');
              /*
              if (!noRefetch) {
                  self._refetch(url, data, successCallback, failCallback)
              }*/
          } else {
              return Promise.reject('系统错误： '+result.msg);
          }

      },
      error => {
        console.log('err' + error)
        window.vue.$toast.show(error.message);
        return Promise.reject(error)
      }
  )

  return instance(config)

}
