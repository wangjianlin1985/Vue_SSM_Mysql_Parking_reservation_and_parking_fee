<template>
  <div class="toast" v-html="msg" v-show="isShow">
  </div>
</template>

<script>
export default {
  name: 'Toast',
  data() {
    return {
      msg: '',
      isShow: false,
      timer: null
    }
  },
  methods: {
    show(msg='????', delay=1500) {
      this.msg = msg
      this.isShow = true
      if(this.timer) {
        clearTimeout(this.timer)
      }
      this.timer = setTimeout(()=> {
        this.isShow = false
        this.msg = ''
      }, delay)
    },
    hide() {
      this.isShow = false
    },
    loading() {
      this.isShow = true
      this.msg = '<img src="/images/static/loading.gif">'
    }
  }
}
</script>

<style scoped>
.toast {
  padding: 16px 24px;
  background: rgba(0, 0, 0, 0.7);
  border-radius: 10px;
  color: #fff;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 999;
}
.notify {
  position: fixed; 
  z-index: 9999;
  top: 10px;
  left: 10px;
  right: 10px;
  padding: 16px 24px;
  background-color: #333;
  color: #fff;
  border-radius: 6px;
  box-shadow: 0 0 6px rgba(51, 51, 51, .28);;
}
</style>