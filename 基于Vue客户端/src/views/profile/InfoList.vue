<template>
  <div class="info-list" v-if="profile">




    <div class="info-item" @click.stop="carList">
      <div class="title">
        <van-icon name="notes-o" color="red" />&nbsp;&nbsp;我的车辆列表<span class="title-r"><van-icon name="arrow" /></span>
      </div>
      <div class="content"><div class="info-box"></div></div>
    </div>



    <div class="info-item" @click.stop="parkList">
      <div class="title">
        <van-icon name="notes-o" color="red" />&nbsp;&nbsp;我的车辆停车记录<span class="title-r"><van-icon name="arrow" /></span>
      </div>
      <div class="content"><div class="info-box"></div></div>
    </div>



    <div class="info-item" @click.stop="leavewordList">
      <div class="title">
        <van-icon name="notes-o" color="red" />&nbsp;&nbsp;我的留言列表<span class="title-r"><van-icon name="arrow" /></span>
      </div>
      <div class="content"><div class="info-box"></div></div>
    </div>

    <div class="info-item" @click.stop="noticeList">
      <div class="title">
        <van-icon name="notes-o" color="red" />&nbsp;&nbsp;新闻公告列表<span class="title-r"><van-icon name="arrow" /></span>
      </div>
      <div class="content"><div class="info-box"></div></div>
    </div>




    <div class="info-item" @click.stop="modify">
      <div class="title">
        <van-icon name="edit" color="red" />&nbsp;&nbsp;修改个人信息<span class="title-r"><span class="coupon-btn" @click.stop="couponClick" style="display:none;">积分兑换</span></span>
      </div>
      <div class="content"><div class="info-box"> </div></div>
    </div>

    <div class="info-item" @click.stop="about">
      <div class="title">
        <van-icon name="info-o" color="red" />&nbsp;&nbsp;关于我们<span class="title-r"> </span>
      </div>
      <div class="content"><div class="info-box"> </div></div>
    </div>

  </div>
</template>

<script>

export default {
  name: 'InfoList',
  components: {
  },
  props: {
    profile: {
      type: Object,
      default: null
    }
  },
  methods: {

    carList(){
      this.$router.push('/car/list')
    },
    carModelList(){
      this.$router.push('/carModel/list')
    },
    parkList(){
      this.$router.push('/park/list')
    },
    leavewordList(){
      this.$router.push('/leaveword/list')
    },
    noticeList(){
      this.$router.push('/notice/list')
    },


    couponClick() {
      this.$router.replace('/coupon')
    },
    about() {
      this.$router.replace('/about')
    },
    modify() {
      //修改个人信息
      this.$router.replace('/userInfo/modify/self')
    },
    notOpen() {
      this.$notify.error('暂未开放该功能')
    }
  }
}
</script>

<style scoped>
  .info-list {
    width: 360px;
    background-color: #fff;
    margin: 10px auto;
    border-radius: 4px;
    box-shadow: 0 0 6px 1px rgba(0, 0, 0, .1);
    overflow: hidden;
  }
  .info-box {
    background-color: #fff;
    border-bottom: 1px solid #f3f3f3;
  }
  .coupon-btn {
    padding: 8px;
    background-color: #0a3d62;
    font-size: 10px;
    border-radius: 5px;
    color: #fff;
    vertical-align: top;
  }
  .score {
    margin-right: 20px;
    font-size: 12px;
  }


  .info-item {
    width: 100%;
    position: relative;
  }
  .title {
    width: 100%;
    height: 40px;
    background-color: #fff;
    font-size: 16px;
    line-height: 40px;
    padding: 0 10px;
  }
  .info-item .fold-icon {
    position: absolute;
    top: 0px;
    right: 10px;
    font-family: 'icomoon';
    font-size: 20px;
  }
  .info-item  .content {
    width: 100%;
    position: relative;
    top: 0px;
    bottom: 0px;
    background-color: #f3f3f3;
  }
  .title-r {
    float: right;
  }

</style>