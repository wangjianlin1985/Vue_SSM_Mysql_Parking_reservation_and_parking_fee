<template>
    <div id="leaveword">
        <van-popup v-model="showQueryLeavewordForm"  closeable position="top" :style="{ height: 'auto' }">
            <div class="leavewordQueryForm">
                <h3>留言查询</h3>
                <van-field clearable v-model="leaveTitle" label="留言标题：" placeholder="请输入留言标题" />
                <van-field v-show="false" readonly clickable label="留言人" :value="userObj_name" right-icon="arrow-down" placeholder="选择留言人" @click="showUserObjPicker = true" />
                <van-popup v-model="showUserObjPicker" position="bottom">
                    <van-picker show-toolbar :columns="userObjColumns" @cancel="showUserObjPicker = false" @confirm="onConfirmUserObj" />
                </van-popup>
                <van-field readonly label="留言时间" :value="leaveTime"   placeholder="选择留言时间" @click="showLeaveTimePicker = true">
                    <template #button>
                        <van-button size="small" type="warning" @click.stop="clearLeaveTime">清除</van-button>
                    </template>
                </van-field>
                <van-datetime-picker v-if="showLeaveTimePicker" v-model="picker_leaveTime" type="date" @cancel="showLeaveTimePicker = false" title="选择留言时间" @confirm="onConfirmLeaveTime" />
                <div class="queryBtn">
                    <van-button round  size= small type="primary" @click="queryLeaveword">查询</van-button>
                </div>
            </div>
        </van-popup>

        <nav-bar class="nav" ref="nav" >
            <template v-slot:left>
                <van-icon name="plus" size="25" v-show="true" @click.stop="leavewordAdd" />
                <van-icon name="arrow-left" v-show="false" size="25"  @click.stop="back" />
            </template>
            <template v-slot:center>留言列表</template>
            <template v-slot:right><van-icon name="search" size="25"  @click.stop="showQueryLeavewordForm=true" /></template>
        </nav-bar>

        <scroll class="content" top="1" bottom="50" ref="scroll" :isPullDown="true" @pullingDown="pullingDown" :isPullUp="true" @pullingUp="pullingUp"  @iscroll="contentScroll">
            <div class="ignore-pull-down1" ref="pull" v-show="pullFlag">下拉更新</div>
            <div class="ignore-pull-down2" ref="pull" v-show="!pullFlag">
                <span class="ball1"></span><span class="ball2"></span><span class="ball3"></span>
            </div>
            <div class="leavewordList">
                <template v-for="leaveword in leavewordList">
                    <div class="leavewordItem" :key="leaveword.leaveWordId" @click.stop="leavewordDetailClick(`${leaveword.leaveWordId}`)">
                        <div class="content_view">
                            <div class="leavewordText">
                                <div>留言标题：{{leaveword.leaveTitle}}</div>
                                <div>留言人：{{leaveword.userObj.name}}</div>
                                <div>留言时间：{{leaveword.leaveTime}}</div>
                            </div>
                        </div>
                        <div class="opt_view" v-show="true">
                            <van-button v-show="false"  round  size= small type="primary"  @click.stop="leavewordEditClick(`${leaveword.leaveWordId}`)" >编辑</van-button>
                            <van-button round  size= small type="warning" @click.stop="leavewordDeleteClick(`${leaveword.leaveWordId}`)" style="margin-left:10px;">删除</van-button>
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

    import {queryLeaveword,deleteLeaveword} from '../../network/Leaveword'
    import { queryAllUserInfo } from '../../network/UserInfo'
    export default {
        name: 'LeavewordList',
        components: {
            NavBar,
            Scroll,
            ReturnTop
        },
        data() {
            return {
                leavewordList: [],  //界面需要展示的留言列表数据
                leaveTitle: "", //留言标题查询关键字

                userObjList:[], //留言人列表
                userObj_user_name: '',  //要查询的用户名
                userObj_name: "不限制", //要查询的姓名
                showUserObjPicker: false, //是否显示留言人Picker
                userObjColumns: ['不限制'], //留言人Picker的选项初始化

                leaveTime: "",  //留言时间
                showLeaveTimePicker: false, //是否显示选择留言时间Picker
                picker_leaveTime: new Date(), //留言时间Picker绑定的日期

                pullFlag: true,  //是否允许下拉刷新标志
                page: 1, //默认显示结果第1页
                showQueryLeavewordForm: false, //是否显示查询留言表单
                isReturnTop: false,
            }
        },

        created() {
            //初始化留言人下拉框数据
            queryAllUserInfo().then(res => {
                this.userObjList = res.data;
                for(var i=0;i<this.userObjList.length;i++) {
                    this.userObjColumns.push(this.userObjList[i].name)
                }
            });
            //查询留言信息
            this.leavewordQuery("");
            document.getElementById("tabbar").style.display = "flex";
        },

        methods: {
           back() {
                this.$router.go(-1);
           },

           onConfirmUserObj(value,index) {  //选中某个留言人后响应
                this.userObj_name = value;
                if(index == 0) this.userObj_user_name = '';
                else this.userObj_user_name = this.userObjList[index-1].user_name;
                this.showUserObjPicker = false;
            },

            onConfirmLeaveTime(value) {  //选中某个留言时间后的响应
                var year = value.getFullYear();
                var month = (value.getMonth()+1)<10?'0'+ (value.getMonth()+1): (value.getMonth()+1);
                var day = value.getDate()<10?'0'+value.getDate():value.getDate();
                this.leaveTime = year + "-" +  month + "-" + day ;
                this.showLeaveTimePicker = false;
            },
            clearLeaveTime() {  //清空留言时间
               this.leaveTime=''
               this.showLeaveTimePicker=false;
            },

            leavewordAdd() {
                this.$router.push('/leaveword/add');  //打开留言添加页面
            },

            queryLeaveword() {
                this.page = 1;
                this.leavewordList = [];
                this.pullFlag = false;
                this.showQueryLeavewordForm = false;
                this.leavewordQuery("QUERY");
            },

            leavewordQuery(action) {
                queryLeaveword(this.leaveTitle,this.userObj_user_name,this.leaveTime,this.page).then(res => {
                    //this.leavewordList.push(...res.data)
                    this.leavewordList = this.leavewordList.concat(res.data.list)
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

            leavewordDetailClick(leaveWordId) {
                this.$router.push('/leaveword/detail/' + leaveWordId); //跳转到留言详情页
            },
            leavewordEditClick(leaveWordId) {
                this.$router.push('/leaveword/modify/' + leaveWordId); //跳转到留言修改页
            },
            leavewordDeleteClick(leaveWordId) {
                this.$dialog.confirm({
                    title: '删除留言:' + leaveWordId,
                    message: '确定删除吗？'
                }).then(() => {
                    deleteLeaveword(leaveWordId).then(res => {
                        console.log(res)
                        this.leavewordList.forEach(function(leaveword, index, arr) {
                            if(leaveword.leaveWordId == leaveWordId) arr.splice(index, 1);
                        });
                    });
                });
            },

            pullingDown() {  //下拉刷新
                this.page = 1;
                this.leavewordList = [];
                this.pullFlag = false;
                this.leavewordQuery("DOWN");
            },

            pullingUp() {  //上拉加载
                console.log("上拉加载")
                this.page = this.page + 1;
                this.$refs.scroll.finishPullUp();
                this.leavewordQuery("UP");
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
    @import "LeavewordList.css";
    #leaveword {
        height: 100vh;
        width: 100vw;
        position: relative;
    }
</style>
