<template>
  <div class="return-top" :style="{'bottom': bottom+'px', 'right': right+'px'}" @touchMove.prevent @click="goTop"></div>
</template>

<script>
export default {
  name: 'ReturnTop',
  props: {
    bottom: {
      type: String,
      default: '70'
    },
    right: {
      type: String,
      default: '10'
    }
  },
  methods: {
    goTop() {
      this.$emit('returnTop')
    }
  }
}
</script>

<style scoped>
  .return-top {
    height: 42px;
    width: 42px;
    background: rgba(0, 0, 0, .8);
    position: fixed;
    border-radius: 50%;
    font-family: 'icomoon';
    color: #fff;
    font-size: 28px;
    line-height: 42px;
    text-align: center;
    z-index: 9999;
  }
</style>