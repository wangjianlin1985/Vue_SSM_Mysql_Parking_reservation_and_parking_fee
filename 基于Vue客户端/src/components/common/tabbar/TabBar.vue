<template>
  <div id="tabbar">
    <slot></slot>
  </div>
</template>

<script>

export default {
  name: 'TabBar',

  
}
</script>

<style>
#tabbar {
  width: 100%;
  height: 49px;
  position: fixed;
  bottom: 0;
  left: 0;
  display: flex;
  background-color: #fff;
  box-shadow: 0 -1px 5px rgba(0, 0, 0, .08);
}
</style>