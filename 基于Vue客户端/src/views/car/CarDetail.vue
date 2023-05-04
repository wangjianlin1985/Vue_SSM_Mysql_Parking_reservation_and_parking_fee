<template>
    <div id="car">
        <nav-bar class="nav">
            <template v-slot:left><van-icon name="arrow-left" size="25"  @click.stop="back" /></template>
            <template v-slot:center>车辆详情</template>
            <template v-slot:right></template>
        </nav-bar>

        <scroll class="content" top="1" bottom="50" ref="scroll" style="padding: 0px 5px">
            <div class="main-box">
                <div class="info-bar">
                    <span class="title2">&nbsp;车辆id：</span>
                    <span class="title3">{{carId}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;车牌：</span>
                    <span class="title3">{{carNo}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;车型：</span>
                    <span class="title3">{{carModelObj_modelName}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;品牌：</span>
                    <span class="title3">{{pinpai}}</span>
                </div>
                <div class="info-bar" style="height:140px;">
                    <span class="title2">&nbsp;车辆照片：</span>
                    <span class="title3"> <img class="avatar" :src="carPhotoUrl" style="width:auto;height: 100%;" /></span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;油型：</span>
                    <span class="title3">{{youxing}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;耗油量：</span>
                    <span class="title3">{{haoyouliang}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;车险日期：</span>
                    <span class="title3">{{chexianriqi}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;总里程：</span>
                    <span class="title3">{{zonglicheng}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;车辆详情:</span>
                    <span class="desc" v-html="carDesc"> </span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;所属用户：</span>
                    <span class="title3">{{userObj_name}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;登记时间：</span>
                    <span class="title3">{{addTime}}</span>
                </div>
            </div>
            <div style="margin: 16px;">
                <van-button round block type="info" native-type="submit" @click.stop="back">返回</van-button>
            </div>

        </scroll>
    </div>
</template>

<script>
    import NavBar from '../../components/common/navbar/NavBar'
    import Scroll from '../../components/common/scroll/Scroll'
    import { getCar } from "../../network/Car";

    export default {
        name: "CarDetail",
        components: {
            NavBar,
            Scroll,
        },
        data() {
            return {
                carId: "", //车辆id
                carNo: "", //车牌
                carModelObj_modelName: "", //车型
                pinpai: "", //品牌
                carPhotoUrl: '/upload/NoImage.jpg', //车辆照片路径
                youxing: "", //油型
                haoyouliang: "", //耗油量
                chexianriqi: "", //车险日期
                zonglicheng: "", //总里程
                carDesc: "", //车辆详情
                userObj_name: "", //所属用户
                addTime: "", //登记时间
            }
        },
        created() {
            this.carId = this.$route.params.carId; //接收到的车辆id
            //向后端请求单条车辆记录
            getCar(this.carId).then(res => {
                var car = res.data;
                this.carNo = car.carNo;
                this.carModelObj_modelName = car.carModelObj.modelName;
                this.pinpai = car.pinpai;
                this.carPhotoUrl = "/" + car.carPhoto;
                this.youxing = car.youxing;
                this.haoyouliang = car.haoyouliang;
                this.chexianriqi = car.chexianriqi;
                this.zonglicheng = car.zonglicheng;
                this.carDesc = car.carDesc;
                this.userObj_name = car.userObj.name;
                this.addTime = car.addTime;
            })
          document.getElementById("tabbar").style.display = "flex";
        },
        methods: {
            back() {
                //this.$router.replace('/car/list')
                this.$router.go(-1);
            },
        },
    }
</script>

<style scoped>
    .nav {
        background-color: #54bf8e;
        color: #fff;
    }

    .back {
        font-family: 'icomoon';
        font-size: 30px;
    }

    #car {
        height: 100vh;
        width: 100vw;
        position: relative;
    }

    .content {
        position: fixed;
        top: 50px;
        left: 0;
        right: 0px;
        bottom: 49px;
    }

    .header {
        background-color: #fff;
        position: relative;
        padding: 0 15px;
        padding-bottom: 10px;
        overflow-x: scroll;
        box-shadow: 0 1px 6px rgba(0, 0, 0, .08);
    }

    .header p{
        margin-top: 40px;
        color: #3c3c3c;
        line-height: 25px;
        text-indent: 25px;
    }

    .main-box {
        background-color: #fff;
        margin: 10px 0;
        box-shadow: 0 1px 6px rgba(0, 0, 0, .08);
    }

    .info-bar {
        height: auto;
        border-bottom: 1px solid #f3f3f3;
        padding: 0 15px;
        display: block;
        clear:both;
    }

    .title2 {
        width:35%;
        line-height: 46px;
        font-weight: 600;
        font-family: 'icomoon';
    }

    .title3 {
        width: auto;
        height: 100%;
        text-align: right;
        line-height: 46px;
        color: #3c3c3c;
        float: right;
    }

    .desc {
        width: 100%;
        line-height: 25px;
        color: #3c3c3c;
        clear:both;
    }

    .avatar {
        width: 100px;
        height: 100px;
        background-color: #f3f3f3;
        border-radius: 3px;
    }
</style>
