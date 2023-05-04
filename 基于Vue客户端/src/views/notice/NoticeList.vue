<template>
    <div id="notice">
        <van-popup v-model="showQueryNoticeForm"  closeable position="top" :style="{ height: 'auto' }">
            <div class="noticeQueryForm">
                <h3>新闻公告查询</h3>
                <van-field clearable v-model="title" label="标题：" placeholder="请输入标题" />
                <van-field readonly label="发布时间" :value="publishDate"   placeholder="选择发布时间" @click="showPublishDatePicker = true">
                    <template #button>
                        <van-button size="small" type="warning" @click.stop="clearPublishDate">清除</van-button>
                    </template>
                </van-field>
                <van-datetime-picker v-if="showPublishDatePicker" v-model="picker_publishDate" type="date" @cancel="showPublishDatePicker = false" title="选择发布时间" @confirm="onConfirmPublishDate" />
                <div class="queryBtn">
                    <van-button round  size= small type="primary" @click="queryNotice">查询</van-button>
                </div>
            </div>
        </van-popup>

        <nav-bar class="nav" ref="nav" >
            <template v-slot:left>
                <van-icon name="plus" size="25" v-show="false" @click.stop="noticeAdd" />
                <van-icon name="arrow-left" v-show="false" size="25"  @click.stop="back" />
            </template>
            <template v-slot:center>新闻公告列表</template>
            <template v-slot:right><van-icon name="search" size="25"  @click.stop="showQueryNoticeForm=true" /></template>
        </nav-bar>

        <scroll class="content" top="1" bottom="50" ref="scroll" :isPullDown="true" @pullingDown="pullingDown" :isPullUp="true" @pullingUp="pullingUp"  @iscroll="contentScroll">
            <div class="ignore-pull-down1" ref="pull" v-show="pullFlag">下拉更新</div>
            <div class="ignore-pull-down2" ref="pull" v-show="!pullFlag">
                <span class="ball1"></span><span class="ball2"></span><span class="ball3"></span>
            </div>
            <div class="noticeList">
                <template v-for="notice in noticeList">
                    <div class="noticeItem" :key="notice.noticeId" @click.stop="noticeDetailClick(`${notice.noticeId}`)">
                        <div class="content_view">
                            <div class="noticeText">
                                <div>标题：{{notice.title}}</div>
                                <div>发布时间：{{notice.publishDate}}</div>
                            </div>
                        </div>
                        <div class="opt_view" v-show="false">
                            <van-button round  size= small type="primary"  @click.stop="noticeEditClick(`${notice.noticeId}`)" >编辑</van-button>
                            <van-button round  size= small type="warning" @click.stop="noticeDeleteClick(`${notice.noticeId}`)" style="margin-left:10px;">删除</van-button>
                        </div>
                    </div>
                </template>
            </div>
            <div class="block"></div>
        </scroll>
        <return-top @returnTop="returnTop" v-show="isReturnTop"></return-top>
    </div>
</template>

<script>
    import NavBar from '../../components/common/navbar/NavBar'
    import Scroll from '../../components/common/scroll/Scroll'
    import ReturnTop from '../../components/common/returntop/ReturnTop'

    import {queryNotice,deleteNotice} from '../../network/Notice'
    export default {
        name: 'NoticeList',
        components: {
            NavBar,
            Scroll,
            ReturnTop
        },
        data() {
            return {
                noticeList: [],  //界面需要展示的新闻公告列表数据
                title: "", //标题查询关键字

                publishDate: "",  //发布时间
                showPublishDatePicker: false, //是否显示选择发布时间Picker
                picker_publishDate: new Date(), //发布时间Picker绑定的日期

                pullFlag: true,  //是否允许下拉刷新标志
                page: 1, //默认显示结果第1页
                showQueryNoticeForm: false, //是否显示查询新闻公告表单
                isReturnTop: false,
            }
        },

        created() {
            //查询新闻公告信息
            this.noticeQuery("");
            document.getElementById("tabbar").style.display = "flex";
        },

        methods: {
           back() {
                this.$router.go(-1);
           },

            onConfirmPublishDate(value) {  //选中某个发布时间后的响应
                var year = value.getFullYear();
                var month = (value.getMonth()+1)<10?'0'+ (value.getMonth()+1): (value.getMonth()+1);
                var day = value.getDate()<10?'0'+value.getDate():value.getDate();
                this.publishDate = year + "-" +  month + "-" + day ;
                this.showPublishDatePicker = false;
            },
            clearPublishDate() {  //清空发布时间
               this.publishDate=''
               this.showPublishDatePicker=false;
            },

            noticeAdd() {
                this.$router.push('/notice/add');  //打开新闻公告添加页面
            },

            queryNotice() {
                this.page = 1;
                this.noticeList = [];
                this.pullFlag = false;
                this.showQueryNoticeForm = false;
                this.noticeQuery("QUERY");
            },

            noticeQuery(action) {
                queryNotice(this.title,this.publishDate,this.page).then(res => {
                    //this.noticeList.push(...res.data)
                    this.noticeList = this.noticeList.concat(res.data.list)
                    if(action == "QUERY") {
                        this.$notify.success('查询成功~');
                        this.$refs.scroll.refresh();
                    }
                    if(action == "UP") {  //上拉加载操作
                        if(res.data.list.length < 10){
                            this.$notify.error('没有更多数据了~')
                            return;
                        }
                        this.$refs.scroll.refresh();
                    }
                    if(action == "DOWN") {  //下拉刷新操作
                        this.$notify.success('刷新成功~');
                        this.$refs.scroll.finishPullDown();
                        this.$refs.scroll.refresh();
                        this.pullFlag = true
                    }
                })
            },

            noticeDetailClick(noticeId) {
                this.$router.push('/notice/detail/' + noticeId); //跳转到新闻公告详情页
            },
            noticeEditClick(noticeId) {
                this.$router.push('/notice/modify/' + noticeId); //跳转到新闻公告修改页
            },
            noticeDeleteClick(noticeId) {
                this.$dialog.confirm({
                    title: '删除新闻公告:' + noticeId,
                    message: '确定删除吗？'
                }).then(() => {
                    deleteNotice(noticeId).then(res => {
                        console.log(res)
                        this.noticeList.forEach(function(notice, index, arr) {
                            if(notice.noticeId == noticeId) arr.splice(index, 1);
                        });
                    });
                });
            },

            pullingDown() {  //下拉刷新
                this.page = 1;
                this.noticeList = [];
                this.pullFlag = false;
                this.noticeQuery("DOWN");
            },

            pullingUp() {  //上拉加载
                console.log("上拉加载")
                this.page = this.page + 1;
                this.$refs.scroll.finishPullUp();
                this.noticeQuery("UP");
            },

            returnTop() {
                this.$refs.scroll.scrollTo(0, 0)
            },

            //滚动事件
            contentScroll(position) {
                this.isReturnTop = position.y < -800
            },
        }
    }
</script>

<style scoped>
    @import "../../assets/css/list_base.css";
    @import "NoticeList.css";
    #notice {
        height: 100vh;
        width: 100vw;
        position: relative;
    }
</style>
