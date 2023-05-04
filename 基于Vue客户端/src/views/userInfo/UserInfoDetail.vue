<template>
    <div id="userInfo">
        <nav-bar class="nav">
            <template v-slot:left><van-icon name="arrow-left" size="25"  @click.stop="back" /></template>
            <template v-slot:center>用户详情</template>
            <template v-slot:right></template>
        </nav-bar>

        <scroll class="content" top="1" bottom="50" ref="scroll" style="padding: 0px 5px">
            <div class="main-box">
                <div class="info-bar">
                    <span class="title2">&nbsp;用户名：</span>
                    <span class="title3">{{user_name}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;登录密码：</span>
                    <span class="title3">{{password}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;姓名：</span>
                    <span class="title3">{{name}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;性别：</span>
                    <span class="title3">{{gender}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;出生日期：</span>
                    <span class="title3">{{birthDate}}</span>
                </div>
                <div class="info-bar" style="height:140px;">
                    <span class="title2">&nbsp;用户照片：</span>
                    <span class="title3"> <img class="avatar" :src="userPhotoUrl" style="width:auto;height: 100%;" /></span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;联系电话：</span>
                    <span class="title3">{{telephone}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;邮箱：</span>
                    <span class="title3">{{email}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;家庭地址：</span>
                    <span class="title3">{{address}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;注册时间：</span>
                    <span class="title3">{{regTime}}</span>
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
    import { getUserInfo } from "../../network/UserInfo";

    export default {
        name: "UserInfoDetail",
        components: {
            NavBar,
            Scroll,
        },
        data() {
            return {
                user_name: "", //用户名
                password: "", //登录密码
                name: "", //姓名
                gender: "", //性别
                birthDate: "", //出生日期
                userPhotoUrl: '/upload/NoImage.jpg', //用户照片路径
                telephone: "", //联系电话
                email: "", //邮箱
                address: "", //家庭地址
                regTime: "", //注册时间
            }
        },
        created() {
            this.user_name = this.$route.params.user_name; //接收到的用户名
            //向后端请求单条用户记录
            getUserInfo(this.user_name).then(res => {
                var userInfo = res.data;
                this.password = userInfo.password;
                this.name = userInfo.name;
                this.gender = userInfo.gender;
                this.birthDate = userInfo.birthDate;
                this.userPhotoUrl = "/" + userInfo.userPhoto;
                this.telephone = userInfo.telephone;
                this.email = userInfo.email;
                this.address = userInfo.address;
                this.regTime = userInfo.regTime;
            })
          document.getElementById("tabbar").style.display = "flex";
        },
        methods: {
            back() {
                //this.$router.replace('/userInfo/list')
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

    #userInfo {
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
