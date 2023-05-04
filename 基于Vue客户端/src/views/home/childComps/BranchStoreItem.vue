<template>
  <div class="branch-store-item" @click="itemClick">
    <div class="top">
      <span class="name">{{storeInfo.shopName}}</span>
      <span class="address">{{storeInfo.address}}</span>
      <span class="sold">销量：{{storeInfo.sold}}</span>
      <img class="avatar" :src="storeInfo.shopPic"/>
    </div>
    <div class="bottom">
      <span class="distance"><span class="icon">&nbsp;</span>距离 {{storeInfo.distance | distance}}</span>
      <span class="serve"><span class="icon">&nbsp;</span>{{storeInfo.serTime}}</span>
      <span class="tell"><span class="icon">&nbsp;</span>{{storeInfo.tell}}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BranchStore',
  props: {
    storeInfo: {
      type: Object,
      default() {
        return {}
      }
    }
  },
  methods: {
    itemClick() {
      this.$router.replace('shop/' + this.storeInfo.shopId)
    }
  },
  filters: {
    distance(km) {
      if(!km) {
        return '未知'
      }
      return km = km < 1 ? (km*1000).toFixed(0)+' m' : km.toFixed(2)+' km'
    }
  }
}
</script>

<style scoped>
  .branch-store-item {
    background-color: #fff;
    border-radius: 5px;
    margin: 10px;
    box-shadow: 1px 1px 10px rgba(0, 0, 0, .1);
    position: relative;
  }

  .top {
    height: 96px;
    margin: 0 20px;
    border-bottom: 1px  solid #d9d9d9;
  }
  .name {
    position: absolute;
    top: 20px;
    font-size: 16px;
    font-weight: 600;
  }
  .address {
    position: absolute;
    top: 50px;
    font-size: 12px;
    color: #888;
  }
  .sold {
    position: absolute;
    top: 70px;
    font-size: 12px;
    color: #888;
  }
  .bottom {
    position: relative;
    height: 46px;
  }
  .distance,
  .star,
  .serve,
  .tell {
    position: absolute;
    font-size: 12px;
    top: 50%;
    transform: translateY(-50%);
    color: #888;
  }
  .distance {
    left: 20px;
  }
  .serve {
    left: 50%;
    transform: translate(-50%,-50%);
  }
  .tell {
    right: 20px;
  }
  .icon {
    font-family: 'icomoon';
    vertical-align: top;
  }
  .avatar {
    width: 66px;
    height: 66px;
    background-color: #f3f3f3;
    position: absolute;
    top: 20px;
    right: 20px;
    border-radius: 3px;
  }
</style>