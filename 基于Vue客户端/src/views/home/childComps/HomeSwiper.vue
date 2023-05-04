<template>
  <div class="swiper-container" :class="{hide: !show}">
    <swiper v-if="list">
      <swiper-item v-for="(item, index) in list" :key="index">
        <img :src="item">
      </swiper-item>
    </swiper>
  </div>
</template>

<script>
import Swiper from 'components/common/swiper/Swiper'
import SwiperItem from 'components/common/swiper/SwiperItem'
export default {
  name: 'HomeSwiper',
  components: {
    Swiper,
    SwiperItem
  },
  data() {
    return {
      show: true,
      list: null
    }
  },
  mounted() {
    this.$bus.$on('aflocation', () => {
      this.show = true
      this.$bus.$off('aflocation')
    })
  },
  created() {

    let list = []
    let swiperList = []

    while(list.length <5 ) {
      let index = Math.round(Math.random() * 10 + 1)
      if(list.indexOf(index)!=-1) {
        //已经有该数字了
        continue
      }
      list.push(index)
    }
    //console.log(list)
    for(let i in list) {
      swiperList.push(`/images/swiper/sw${list[i]}.jpg`)
    }
    this.list = swiperList
    // let list = []
    // for(let i = 0; i < 5 ; i++) {
    //   let index = Math.round(Math.random() * 10 + 1)
    //   list.push(`/public/images/swiper/sw${index}.jpg`)
    // }
    // this.list = list
  }
}
</script>

<style>
  .swiper-container {
    margin: 10px;
    height: 157px;
    background-color: #fff;
    border-radius: 10px;
    /* background: url(~assets/images/sw1.png); */
    /* background-size: cover; */
    position: relative;
    overflow: hidden;
  }
  .hide {
    visibility: hidden;
  }
  img {
    width: 100%;
    /* height: 180px; */
  }
</style>