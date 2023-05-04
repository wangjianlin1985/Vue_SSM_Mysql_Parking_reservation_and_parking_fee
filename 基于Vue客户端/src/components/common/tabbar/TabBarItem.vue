<template>
  <div class="tabbar-item" @click="itemClick" :style="activeStyle">
    <span class="tabbar-item-icon" v-html="icon"></span>
    <span class="tabbar-item-name">
      <slot name="name"></slot>
    </span>
  </div>
</template>

<script>
export default {
  name: 'TabBarItem',
  props: {
    path: String,
    activeColor: {
      type: String,
      default: 'red'
    }, 
    deActiveColor: {
      type: String,
      default: 'Orange'
    },
    icon: {
      type: String,
      default: ''
    }

  },
  methods: {
    itemClick() {
      this.$router.replace(this.path,()=>{})
    }
  },
  computed: {
    isActive() {
      return this.$route.path.indexOf(this.path) !== -1
    },
    activeStyle() {
      return this.isActive ? {color: this.activeColor} : {color: this.deActiveColor}
    }
  }
}
</script>

<style>
  .tabbar-item {
    flex: 1;
    position: relative;
    /* color: #fff; */
  }
  .tabbar-item-icon {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-80%);
    font-family: 'icomoon';
    font-size: 24px;
  }
  .tabbar-item-name {
    position: absolute;
    top: 70%;
    left: 50%;
    transform: translateX(-50%) scale(0.8);
    font-size: 10px;
  }
</style>