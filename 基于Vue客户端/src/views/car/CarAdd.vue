<template>
    <div id="car">
        <nav-bar class="nav">
            <template v-slot:left><van-icon name="arrow-left" size="25"  @click.stop="back" /></template>
            <template v-slot:center>添加车辆</template>
            <template v-slot:right></template>
        </nav-bar>

        <scroll class="content" top="1" bottom="50" ref="scroll" style="padding: 0px 5px">
            <van-form @submit="onSubmit">
                <van-field autosize clearable name="car.carNo" v-model="carNo" label="车牌：" placeholder="请输入车牌" :rules="[{ required: true, message: '请填写车牌' }]" />
                <van-field v-show="false" clearable name="car.carModelObj.modelId" v-model="carModelObj_modelId" label="车型：" />
                <van-field readonly clickable label="车型" :value="carModelObj_modelName"  right-icon="arrow-down" placeholder="选择车型" @click="showCarModelObjPicker = true" />
                <van-picker v-show="showCarModelObjPicker" show-toolbar :columns="carModelObjColumns" :default-index="1" @cancel="showCarModelObjPicker = false" @confirm="onConfirmCarModelObj" />
                <van-field autosize clearable name="car.pinpai" v-model="pinpai" label="品牌：" placeholder="请输入品牌" :rules="[{ required: true, message: '请填写品牌' }]" />
                <van-field  v-show="false" clearable name="car.carPhoto" v-model="carPhoto" label="车辆照片："  :rules="[{ required: true, message: '车辆照片地址不能为空' }]" />
                <div class="van-cell van-field" style="pointer-events: auto;">
                    <div class="van-cell__title van-field__label"><span>车辆照片：</span></div>
                    <div class="van-cell__value van-field__value">
                        <div class="van-field__body">
                            <!-- 图片上传 -->
                            <img-upload name="carPhoto" @uploadFinished="carPhotoUpload"></img-upload>
                        </div>
                    </div>
                </div>
                <van-field autosize clearable name="car.youxing" v-model="youxing" label="油型：" placeholder="请输入油型" :rules="[{ required: true, message: '请填写油型' }]" />
                <van-field autosize clearable name="car.haoyouliang" v-model="haoyouliang" label="耗油量：" placeholder="请输入耗油量" :rules="[{ required: true, message: '请填写耗油量' }]" />
                <van-field readonly name="car.chexianriqi" label="车险日期" :value="chexianriqi"   placeholder="选择车险日期" @click="showChexianriqiPicker = true" :rules="[{ required: true, message: '车险日期' }]">
                    <template #button>
                        <van-button size="small" type="warning" @click.stop="clearChexianriqi">清除</van-button>
                    </template>
                </van-field>
                <van-datetime-picker v-if="showChexianriqiPicker"  v-model="picker_chexianriqi" type="date" @cancel="showChexianriqiPicker = false" title="选择车险日期" @confirm="onConfirmChexianriqi" />
                <van-field autosize clearable name="car.zonglicheng" v-model="zonglicheng" label="总里程：" placeholder="请输入总里程" :rules="[{ required: true, message: '请填写总里程' }]" />
                <div class="van-cell van-field" style="pointer-events: auto;">
                    <div class="van-cell__title van-field__label"><span>车辆详情：</span></div>
                    <div class="van-cell__value van-field__value">
                        <div class="van-field__body"></div>
                    </div>
                </div>
                <van-field  v-show="false" name="car.carDesc" v-model="carDesc" rows="5" label="车辆详情" type="textarea" maxlength="8000"  placeholder="请输入车辆详情" show-word-limit />
                <VueUeditorWrap  v-model="carDesc" :config="editorConfig"  @ready="carDescEditorReady" />
                <van-field v-show="false" clearable name="car.userObj.user_name" v-model="userObj_user_name" label="所属用户：" />
                <van-field v-show="false" readonly clickable label="所属用户" :value="userObj_name"  right-icon="arrow-down" placeholder="选择所属用户" @click="showUserObjPicker = true" />
                <van-picker v-show="showUserObjPicker" show-toolbar :columns="userObjColumns" :default-index="1" @cancel="showUserObjPicker = false" @confirm="onConfirmUserObj" />
                <van-field v-show="false" readonly name="car.addTime" label="登记时间" :value="addTime"   placeholder="选择登记时间" @click="showAddTimePicker = true" :rules="[{ required: true, message: '登记时间' }]">
                    <template #button>
                        <van-button size="small" type="warning" @click.stop="clearAddTime">清除</van-button>
                    </template>
                </van-field>
                <van-datetime-picker v-if="showAddTimePicker"  v-model="picker_addTime" type="date" @cancel="showAddTimePicker = false" title="选择登记时间" @confirm="onConfirmAddTime" />
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
    import VueUeditorWrap from 'vue-ueditor-wrap'
    import ImgUpload from '../../components/content/ImgUpload'
    import { queryAllCarModel } from '../../network/CarModel'
    import { queryAllUserInfo } from '../../network/UserInfo'
    import { addCar} from "../../network/Car";

    export default {
        name: "CarAdd",
        components: {
            NavBar,
            Scroll,
            ImgUpload,
            VueUeditorWrap
        },
        data() {
            return {
                carNo: "", //车牌
                carModelObjList: [], //车型列表
                carModelObj_modelId: 0, //车型id
                carModelObj_modelName: "", //车型名称
                showCarModelObjPicker: false, //是否显示车型Picker
                carModelObjColumns: [], //车型Picker的选项
                pinpai: "", //品牌
                carPhoto: 'upload/NoImage.jpg', //车辆照片路径
                youxing: "", //油型
                haoyouliang: "", //耗油量
                showChexianriqiPicker:false, //是否显示车险日期Picker
                chexianriqi: '', //车险日期
                picker_chexianriqi: new Date(), //车险日期Picker的日期
                zonglicheng: "", //总里程
                carDesc: "", //车辆详情
                userObjList: [], //所属用户列表
                userObj_user_name: '', //用户名
                userObj_name: "", //姓名
                showUserObjPicker: false, //是否显示所属用户Picker
                userObjColumns: [], //所属用户Picker的选项
                showAddTimePicker:false, //是否显示登记时间Picker
                addTime: '--', //登记时间
                picker_addTime: new Date(), //登记时间Picker的日期
                // 简单配置
                editorConfig: {
                    // 编辑器不自动被内容撑高
                    autoHeightEnabled: false,
                    // 初始容器高度
                    initialFrameHeight: 280,
                    // 初始容器宽度
                    initialFrameWidth: '100%',
                    // 上传文件接口
                    //serverUrl: 'http://localhost:8080/JavaWebProject/ueditor1_4_3/jsp/controller.jsp',
                }
            }
        },

        mounted() {

        },

        created() {
            /*查询所有车型初始化下拉Picker*/
            queryAllCarModel().then(res => {
                this.carModelObjList = res.data;
                for(var i=0;i<this.carModelObjList.length;i++) {
                    this.carModelObjColumns.push(this.carModelObjList[i].modelName)
                }
                if(this.carModelObjList.length > 0) {
                    var firstCarModelObj = this.carModelObjList[0]; //默认选中第一个选项
                    this.carModelObj_modelName = firstCarModelObj.modelName;
                    this.carModelObj_modelId = firstCarModelObj.modelId;
                }
            });

            /*查询所有所属用户初始化下拉Picker*/
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
            carDescEditorReady(editorInstance) {
                // 这里可以拿到ueditor的实例，比如editorInstance.getContent()就可以拿到编辑器的html内容
                //this.evtHub.$emit('editor.data', editorInstance);
                console.log("准备编辑器ok",editorInstance);
            },
            back() {
                this.$router.go(-1);
            },

            onConfirmCarModelObj(value,index) {
                this.carModelObj_modelName = value; //选中某个车型后响应
                this.carModelObj_modelId = this.carModelObjList[index].modelId;
                this.showCarModelObjPicker = false;
            },
            onConfirmUserObj(value,index) {
                this.userObj_name = value; //选中某个所属用户后响应
                this.userObj_user_name = this.userObjList[index].user_name;
                this.showUserObjPicker = false;
            },
            onSubmit(car) {  //向后台请求添加车辆
                addCar(car).then(res => {
                    console.log(res)
                    this.$notify.success('添加成功~')
                    setTimeout(()=>{
                        this.$router.go(-1);
                    },200)
                }).catch(error => { this.$notify.error(error)})
            },
            onConfirmChexianriqi(value) {  //选中某个车险日期后的响应
                var year = value.getFullYear();
                var month = (value.getMonth()+1)<10?'0'+ (value.getMonth()+1): (value.getMonth()+1);
                var day = value.getDate()<10?'0'+value.getDate():value.getDate();
                this.chexianriqi = year + "-" +  month + "-" + day ;
                this.showChexianriqiPicker = false;
            },
            clearChexianriqi() { //清空车险日期
                this.chexianriqi='';
                this.showChexianriqiPicker=false;
            },
            onConfirmAddTime(value) {  //选中某个登记时间后的响应
                var year = value.getFullYear();
                var month = (value.getMonth()+1)<10?'0'+ (value.getMonth()+1): (value.getMonth()+1);
                var day = value.getDate()<10?'0'+value.getDate():value.getDate();
                this.addTime = year + "-" +  month + "-" + day ;
                this.showAddTimePicker = false;
            },
            clearAddTime() { //清空登记时间
                this.addTime='';
                this.showAddTimePicker=false;
            },
            carPhotoUpload(name,data) {  //上传车辆照片后的回调方法
                if(name == "carPhoto") {
                    this.carPhoto = data;
                }
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

    .van-dropdown-menu {  width: 100%; }

</style>
