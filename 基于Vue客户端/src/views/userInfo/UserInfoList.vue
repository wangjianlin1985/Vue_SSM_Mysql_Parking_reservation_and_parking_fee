<template>
    <div id="userInfo">
        <van-popup v-model="showQueryUserInfoForm"  closeable position="top" :style="{ height: 'auto' }">
            <div class="userInfoQueryForm">
                <h3>用户查询</h3>
                <van-field clearable v-model="user_name" label="用户名：" placeholder="请输入用户名" />
                <van-field clearable v-model="name" label="姓名：" placeholder="请输入姓名" />
                <van-field readonly label="出生日期" :value="birthDate"   placeholder="选择出生日期" @click="showBirthDatePicker = true">
                    <template #button>
                        <van-button size="small" type="warning" @click.stop="clearBirthDate">清除</van-button>
                    </template>
                </van-field>
                <van-datetime-picker v-if="showBirthDatePicker" v-model="picker_birthDate" type="date" @cancel="showBirthDatePicker = false" title="选择出生日期" @confirm="onConfirmBirthDate" />
                <van-field clearable v-model="telephone" label="联系电话：" placeholder="请输入联系电话" />
                <div class="queryBtn">
                    <van-button round  size= small type="primary" @click="queryUserInfo">查询</van-button>
                </div>
            </div>
        </van-popup>

        <nav-bar class="nav" ref="nav" >
            <template v-slot:left>
                <van-icon name="plus" size="25" v-show="false" @click.stop="userInfoAdd" />
                <van-icon name="arrow-left" v-show="false" size="25"  @click.stop="back" />
            </template>
            <template v-slot:center>用户列表</template>
            <template v-slot:right><van-icon name="search" size="25"  @click.stop="showQueryUserInfoForm=true" /></template>
        </nav-bar>

        <scroll class="content" top="1" bottom="50" ref="scroll" :isPullDown="true" @pullingDown="pullingDown" :isPullUp="true" @pullingUp="pullingUp"  @iscroll="contentScroll">
            <div class="ignore-pull-down1" ref="pull" v-show="pullFlag">下拉更新</div>
            <div class="ignore-pull-down2" ref="pull" v-show="!pullFlag">
                <span class="ball1"></span><span class="ball2"></span><span class="ball3"></span>
            </div>
            <div class="userInfoList">
                <template v-for="userInfo in userInfoList">
                    <div class="userInfoItem" :key="userInfo.user_name" @click.stop="userInfoDetailClick(`${userInfo.user_name}`)">
                        <div class="content_view">
                            <div class="userInfoImg">
                                <img v-lazy="'/' + userInfo.userPhoto">
                            </div>
                            <div class="userInfoText">
                                <div>用户名：{{userInfo.user_name}}</div>
                                <div>姓名：{{userInfo.name}}</div>
                                <div>性别：{{userInfo.gender}}</div>
                                <div>出生日期：{{userInfo.birthDate}}</div>
                            </div>
                        </div>
                        <div class="opt_view" v-show="false">
                            <van-button round  size= small type="primary"  @click.stop="userInfoEditClick(`${userInfo.user_name}`)" >编辑</van-button>
                            <van-button round  size= small type="warning" @click.stop="userInfoDeleteClick(`${userInfo.user_name}`)" style="margin-left:10px;">删除</van-button>
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

    import {queryUserInfo,deleteUserInfo} from '../../network/UserInfo'
    export default {
        name: 'UserInfoList',
        components: {
            NavBar,
            Scroll,
            ReturnTop
        },
        data() {
            return {
                userInfoList: [],  //界面需要展示的用户列表数据
                user_name: "", //用户名查询关键字
                name: "", //姓名查询关键字

                birthDate: "",  //出生日期
                showBirthDatePicker: false, //是否显示选择出生日期Picker
                picker_birthDate: new Date(), //出生日期Picker绑定的日期
                telephone: "", //联系电话查询关键字

                pullFlag: true,  //是否允许下拉刷新标志
                page: 1, //默认显示结果第1页
                showQueryUserInfoForm: false, //是否显示查询用户表单
                isReturnTop: false,
            }
        },

        created() {
            //查询用户信息
            this.userInfoQuery("");
            document.getElementById("tabbar").style.display = "flex";
        },

        methods: {
           back() {
                this.$router.go(-1);
           },

            onConfirmBirthDate(value) {  //选中某个出生日期后的响应
                var year = value.getFullYear();
                var month = (value.getMonth()+1)<10?'0'+ (value.getMonth()+1): (value.getMonth()+1);
                var day = value.getDate()<10?'0'+value.getDate():value.getDate();
                this.birthDate = year + "-" +  month + "-" + day ;
                this.showBirthDatePicker = false;
            },
            clearBirthDate() {  //清空出生日期
               this.birthDate=''
               this.showBirthDatePicker=false;
            },

            userInfoAdd() {
                this.$router.push('/userInfo/add');  //打开用户添加页面
            },

            queryUserInfo() {
                this.page = 1;
                this.userInfoList = [];
                this.pullFlag = false;
                this.showQueryUserInfoForm = false;
                this.userInfoQuery("QUERY");
            },

            userInfoQuery(action) {
                queryUserInfo(this.user_name,this.name,this.birthDate,this.telephone,this.page).then(res => {
                    //this.userInfoList.push(...res.data)
                    this.userInfoList = this.userInfoList.concat(res.data.list)
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

            userInfoDetailClick(user_name) {
                this.$router.push('/userInfo/detail/' + user_name); //跳转到用户详情页
            },
            userInfoEditClick(user_name) {
                this.$router.push('/userInfo/modify/' + user_name); //跳转到用户修改页
            },
            userInfoDeleteClick(user_name) {
                this.$dialog.confirm({
                    title: '删除用户:' + user_name,
                    message: '确定删除吗？'
                }).then(() => {
                    deleteUserInfo(user_name).then(res => {
                        console.log(res)
                        this.userInfoList.forEach(function(userInfo, index, arr) {
                            if(userInfo.user_name == user_name) arr.splice(index, 1);
                        });
                    });
                });
            },

            pullingDown() {  //下拉刷新
                this.page = 1;
                this.userInfoList = [];
                this.pullFlag = false;
                this.userInfoQuery("DOWN");
            },

            pullingUp() {  //上拉加载
                console.log("上拉加载")
                this.page = this.page + 1;
                this.$refs.scroll.finishPullUp();
                this.userInfoQuery("UP");
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
    @import "UserInfoList.css";
    #userInfo {
        height: 100vh;
        width: 100vw;
        position: relative;
    }
</style>
