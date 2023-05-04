<template>
  <div class="avatar-item" :class="{choose: offset==choose}">
    <div class="img"  :style="{backgroundPosition: '0' + ' ' + (-offset) +'em'}"></div>
  </div>
</template>

<script>
export default {
  name: 'AvatarItem',
  props: {
    offset: {
      type: Number,
      default: 0
    },
    choose: {
      type: Number,
      default: 0
    }
  }
}
</script>

<style scoped>
  .choose {
    visibility: hidden;
  }
  .img {
    width: 100%;
    height: 100%;
    font-size: 65px;
    background: url(~assets/images/avatar.png);
    background-size: 65px auto;
  }
</style>