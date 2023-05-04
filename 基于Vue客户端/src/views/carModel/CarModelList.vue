<template>
    <div id="carModel">
        <van-popup v-model="showQueryCarModelForm"  closeable position="top" :style="{ height: 'auto' }">
            <div class="carModelQueryForm">
                <h3>车型查询</h3>
                <div class="queryBtn">
                    <van-button round  size= small type="primary" @click="queryCarModel">查询</van-button>
                </div>
            </div>
        </van-popup>

        <nav-bar class="nav" ref="nav" >
            <template v-slot:left>
                <van-icon name="plus" size="25" v-show="false" @click.stop="carModelAdd" />
                <van-icon name="arrow-left" v-show="false" size="25"  @click.stop="back" />
            </template>
            <template v-slot:center>车型列表</template>
            <template v-slot:right><van-icon name="search" size="25"  @click.stop="showQueryCarModelForm=true" /></template>
        </nav-bar>

        <scroll class="content" top="1" bottom="50" ref="scroll" :isPullDown="true" @pullingDown="pullingDown" :isPullUp="true" @pullingUp="pullingUp"  @iscroll="contentScroll">
            <div class="ignore-pull-down1" ref="pull" v-show="pullFlag">下拉更新</div>
            <div class="ignore-pull-down2" ref="pull" v-show="!pullFlag">
                <span class="ball1"></span><span class="ball2"></span><span class="ball3"></span>
            </div>
            <div class="carModelList">
                <template v-for="carModel in carModelList">
                    <div class="carModelItem" :key="carModel.modelId" @click.stop="carModelDetailClick(`${carModel.modelId}`)">
                        <div class="content_view">
                            <div class="carModelText">
                                <div>车型id：{{carModel.modelId}}</div>
                                <div>车型名称：{{carModel.modelName}}</div>
                            </div>
                        </div>
                        <div class="opt_view" v-show="false">
                            <van-button round  size= small type="primary"  @click.stop="carModelEditClick(`${carModel.modelId}`)" >编辑</van-button>
                            <van-button round  size= small type="warning" @click.stop="carModelDeleteClick(`${carModel.modelId}`)" style="margin-left:10px;">删除</van-button>
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

    import {queryCarModel,deleteCarModel} from '../../network/CarModel'
    export default {
        name: 'CarModelList',
        components: {
            NavBar,
            Scroll,
            ReturnTop
        },
        data() {
            return {
                carModelList: [],  //界面需要展示的车型列表数据

                pullFlag: true,  //是否允许下拉刷新标志
                page: 1, //默认显示结果第1页
                showQueryCarModelForm: false, //是否显示查询车型表单
                isReturnTop: false,
            }
        },

        created() {
            //查询车型信息
            this.carModelQuery("");
            document.getElementById("tabbar").style.display = "flex";
        },

        methods: {
           back() {
                this.$router.go(-1);
           },

            carModelAdd() {
                this.$router.push('/carModel/add');  //打开车型添加页面
            },

            queryCarModel() {
                this.page = 1;
                this.carModelList = [];
                this.pullFlag = false;
                this.showQueryCarModelForm = false;
                this.carModelQuery("QUERY");
            },

            carModelQuery(action) {
                queryCarModel(this.page).then(res => {
                    //this.carModelList.push(...res.data)
                    this.carModelList = this.carModelList.concat(res.data.list)
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

            carModelDetailClick(modelId) {
                this.$router.push('/carModel/detail/' + modelId); //跳转到车型详情页
            },
            carModelEditClick(modelId) {
                this.$router.push('/carModel/modify/' + modelId); //跳转到车型修改页
            },
            carModelDeleteClick(modelId) {
                this.$dialog.confirm({
                    title: '删除车型:' + modelId,
                    message: '确定删除吗？'
                }).then(() => {
                    deleteCarModel(modelId).then(res => {
                        console.log(res)
                        this.carModelList.forEach(function(carModel, index, arr) {
                            if(carModel.modelId == modelId) arr.splice(index, 1);
                        });
                    });
                });
            },

            pullingDown() {  //下拉刷新
                this.page = 1;
                this.carModelList = [];
                this.pullFlag = false;
                this.carModelQuery("DOWN");
            },

            pullingUp() {  //上拉加载
                console.log("上拉加载")
                this.page = this.page + 1;
                this.$refs.scroll.finishPullUp();
                this.carModelQuery("UP");
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
    @import "CarModelList.css";
    #carModel {
        height: 100vh;
        width: 100vw;
        position: relative;
    }
</style>
