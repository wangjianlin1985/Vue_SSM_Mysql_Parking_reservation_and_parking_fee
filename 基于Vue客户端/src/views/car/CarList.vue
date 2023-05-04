<template>
    <div id="car">
        <van-popup v-model="showQueryCarForm"  closeable position="top" :style="{ height: 'auto' }">
            <div class="carQueryForm">
                <h3>车辆查询</h3>
                <van-field clearable v-model="carNo" label="车牌：" placeholder="请输入车牌" />
                <van-field readonly clickable label="车型" :value="carModelObj_modelName" right-icon="arrow-down" placeholder="选择车型" @click="showCarModelObjPicker = true" />
                <van-popup v-model="showCarModelObjPicker" position="bottom">
                    <van-picker show-toolbar :columns="carModelObjColumns" @cancel="showCarModelObjPicker = false" @confirm="onConfirmCarModelObj" />
                </van-popup>
                <van-field clearable v-model="pinpai" label="品牌：" placeholder="请输入品牌" />
                <van-field readonly label="车险日期" :value="chexianriqi"   placeholder="选择车险日期" @click="showChexianriqiPicker = true">
                    <template #button>
                        <van-button size="small" type="warning" @click.stop="clearChexianriqi">清除</van-button>
                    </template>
                </van-field>
                <van-datetime-picker v-if="showChexianriqiPicker" v-model="picker_chexianriqi" type="date" @cancel="showChexianriqiPicker = false" title="选择车险日期" @confirm="onConfirmChexianriqi" />
                <van-field v-show="false" readonly clickable label="所属用户" :value="userObj_name" right-icon="arrow-down" placeholder="选择所属用户" @click="showUserObjPicker = true" />
                <van-popup v-model="showUserObjPicker" position="bottom">
                    <van-picker show-toolbar :columns="userObjColumns" @cancel="showUserObjPicker = false" @confirm="onConfirmUserObj" />
                </van-popup>
                <van-field readonly label="登记时间" :value="addTime"   placeholder="选择登记时间" @click="showAddTimePicker = true">
                    <template #button>
                        <van-button size="small" type="warning" @click.stop="clearAddTime">清除</van-button>
                    </template>
                </van-field>
                <van-datetime-picker v-if="showAddTimePicker" v-model="picker_addTime" type="date" @cancel="showAddTimePicker = false" title="选择登记时间" @confirm="onConfirmAddTime" />
                <div class="queryBtn">
                    <van-button round  size= small type="primary" @click="queryCar">查询</van-button>
                </div>
            </div>
        </van-popup>

        <nav-bar class="nav" ref="nav" >
            <template v-slot:left>
                <van-icon name="plus" size="25" @click.stop="carAdd" />
                <van-icon name="arrow-left" v-show="false" size="25"  @click.stop="back" />
            </template>
            <template v-slot:center>车辆列表</template>
            <template v-slot:right><van-icon name="search" size="25"  @click.stop="showQueryCarForm=true" /></template>
        </nav-bar>

        <scroll class="content" top="1" bottom="50" ref="scroll" :isPullDown="true" @pullingDown="pullingDown" :isPullUp="true" @pullingUp="pullingUp"  @iscroll="contentScroll">
            <div class="ignore-pull-down1" ref="pull" v-show="pullFlag">下拉更新</div>
            <div class="ignore-pull-down2" ref="pull" v-show="!pullFlag">
                <span class="ball1"></span><span class="ball2"></span><span class="ball3"></span>
            </div>
            <div class="carList">
                <template v-for="car in carList">
                    <div class="carItem" :key="car.carId" @click.stop="carDetailClick(`${car.carId}`)">
                        <div class="content_view">
                            <div class="carImg">
                                <img v-lazy="'/' + car.carPhoto">
                            </div>
                            <div class="carText">
                                <div>车牌：{{car.carNo}}</div>
                                <div>车型：{{car.carModelObj.modelName}}</div>
                                <div>品牌：{{car.pinpai}}</div>
                                <div>油型：{{car.youxing}}</div>
                                <div>耗油量：{{car.haoyouliang}}</div>
                                <div>车险日期：{{car.chexianriqi}}</div>
                                <div>总里程：{{car.zonglicheng}}</div>
                                <div>登记时间：{{car.addTime}}</div>
                            </div>
                        </div>
                        <div class="opt_view">
                            <van-button round  size= small type="primary"  @click.stop="carEditClick(`${car.carId}`)" >编辑</van-button>
                            <van-button round  size= small type="warning" @click.stop="carDeleteClick(`${car.carId}`)" style="margin-left:10px;">删除</van-button>
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

    import {queryCar,deleteCar} from '../../network/Car'
    import { queryAllCarModel } from '../../network/CarModel'
    import { queryAllUserInfo } from '../../network/UserInfo'
    export default {
        name: 'CarList',
        components: {
            NavBar,
            Scroll,
            ReturnTop
        },
        data() {
            return {
                carList: [],  //界面需要展示的车辆列表数据
                carNo: "", //车牌查询关键字

                carModelObjList:[], //车型列表
                carModelObj_modelId: 0,  //要查询的车型id
                carModelObj_modelName: "不限制", //要查询的车型名称
                showCarModelObjPicker: false, //是否显示车型Picker
                carModelObjColumns: ['不限制'], //车型Picker的选项初始化
                pinpai: "", //品牌查询关键字

                chexianriqi: "",  //车险日期
                showChexianriqiPicker: false, //是否显示选择车险日期Picker
                picker_chexianriqi: new Date(), //车险日期Picker绑定的日期

                userObjList:[], //所属用户列表
                userObj_user_name: '',  //要查询的用户名
                userObj_name: "不限制", //要查询的姓名
                showUserObjPicker: false, //是否显示所属用户Picker
                userObjColumns: ['不限制'], //所属用户Picker的选项初始化

                addTime: "",  //登记时间
                showAddTimePicker: false, //是否显示选择登记时间Picker
                picker_addTime: new Date(), //登记时间Picker绑定的日期

                pullFlag: true,  //是否允许下拉刷新标志
                page: 1, //默认显示结果第1页
                showQueryCarForm: false, //是否显示查询车辆表单
                isReturnTop: false,
            }
        },

        created() {
            //初始化车型下拉框数据
            queryAllCarModel().then(res => {
                this.carModelObjList = res.data;
                for(var i=0;i<this.carModelObjList.length;i++) {
                    this.carModelObjColumns.push(this.carModelObjList[i].modelName)
                }
            });
            //初始化所属用户下拉框数据
            queryAllUserInfo().then(res => {
                this.userObjList = res.data;
                for(var i=0;i<this.userObjList.length;i++) {
                    this.userObjColumns.push(this.userObjList[i].name)
                }
            });
            //查询车辆信息
            this.carQuery("");
            document.getElementById("tabbar").style.display = "flex";
        },

        methods: {
           back() {
                this.$router.go(-1);
           },

           onConfirmCarModelObj(value,index) {  //选中某个车型后响应
                this.carModelObj_modelName = value;
                if(index == 0) this.carModelObj_modelId = 0;
                else this.carModelObj_modelId = this.carModelObjList[index-1].modelId;
                this.showCarModelObjPicker = false;
            },

            onConfirmChexianriqi(value) {  //选中某个车险日期后的响应
                var year = value.getFullYear();
                var month = (value.getMonth()+1)<10?'0'+ (value.getMonth()+1): (value.getMonth()+1);
                var day = value.getDate()<10?'0'+value.getDate():value.getDate();
                this.chexianriqi = year + "-" +  month + "-" + day ;
                this.showChexianriqiPicker = false;
            },
            clearChexianriqi() {  //清空车险日期
               this.chexianriqi=''
               this.showChexianriqiPicker=false;
            },

           onConfirmUserObj(value,index) {  //选中某个所属用户后响应
                this.userObj_name = value;
                if(index == 0) this.userObj_user_name = '';
                else this.userObj_user_name = this.userObjList[index-1].user_name;
                this.showUserObjPicker = false;
            },

            onConfirmAddTime(value) {  //选中某个登记时间后的响应
                var year = value.getFullYear();
                var month = (value.getMonth()+1)<10?'0'+ (value.getMonth()+1): (value.getMonth()+1);
                var day = value.getDate()<10?'0'+value.getDate():value.getDate();
                this.addTime = year + "-" +  month + "-" + day ;
                this.showAddTimePicker = false;
            },
            clearAddTime() {  //清空登记时间
               this.addTime=''
               this.showAddTimePicker=false;
            },

            carAdd() {
                this.$router.push('/car/add');  //打开车辆添加页面
            },

            queryCar() {
                this.page = 1;
                this.carList = [];
                this.pullFlag = false;
                this.showQueryCarForm = false;
                this.carQuery("QUERY");
            },

            carQuery(action) {
                queryCar(this.carNo,this.carModelObj_modelId,this.pinpai,this.chexianriqi,this.userObj_user_name,this.addTime,this.page).then(res => {
                    //this.carList.push(...res.data)
                    this.carList = this.carList.concat(res.data.list)
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

            carDetailClick(carId) {
                this.$router.push('/car/detail/' + carId); //跳转到车辆详情页
            },
            carEditClick(carId) {
                this.$router.push('/car/modify/' + carId); //跳转到车辆修改页
            },
            carDeleteClick(carId) {
                this.$dialog.confirm({
                    title: '删除车辆:' + carId,
                    message: '确定删除吗？'
                }).then(() => {
                    deleteCar(carId).then(res => {
                        console.log(res)
                        this.carList.forEach(function(car, index, arr) {
                            if(car.carId == carId) arr.splice(index, 1);
                        });
                    });
                });
            },

            pullingDown() {  //下拉刷新
                this.page = 1;
                this.carList = [];
                this.pullFlag = false;
                this.carQuery("DOWN");
            },

            pullingUp() {  //上拉加载
                console.log("上拉加载")
                this.page = this.page + 1;
                this.$refs.scroll.finishPullUp();
                this.carQuery("UP");
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
    @import "CarList.css";
    #car {
        height: 100vh;
        width: 100vw;
        position: relative;
    }
</style>
