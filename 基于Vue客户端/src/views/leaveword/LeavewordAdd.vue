<template>
    <div id="leaveword">
        <nav-bar class="nav">
            <template v-slot:left><van-icon name="arrow-left" size="25"  @click.stop="back" /></template>
            <template v-slot:center>添加留言</template>
            <template v-slot:right></template>
        </nav-bar>

        <scroll class="content" top="1" bottom="50" ref="scroll" style="padding: 0px 5px">
            <van-form @submit="onSubmit">
                <van-field autosize clearable name="leaveword.leaveTitle" v-model="leaveTitle" label="留言标题：" placeholder="请输入留言标题" :rules="[{ required: true, message: '请填写留言标题' }]" />
                <van-field autosize clearable name="leaveword.leaveContent" v-model="leaveContent" label="留言内容：" placeholder="请输入留言内容" :rules="[{ required: true, message: '请填写留言内容' }]" />
                <van-field v-show="false" clearable name="leaveword.userObj.user_name" v-model="userObj_user_name" label="留言人：" />
                <van-field v-show="false" readonly clickable label="留言人" :value="userObj_name"  right-icon="arrow-down" placeholder="选择留言人" @click="showUserObjPicker = true" />
                <van-picker v-show="showUserObjPicker" show-toolbar :columns="userObjColumns" :default-index="1" @cancel="showUserObjPicker = false" @confirm="onConfirmUserObj" />
                <van-field v-show="false" readonly name="leaveword.leaveTime" label="留言时间" :value="leaveTime"   placeholder="选择留言时间" @click="showLeaveTimePicker = true" :rules="[{ required: true, message: '留言时间' }]">
                    <template #button>
                        <van-button size="small" type="warning" @click.stop="clearLeaveTime">清除</van-button>
                    </template>
                </van-field>
                <van-datetime-picker v-if="showLeaveTimePicker"  v-model="picker_leaveTime" type="date" @cancel="showLeaveTimePicker = false" title="选择留言时间" @confirm="onConfirmLeaveTime" />
                <van-field v-show="false" autosize clearable name="leaveword.replyContent" v-model="replyContent" label="管理回复：" placeholder="请输入管理回复" :rules="[{ required: true, message: '请填写管理回复' }]" />
                <van-field v-show="false" readonly name="leaveword.replyTime" label="回复时间" :value="replyTime"   placeholder="选择回复时间" @click="showReplyTimePicker = true" :rules="[{ required: true, message: '回复时间' }]">
                    <template #button>
                        <van-button size="small" type="warning" @click.stop="clearReplyTime">清除</van-button>
                    </template>
                </van-field>
                <van-datetime-picker v-if="showReplyTimePicker"  v-model="picker_replyTime" type="date" @cancel="showReplyTimePicker = false" title="选择回复时间" @confirm="onConfirmReplyTime" />
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
    import { queryAllUserInfo } from '../../network/UserInfo'
    import { addLeaveword} from "../../network/Leaveword";

    export default {
        name: "LeavewordAdd",
        components: {
            NavBar,
            Scroll,
        },
        data() {
            return {
                leaveTitle: "", //留言标题
                leaveContent: "", //留言内容
                userObjList: [], //留言人列表
                userObj_user_name: '', //用户名
                userObj_name: "", //姓名
                showUserObjPicker: false, //是否显示留言人Picker
                userObjColumns: [], //留言人Picker的选项
                showLeaveTimePicker:false, //是否显示留言时间Picker
                leaveTime: '--', //留言时间
                picker_leaveTime: new Date(), //留言时间Picker的日期
                replyContent: "--", //管理回复
                showReplyTimePicker:false, //是否显示回复时间Picker
                replyTime: '--', //回复时间
                picker_replyTime: new Date(), //回复时间Picker的日期
            }
        },

        mounted() {

        },

        created() {
            /*查询所有留言人初始化下拉Picker*/
            queryAllUserInfo().then(res => {
                this.userObjList = res.data;
                for(var i=0;i<this.userObjList.length;i++) {
                    this.userObjColumns.push(this.userObjList[i].name)
                }
                if(this.userObjList.length > 0) {
                    var firstUserObj = this.userObjList[0]; //默认选中第一个选项
                    this.userObj_name = firstUserObj.name;
                    this.userObj_user_name = firstUserObj.user_name;
                }
            });

            document.getElementById("tabbar").style.display = "flex";
        },

        methods: {
            back() {
                this.$router.go(-1);
            },

            onConfirmUserObj(value,index) {
                this.userObj_name = value; //选中某个留言人后响应
                this.userObj_user_name = this.userObjList[index].user_name;
                this.showUserObjPicker = false;
            },
            onSubmit(leaveword) {  //向后台请求添加留言
                addLeaveword(leaveword).then(res => {
                    console.log(res)
                    this.$notify.success('添加成功~')
                    setTimeout(()=>{
                        this.$router.go(-1);
                    },200)
                }).catch(error => { this.$notify.error(error)})
            },
            onConfirmLeaveTime(value) {  //选中某个留言时间后的响应
                var year = value.getFullYear();
                var month = (value.getMonth()+1)<10?'0'+ (value.getMonth()+1): (value.getMonth()+1);
                var day = value.getDate()<10?'0'+value.getDate():value.getDate();
                this.leaveTime = year + "-" +  month + "-" + day ;
                this.showLeaveTimePicker = false;
            },
            clearLeaveTime() { //清空留言时间
                this.leaveTime='';
                this.showLeaveTimePicker=false;
            },
            onConfirmReplyTime(value) {  //选中某个回复时间后的响应
                var year = value.getFullYear();
                var month = (value.getMonth()+1)<10?'0'+ (value.getMonth()+1): (value.getMonth()+1);
                var day = value.getDate()<10?'0'+value.getDate():value.getDate();
                this.replyTime = year + "-" +  month + "-" + day ;
                this.showReplyTimePicker = false;
            },
            clearReplyTime() { //清空回复时间
                this.replyTime='';
                this.showReplyTimePicker=false;
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

    .van-dropdown-menu {  width: 100%; }

</style>
