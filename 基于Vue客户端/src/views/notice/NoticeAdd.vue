<template>
    <div id="notice">
        <nav-bar class="nav">
            <template v-slot:left><van-icon name="arrow-left" size="25"  @click.stop="back" /></template>
            <template v-slot:center>添加新闻公告</template>
            <template v-slot:right></template>
        </nav-bar>

        <scroll class="content" top="1" bottom="50" ref="scroll" style="padding: 0px 5px">
            <van-form @submit="onSubmit">
                <van-field autosize clearable name="notice.title" v-model="title" label="标题：" placeholder="请输入标题" :rules="[{ required: true, message: '请填写标题' }]" />
                <van-field autosize clearable name="notice.content" v-model="content" label="公告内容：" placeholder="请输入公告内容" :rules="[{ required: true, message: '请填写公告内容' }]" />
                <van-field readonly name="notice.publishDate" label="发布时间" :value="publishDate"   placeholder="选择发布时间" @click="showPublishDatePicker = true" :rules="[{ required: true, message: '发布时间' }]">
                    <template #button>
                        <van-button size="small" type="warning" @click.stop="clearPublishDate">清除</van-button>
                    </template>
                </van-field>
                <van-datetime-picker v-if="showPublishDatePicker"  v-model="picker_publishDate" type="date" @cancel="showPublishDatePicker = false" title="选择发布时间" @confirm="onConfirmPublishDate" />
                <div style="margin: 16px;">
                    <van-button round block type="info" native-type="submit">提交</van-button>
                </div>
            </van-form>
        </scroll>
    </div>
</template>

<script>
    import NavBar from '../../components/common/navbar/NavBar'
    import Scroll from '../../components/common/scroll/Scroll'
    import { addNotice} from "../../network/Notice";

    export default {
        name: "NoticeAdd",
        components: {
            NavBar,
            Scroll,
        },
        data() {
            return {
                title: "", //标题
                content: "", //公告内容
                showPublishDatePicker:false, //是否显示发布时间Picker
                publishDate: '', //发布时间
                picker_publishDate: new Date(), //发布时间Picker的日期
            }
        },

        mounted() {

        },

        created() {
            document.getElementById("tabbar").style.display = "flex";
        },

        methods: {
            back() {
                this.$router.go(-1);
            },

            onSubmit(notice) {  //向后台请求添加新闻公告
                addNotice(notice).then(res => {
                    console.log(res)
                    this.$notify.success('添加成功~')
                    setTimeout(()=>{
                        this.$router.go(-1);
                    },200)
                }).catch(error => { this.$notify.error(error)})
            },
            onConfirmPublishDate(value) {  //选中某个发布时间后的响应
                var year = value.getFullYear();
                var month = (value.getMonth()+1)<10?'0'+ (value.getMonth()+1): (value.getMonth()+1);
                var day = value.getDate()<10?'0'+value.getDate():value.getDate();
                this.publishDate = year + "-" +  month + "-" + day ;
                this.showPublishDatePicker = false;
            },
            clearPublishDate() { //清空发布时间
                this.publishDate='';
                this.showPublishDatePicker=false;
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

    .van-dropdown-menu {  width: 100%; }

</style>
