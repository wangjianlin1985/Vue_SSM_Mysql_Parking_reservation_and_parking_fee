<template>
  <div class="about">
    <nav-bar class="nav">
      <template v-slot:left>
        <div class="back" @click="back"></div>
      </template>
      <template v-slot:center>
        关于系统
      </template>
    </nav-bar>
    <scroll class="about-content">
      <p>
      XX互联创想科技有限公司是一家致力于互联网产品设计、开发、销售为一体的公司。
      </p>
      <p>
        公司一贯注重高新技术的不断探索与提升，凭借优秀的研发团队，丰富的行业经验积累，自行研发出多款实用性很强的互联网产品。对诸多企业的信息化建设，从运营、管理、到盈利起到了不容忽视的作用。
      </p>
      <p>
        截止目前，据不完全统计，我们的产品在互联网上已经突破了数万次的下载与安装量。不论是从产品自身的建设、或者是从产品的售前和售后服务等，我们都对其进行 严格把关，从而获得了良好的用户口碑与相当优秀的客户好评率，同时也积累了相当一部分的忠实客户。这也是我们飞速发展的一个重要原因，同时也是我们的宗旨和荣誉。
      </p>
      <p>公司电话：028-82939234</p>
      <p>公司地址：四川省成都市红星路二段10号</p>

    </scroll>
  </div>
</template>

<script>
import NavBar from 'components/common/navbar/NavBar'
import Scroll from 'components/common/scroll/Scroll'



export default {
  name: 'About',
  components: {
    NavBar,
    Scroll
  },
  data() {
    return {
      score: 0,
      couponList: null,
      flag: true,


    }
  },
  methods: {
    back() {
      this.$router.replace('/profile')
    },
  },
  created() {
  }
}
</script>

<style scoped>
.about {
  width: 100vw;
  height: 100vh;
  overflow-x: hidden;
  position: relative;
}
.nav {
  background-color: #54bf8e;
  color: #fff;
}
.about-content {

}
.empty {
  filter: grayscale(1);
}
.back {
  font-family: 'icomoon';
  font-size: 30px;
}


.about-content {
  position: absolute;
  top: 44px;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #fff;
  padding: 0px;
  padding-bottom: 10px;
  overflow-x: scroll;
  box-shadow: 0 1px 6px rgba(0, 0, 0, .08);
}


.about-content p{
  font-size: 12px;
  margin-top: 10px;
  color: #3c3c3c;
  line-height: 25px;
  text-indent: 25px;
}


</style>