<template>
    <div id="leaveword">
        <nav-bar class="nav">
            <template v-slot:left><van-icon name="arrow-left" size="25"  @click.stop="back" /></template>
            <template v-slot:center>留言详情</template>
            <template v-slot:right></template>
        </nav-bar>

        <scroll class="content" top="1" bottom="50" ref="scroll" style="padding: 0px 5px">
            <div class="main-box">
                <div class="info-bar">
                    <span class="title2">&nbsp;留言id：</span>
                    <span class="title3">{{leaveWordId}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;留言标题：</span>
                    <span class="title3">{{leaveTitle}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;留言内容：</span>
                    <span class="title3">{{leaveContent}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;留言人：</span>
                    <span class="title3">{{userObj_name}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;留言时间：</span>
                    <span class="title3">{{leaveTime}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;管理回复：</span>
                    <span class="title3">{{replyContent}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;回复时间：</span>
                    <span class="title3">{{replyTime}}</span>
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
    import { getLeaveword } from "../../network/Leaveword";

    export default {
        name: "LeavewordDetail",
        components: {
            NavBar,
            Scroll,
        },
        data() {
            return {
                leaveWordId: "", //留言id
                leaveTitle: "", //留言标题
                leaveContent: "", //留言内容
                userObj_name: "", //留言人
                leaveTime: "", //留言时间
                replyContent: "", //管理回复
                replyTime: "", //回复时间
            }
        },
        created() {
            this.leaveWordId = this.$route.params.leaveWordId; //接收到的留言id
            //向后端请求单条留言记录
            getLeaveword(this.leaveWordId).then(res => {
                var leaveword = res.data;
                this.leaveTitle = leaveword.leaveTitle;
                this.leaveContent = leaveword.leaveContent;
                this.userObj_name = leaveword.userObj.name;
                this.leaveTime = leaveword.leaveTime;
                this.replyContent = leaveword.replyContent;
                this.replyTime = leaveword.replyTime;
            })
          document.getElementById("tabbar").style.display = "flex";
        },
        methods: {
            back() {
                //this.$router.replace('/leaveword/list')
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

    #leaveword {
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
