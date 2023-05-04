<template>
  <div id="location">
    <span class="icon"></span>
    <span class="info">{{locationInfo}}</span>
  </div>
</template>

<script>
export default {
  name: 'Location',
  props: {
    locationInfo: {
      type: String,
      default: null
    }
  }
}
</script>

<style scoped>
  #location {
    width: 100%;
    height: 66px;
    /* background-color: #4fc08d; */
    position: fixed;
  }
  .icon ,
  .info {
    position: absolute;
    bottom: 20px;
    font-size: 18px;
    color: #fff;
  }
  .info {
    left: 40px;
    width: 170px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis; 
  }
  .icon {
    font-family: 'icomoon';
    font-size: 20px;
    left: 14px;
  }
</style>