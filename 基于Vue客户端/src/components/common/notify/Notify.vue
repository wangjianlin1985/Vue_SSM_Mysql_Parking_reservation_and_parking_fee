<template>
  <div class="notify" :class="{show: isShow, common: type == 'common',success: type == 'success', error: type == 'error'}">
    <div class="text" v-html="msg"></div>
    <span class="close-btn" @click="hide">&times;</span>
  </div>
</template>

<script>
export default {
  name: 'Notify',
  data() {
    return {
      msg: '',
      isShow: false,
      timer: null,
      type: 'common'
    }
  },
  methods: {
    show(msg, delay) {
      this.msg = msg
      this.isShow = true
      if(this.timer) {
        clearTimeout(this.timer)
      }
      this.timer = setTimeout(()=> {
        this.isShow = false
      }, delay)
    },
    hide() {
      this.isShow = false
    },
    success(msg="success", delay=1000) {
      this.type = 'success',
      this.show(msg, delay)
    },
    error(msg="error", delay=1000) {
      this.type = 'error',
      this.show(msg, delay)
    },
    common(msg="success", delay=500) {
      this.type = 'common',
      this.show(msg, delay)
    }
  }
}
</script>

<style scoped>
.notify {
  position: fixed; 
  z-index: 9999;
  top: 0;
  left: 10px;
  right: 10px;
  transform: translate3d(0, -100%, 0);
  padding: 16px 24px;
  background-color: #333;
  font-size: 12px;
  border-radius: 6px;
  box-shadow: 0 0 6px rgba(51, 51, 51, .28);;
  transition: transform .6s;
}
.text {
  width: 100%;
  height: 100%;
  padding-right: 20px;
}
.close-btn {
  width: 24px;
  height: 24px;
  position: absolute;
  top: 10px;
  right: 10px;
  line-height: 24px;
  text-align: center;
  font-size: 18px;
  font-weight: 600;
}
.show {
  transform: translate3d(0, 10px, 0);
}
.common {
  background-color: #fff;
  color: #333;
}
.success {
  background-color: #30ad63;
  color: #fff;
}
.error {
  background-color: #eb2f06;
  color: #fff;
}

</style>