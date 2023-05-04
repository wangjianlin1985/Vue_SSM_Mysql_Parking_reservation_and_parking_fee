<template>
  <div id="login">
    <div class="bg"></div>

    <div class="box">
      <nav-bar class="nav">
        <template v-slot:left></template>
        <template v-slot:center>注册登录</template>
      </nav-bar>
      <div class="content">
        <div class="form">
          <p>点击获取即可查看手机验证码</p>
          <div class="label" @click="maskClick">
            <label for="tell">手机号</label>
            <input class="input1" type="text" id="tell" autocomplete="off"
             ref="tell" maxlength="11" v-model="tell" @click="tellClick" @blur="blur">
          </div>
          <div class="label">
            <label for="code">验证码</label>
            <input class="input2" type="text" id="code" autocomplete="off"
             ref="code"  maxlength="4" v-model="code" @click="codeClick" @blur="blur">
            <button class="getCode" @click="getCode" ref="send">获取验证码</button>
          </div>
          <button class="login" @click="login">登录</button>
          
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import NavBar from 'components/common/navbar/NavBar'
import { requestCode, login} from 'network/login'
export default {
  name: 'Login',
  components: {
    NavBar,
  },
  data() {
    return {
      isDisabled: true,
      tell: '18888888888',
      code: null,
      timer: null
    }
  },
  methods: {
    maskClick(){
      //this.$notify.error('测试使用，暂时不开放其他用户')
    },
    tellClick() {
      this.$refs.tell.focus()
    },
    codeClick() {
      this.$refs.code.focus()
    },
    blur() {
      window.scroll(0, 0)
    },
    getCode() { //获取验证码点击
      let tell = this.$refs.tell.value
      if(!tell) {
        this.$toast.show('手机不能为空')
      }else {
        if(!(/^1[3456789]\d{9}$/.test(tell))){ 
          this.$toast.show('手机格式错误')
          return false; 
        }else {
          //请求服务器发送验证码
          requestCode(this.tell).then(ret => {
            this.$notify.success(`验证码为：${ret.data}`)
          })

          //按钮禁用
          this.$toast.show('验证码已发送')
          this.$refs.send.setAttribute('disabled', true)
          let count = 30
          this.$refs.send.innerHTML = count-- +'s'
          this.timer = setInterval(() => {
            this.$refs.send.innerHTML = count-- +'s'
          }, 1000);
          setTimeout(()=> {
            clearInterval(this.timer)
            this.$refs.send.innerHTML = '获取验证码'
            this.$refs.send.removeAttribute('disabled')
          },30000)

        }
      }
    },
    login() { //登录按钮点击
      if(!(/^1[3456789]\d{9}$/.test(this.tell)) || !(/^\d{4}$/.test(this.code))) {
        this.$toast.show('填写错误')
      }else {
        login(this.tell, this.code).then(ret => {
          if(ret.code == 90006) {
            this.$notify.error('验证码错误')
          }else {
            this.$notify.success(ret.msg)
            //保存accessToken
            var authToken = ret.data.authToken
            localStorage.setItem("x-auth-token",authToken)
            localStorage.setItem("username",this.tell)
            if(ret.data.new_flag == 0) {
              //登录成功
              this.$router.replace('/home')
            }else if(ret.data.new_flag == 1) {
              //新用户
              this.$router.replace('/register')
            }
          }
        })
      }
    }
  },
  destroyed() {
    clearInterval(this.timer)
  }
}
</script>

<style scoped>


#login {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  overflow: hidden;
  background-color: #f3f3f3;
}

  .nav {
    background-color: #54bf8e;
    color: #fff;
  }

.box {
  width: 360px;
  height: 442px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate3d(-50%, -50%, 0);
  z-index: 9;
}

.content {
  width: 100%;
  height: 100%;
  background-color: #fff;
  padding: 25px 25px 25px 25px;
  box-shadow: 0 7px 25px rgba(0, 0, 0, .1);
  border-radius: 5px;
}
.box::after {
  content: '';
  width: 320px;
  height: 400px;
  background-color: #fff;
  position: absolute;
  bottom: -6px;
  left: 50%;
  transform: translate3d(-50%, 0, 0);
  z-index: -1;
  border-radius: 0 0 2px 2px;
  box-shadow: 0 0 25px rgba(0, 0, 0, .2);
}
.form {
  width: 100%;
  height: 100%;
}
p {
  text-align: center;
  font-size: 14px;
  font-weight: 600;
  margin: 20px 0;
}
.label {
  width: 100%;
  margin-bottom: 30px;
  font-size: 14px;
}
.login {
  width: 100%;
  height: 48px;
  padding: 15px;
  font-size: 16px;
  text-align: center;
  font-weight: 600;
  vertical-align: middle;
  border: none;
  background-color: #333;
  color: #fff;
  box-shadow: 0 2px 10px 1px rgba(0, 0, 0, .6);
  border-radius: 5px;
}
label {
  width: 100%;
  height: 19px;
  margin-bottom: 5px;
  display: block;
  font-weight: 600;
}
.input1,
.input2 {
  height: 45px;
  border: none;
  background-color: #f3f3f3;
  outline: none;
  text-indent: 1.5em;
  border-radius: 2px;
}
.input1 {
  width: 100%;
}
.input2 {
  width: 68%;
  margin-right: 2%;

}
.getCode {
  width: 30%;
  background-color: #333;
  color: #fff;
  border: none;
  height: 45px;
  border-radius: 2px;
  font-weight: 600;
}
.bg {
  width: 100%;
  height: 11%;
  background: url(~assets/images/fuji.jpg);
  background-size: cover;
}
button:disabled {
  background-color: #f3f3f3;
  color: #333;
}
</style>