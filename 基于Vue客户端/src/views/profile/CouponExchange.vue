<template>
  <div class="coupon-exchange">
    <nav-bar class="nav">
      <template v-slot:left>
        <div class="back" @click="back"></div>
      </template>
      <template v-slot:center>
        优惠券兑换
      </template>
    </nav-bar>
    <scroll class="coupon-list">
      <div class="score">可用积分：{{score}}</div>
      <div class="coupon-item" v-for="item in couponList" :key="item.iid" :class="{empty: item.rest <= 0}">
        <span class="value"><span style="fontSize: 14px;">&yen;&nbsp;</span>{{item.value}}</span>
        <span class="attain" v-if="item.attain == 0">无门槛使用</span>
        <span class="attain" v-else>满&nbsp;{{item.attain}}&nbsp;元可用</span>
        <img class="coupon-img" src="~assets/images/coupon1.png" v-if="item.attain == 0">
        <img class="coupon-img" src="~assets/images/coupon2.png" v-else>
        <div class="op-block">
          <span class="exchange-btn" @click="exchange(item.iid, item.score)" v-if="item.rest > 0">{{item.score}}&nbsp;积分<br/>兑换</span>
          <span class="exchange-btn" v-else>抢光了</span>
          <span class="limit-rest">{{item.rest}}</span>
        </div>
      </div>
    </scroll>
  </div>
</template>

<script>
import NavBar from 'components/common/navbar/NavBar'
import Scroll from 'components/common/scroll/Scroll'
export default {
  name: 'CouponExchange',
  components: {
    NavBar,
    Scroll
  },
  data() {
    return {
      score: 0,
      couponList: null,
      flag: true
    }
  },
  methods: {
    back() {
      this.$router.replace('/profile')
    },
    formatList(list) {
      let able = []
      let disable = []
      list.forEach(el => {
        if(el.rest >0) {
          able.push(el)
        }else {
          disable.push(el)
        }
      })
      able.push(...disable)
      this.couponList = able
    },
    getCouponMaster() {

    },
    getScore() {

    },
    exchange(iid, score) {
      if(this.score < score) {
        return this.$toast.show('积分不足！')
      }
      this.$dialog.confirm({
        title: '优惠券兑换',
        message: `确认使用 ${score} 积分兑换吗？`
      }).then(() => {
        if(this.flag) {
          this.flag = false
          exchangeCoupon(iid).then(res => {
            if(res.errcode == 1002) {
              this.$toast.show('被抢光了~')
            }else if(res.errcode == 1001) {
              this.$toast.show('兑换失败')
            }else {
              this.score -= score
              this.$toast.show('兑换成功')
              if(res.data) {
                this.formatList(res.data)
              }
            }
            this.flag = true
          })
        }
      }).catch(() => {})
    }
  },
  created() {
    this.getCouponMaster()
    this.getScore()
  }
}
</script>

<style scoped>
.coupon-exchange {
  width: 100vw;
  height: 100vh;
  overflow-x: hidden;
  position: relative;
}
.nav {
  background-color: #54bf8e;
  color: #fff;
}
.coupon-list {
  position: absolute;
  top: 44px;
  left: 0;
  right: 0;
  bottom: 0;
}
.empty {
  filter: grayscale(1);
}
.back {
  font-family: 'icomoon';
  font-size: 30px;
}
.coupon-img {
  width: 100%;
}
.coupon-item {
  margin: 5px 0;
  padding: 0 10px;
  position: relative;
}
span.value {
  position: absolute;
  left: 50%;
  top: 20px;
  transform: translate3d(-50%, 0, 0);
  font-size: 24px;
  color: #eb2f06;
}
span.attain {
  position: absolute;
  bottom: 30px;
  left: 50%;
  transform: translate3d(-50%, 0, 0);
  color: #888;
  font-size: 12px;
}
.op-block {
  position: absolute;
  height: calc(100% - 16px);
  top: 6px;
  right: 13px;
  width: 90px;
}
span.exchange-btn {
  font-size: 14px;
  color: #333;
  position: absolute;
  white-space: nowrap;
  top: 50%;
  left: 50%;
  transform: translate3d(-50%, -50%, 0);
  text-align: center;
  line-height: 20px;
}
span.limit-rest {
  position: absolute;
  top: 0;
  right: 0;
  transform: translate3d(30%, -50%, 0);
  font-size: 12px;
  color: #FFF;
  background-color: #eb2f06;
  padding: 2px 5px 1px 5px;
  border-radius: 10px;
}
.score {
  height: 49px;
  line-height: 49px;
  text-align: center;
}
</style>