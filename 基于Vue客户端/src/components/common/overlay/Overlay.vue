<template>
  <div class="overlay" v-show="isShow" @touchmove.prevent :style="{zIndex: zIndex}">

  </div>
</template>

<script>
export default {
  name: 'Overlay',
  props: {
    zIndex: {
      type: Number,
      default: 0
    },
    isShow: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    show() {
      this.isShow = true
    },
    hide() {
      this.isShow = false
    }
  }
}
</script>

<style scoped>
.overlay {
  background: rgba(0, 0, 0, 0.7);
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
}
</style>