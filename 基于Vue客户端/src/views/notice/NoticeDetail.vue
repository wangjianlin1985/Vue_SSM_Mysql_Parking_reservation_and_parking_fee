<template>
    <div id="notice">
        <nav-bar class="nav">
            <template v-slot:left><van-icon name="arrow-left" size="25"  @click.stop="back" /></template>
            <template v-slot:center>新闻公告详情</template>
            <template v-slot:right></template>
        </nav-bar>

        <scroll class="content" top="1" bottom="50" ref="scroll" style="padding: 0px 5px">
            <div class="main-box">
                <div class="info-bar">
                    <span class="title2">&nbsp;公告id：</span>
                    <span class="title3">{{noticeId}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;标题：</span>
                    <span class="title3">{{title}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;公告内容：</span>
                    <span class="title3">{{content}}</span>
                </div>
                <div class="info-bar">
                    <span class="title2">&nbsp;发布时间：</span>
                    <span class="title3">{{publishDate}}</span>
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
    import { getNotice } from "../../network/Notice";

    export default {
        name: "NoticeDetail",
        components: {
            NavBar,
            Scroll,
        },
        data() {
            return {
                noticeId: "", //公告id
                title: "", //标题
                content: "", //公告内容
                publishDate: "", //发布时间
            }
        },
        created() {
            this.noticeId = this.$route.params.noticeId; //接收到的公告id
            //向后端请求单条新闻公告记录
            getNotice(this.noticeId).then(res => {
                var notice = res.data;
                this.title = notice.title;
                this.content = notice.content;
                this.publishDate = notice.publishDate;
            })
          document.getElementById("tabbar").style.display = "flex";
        },
        methods: {
            back() {
                //this.$router.replace('/notice/list')
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

    #notice {
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
