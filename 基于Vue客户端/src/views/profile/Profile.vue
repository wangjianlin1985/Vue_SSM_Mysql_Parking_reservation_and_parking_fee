<template>
  <div class="profile">
    <scroll class="content" v-if="profile" bottom="80">
      <!-- 头部头像、昵称 -->
      <div class="head">
        <div class="avatar">
          <img :src="profile.userPhotoUrl" alt="">
        </div>
        <span class="nickName">{{profile.name}}</span>
      </div>
      <!-- 信息列表 -->
      <info-list :profile="profile"></info-list>
      <div class="logout" @click="logout">退出登录</div>
    </scroll>

  </div>
</template>

<script>
import Scroll from 'components/common/scroll/Scroll'

import InfoList from './InfoList'

import {getUserInfo, logout} from '../../network/login'

export default {
  name: 'Profile',
  components: {
    Scroll,
    InfoList
  },
  data() {
    return {
      profile: null
    }
  },
  created() {
    getUserInfo().then(ret => {
      this.profile = ret.data
    })

  },
  methods: {
    logout() {
      this.$dialog.confirm({
        title: '退出登录',
        message: '确定退出吗？'
      }).then(() => {
          logout().then(ret => {
          localStorage.clear()
          this.$router.replace('/login')
        })
      }).catch(() => {})
    },
  }
}
</script>

<style scoped>
.content {
  position: absolute;
  top: 0;
  left: 0;
  right: 0px;
  bottom: 49px;
}
.head {
  width: 100%;
  height: 120px;
  background: url(~assets/images/profile.jpg);
  background-size: cover;
  position: relative;
}
.avatar {
  width: 80px;
  height: 80px;
  background-color: rgba(0, 0, 0, .3);
  border-radius: 5px;
  position: absolute;
  bottom: 10px;
  left: 10px;
  overflow: hidden;
  box-shadow: 0px 0px 6px 1px rgba(255, 255, 255, 0.4);
}
.avatar img {
  width: 100%;
  height: 100%;
}
.head .nickName {
  font-size: 20px;
  position: absolute;
  bottom: 10px;
  left: 100px;
  color: #fff;
}
.logout {
  height: 49px;
  background-color: #333;
  color: #fff;
  margin: 10px;
  line-height: 49px;
  text-align: center;
  border-radius: 5px;
  box-shadow: 0px 0px 10px 3px rgba(0, 0, 0, 0.26);
}
</style>