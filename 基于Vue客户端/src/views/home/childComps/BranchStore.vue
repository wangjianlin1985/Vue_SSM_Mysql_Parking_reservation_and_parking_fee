<template>
  <div class="branch-store" v-if="storeList.length">
    <branch-store-item key="test" :storeInfo="testStore" v-if="true"></branch-store-item>
    <branch-store-item v-for="(item,index) in storeList" :key="index" :storeInfo="item"></branch-store-item>
  </div>
  <div class="wait" v-else>寻找中，请等候...</div>
</template>

<script>
import BranchStoreItem from './BranchStoreItem'

export default {
  name: 'Branchstore',
  components: {
    BranchStoreItem
  },
  props: {
    storeList: {
      type: Array,
      default() {
        return []
      }
    }
  },
  data() {
    return {
      testStore: {
        "img": [
        "/public/images/shop/sinfo1.jpg",
        "/public/images/shop/sinfo2.jpg",
        "/public/images/shop/sinfo3.jpg"
        ],
        "serTime": "00:00 - 23:59",
        "tip": "测试测试测试测试测试测试测试",
        "discounts": [
        {
        "attain": 0,
        "discount": 0
        }
        ],
        "sold": 0,
        "comment": 0,
        "like": 0,
        "shopId": "test",
        "shopName": "测试店(有真实数据)",
        "tell": "22088888",
        "address": "上西天去咯",
        "shopPic": "/public/images/shop/shop1.jpg",
        "lng": "0",
        "lat": "0",
        "__v": 0,
        "fulladdress": "完整地址"
      }
    }
  }
}
</script>

<style scoped>
.branch-store {
  width: 100%;
  position: relative;
  z-index: -2;
  padding-bottom: 20px;
}
.branch-store::after {
  content: '';
  height: calc(100% + 300px);
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -2;
  background-color: #f3f3f3;
}
.wait {
  margin-top: 200px;
  text-align: center;
  color: #fff;
}
.branch-store::before {
  content: '';
  height: 70px;
  width: 100%;
  position: absolute;
  top: -70px;
  background-color: #f3f3f3;
  border-radius: 10px 10px 0 0 ;
}
</style>