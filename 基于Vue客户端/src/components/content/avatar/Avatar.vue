<template>
  <div class="avatar-box">
    <avatar-item v-for="(item, index) in position" :key="item" :offset="item"
    @click.native="itemClick(index)" :choose="choose"></avatar-item>
  </div>
</template>

<script>
import AvatarItem from './AvatarItem'
export default {
  name: 'Avatar',
  components: {
    AvatarItem
  },
  data() {
    return {
      position: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24],
      choose: 0
    }
  },
  methods: {
    itemClick(index) {
      this.choose = index
      this.$emit('avatar', index)
    }
  }
}
</script>

<style scoped>
.avatar-box {
  width: 360px;
  height: 370px;
  padding: 10px 5px;
  margin: 10px auto;
  display: grid;
  grid-template-columns: 65px 65px 65px 65px 65px;
  grid-template-rows: 65px 65px 65px 65px 65px;
  grid-gap: 6px;
  border-radius: 5px;
  box-shadow: 1px 1px 5px rgba(0, 0, 0, .5);
  overflow: hidden;
  background-color: #fff;
}

</style>