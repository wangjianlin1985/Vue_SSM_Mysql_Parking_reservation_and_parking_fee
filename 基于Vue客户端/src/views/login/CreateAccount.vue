<template>
  <div id="create-account">
    <nav-bar class="nav">
      <template v-slot:left>
        <div class="back" @click="back"></div>
      </template>
      <template v-slot:center>创建账户</template>
    </nav-bar>
    <div class="info">
      <div class="avatar" :style="{backgroundPosition: '0'+' '+(-offset)+'em'}"></div>
      <input class="editName" type="text"  maxlength="6" placeholder="请编辑用户名"
       v-model.trim="nickName" v-if="isEdit" @click="focus" ref="in">
      <div class="nickName" v-else>{{nickName}}</div>
      <div class="check" v-if="isEdit"  @click="toggle"></div>
      <div class="edit" v-else @click="toggle"></div>
    </div>
    <avatar @avatar="avatarChange"></avatar>
    <button class="register" @click="register">完成注册</button>
  </div>
</template>

<script>
import NavBar from 'components/common/navbar/NavBar'
import Avatar from 'components/content/avatar/Avatar'
import {register} from 'network/login'
export default {
  name: 'CreateAccount',
  components: {
    NavBar,
    Avatar
  },
  data() {
    return {
      nickName: null,
      isEdit: true,
      offset: 0
    }
  },
  methods: {
    register() {
      if(this.nickName) {
        register(this.nickName, this.offset).then(ret => {
          this.$toast.show("注册成功")
          this.$router.replace('/home')
        })
      }else {
        this.$toast.show('请填写用户名')
      }
    },
    toggle() {
      this.isEdit = !this.isEdit
    },
    focus() {
      this.$refs.in.focus()
    },
    avatarChange(index) {
      this.offset = index
    },
    back() {
      this.$router.replace('/login')
    }
  }
}
</script>

<style scoped>
#create-account {
  width: 100vw;
}
.nav {
  background-color: #333;
  color: #fff;
  position: fixed;
  top: 0;
  z-index: 9;
}
.back {
  font-family: 'icomoon';
  font-size: 30px;
}
.info {
  width: 100%;
  height: 90px;
  margin: 0 auto;
  /* background-color: pink; */
  margin-top: 60px;
  position: relative;
}
.avatar {
  font-size: 88px;
  width: 88px;
  height: 88px;
  border-radius: 50%;
  position: absolute;
  top: 0;
  left: 20px;
  background: url(~assets/images/avatar.png);
  background-size: 88px auto;
  box-shadow: 0px 0px 5px 1px rgba(0, 0, 0, .2);
}
.editName {
  width: 120px;
  position: absolute; 
  bottom: 0;
  left: 50%;
  transform: translate3d(-50%, 0, 0);
}
.nickName {
  width: 120px;
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translate3d(-50%, 0, 0);
  z-index: 2;
  font-size: 20px;
  border-bottom: 1px solid #000;
}
.edit,
.check {
  width: 20px;
  height: 20px;
  position: absolute;
  bottom: 0;
  right: 100px;
  font-family: 'icomoon';
  color: #000;
  font-size: 20px;
}
.register {
  width: 80%;
  height: 49px;
  background-color: #333;
  border: none;
  display: block;
  margin: 0 auto;
  color: #fff;
  border-radius: 4px;
  font-size: 16px;
  box-shadow: 0 1px 10px rgba(0, 0, 0, .5);
  margin-top: 20px;
}
.editName {
  background: none;
  border-radius: 0;
  border: none;
  outline: none;
  border-bottom: 1px solid #000;
  font-size: 20px;
}

</style>