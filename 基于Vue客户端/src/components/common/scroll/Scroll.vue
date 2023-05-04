<template>
  <div class="wrapper" ref="wrapper">
    <div class="main" :style="{backgroundColor:bgc, paddingTop:top+'px',paddingBottom:bottom+'px'}">
      <div style="height: 1px"></div>
      <slot></slot>
      <div style="height: 1px"></div>
    </div>
  </div>
</template>

<script>
import BScroll from 'better-scroll'
export default {
  name: 'Scroll',
  data() {
    return {
      scroll: null
    }
  },
  props: {
    probeType: {
      type: Number,
      default: 0
    },
    isPullUp: {
      type: Boolean,
      default: false
    },
    isPullDown: {
      type: Boolean,
      default: false
    },
    bgc: {
      type: String,
      default: 'none'
    },
    top: {
      type: String,
      default: '0'
    },
    bottom: {
      type: String,
      default: '0'
    }
  },
  mounted() {
    let isDown = this.isPullDown ? {threshold: 80, stop: 50} : false
    this.scroll = new BScroll(this.$refs.wrapper, {
      probeType: this.probeType,
      click: true,
      mouseWheel: true,
      pullUpLoad: this.isPullUp,
      pullDownRefresh: isDown
    })
    this.scroll.on('scroll', position => {
      this.$emit('iscroll', position)
    })
    this.isPullUp && this.scroll.on('pullingUp', () => {
      this.$emit('pullingUp')
    })
    this.isPullDown && this.scroll.on('pullingDown', () => {
      this.$emit('pullingDown')
    })
  },
  methods: {
    scrollTo(x, y, time=300) {
      this.scroll && this.scroll.scrollTo(x, y, time)
    },
    finishPullUp() {
      setTimeout(() => {
        this.scroll && this.scroll.finishPullUp()
      }, 300);
    },
    finishPullDown() {
      this.scroll && this.scroll.finishPullDown()
    },
    refresh() {
      // console.log('----')
      this.scroll && this.scroll.refresh()
    },
    stop() {
      this.scroll && this.scroll.stop()
    }
  }
}
</script>

<style>
.wrapper {
  overflow: hidden;
}
.main {
  min-height: calc(100% + 1px);
}
</style>