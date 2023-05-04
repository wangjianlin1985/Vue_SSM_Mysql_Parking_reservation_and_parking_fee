<template>
    <div id="region">
        <van-popup v-model="showQueryRegionForm"  closeable position="top" :style="{ height: 'auto' }">
            <div class="regionQueryForm">
                <h3>区域查询</h3>
                <van-field clearable v-model="regionFloor" label="所在楼层：" placeholder="请输入所在楼层" />
                <div class="queryBtn">
                    <van-button round  size= small type="primary" @click="queryRegion">查询</van-button>
                </div>
            </div>
        </van-popup>

        <nav-bar class="nav" ref="nav" >
            <template v-slot:left>
                <van-icon name="plus" size="25" v-show="false" @click.stop="regionAdd" />
                <van-icon name="arrow-left" v-show="false" size="25"  @click.stop="back" />
            </template>
            <template v-slot:center>区域列表</template>
            <template v-slot:right><van-icon name="search" size="25"  @click.stop="showQueryRegionForm=true" /></template>
        </nav-bar>

        <scroll class="content" top="1" bottom="50" ref="scroll" :isPullDown="true" @pullingDown="pullingDown" :isPullUp="true" @pullingUp="pullingUp"  @iscroll="contentScroll">
            <div class="ignore-pull-down1" ref="pull" v-show="pullFlag">下拉更新</div>
            <div class="ignore-pull-down2" ref="pull" v-show="!pullFlag">
                <span class="ball1"></span><span class="ball2"></span><span class="ball3"></span>
            </div>
            <div class="regionList">
                <template v-for="region in regionList">
                    <div class="regionItem" :key="region.regionId" @click.stop="regionDetailClick(`${region.regionId}`)">
                        <div class="content_view">
                            <div class="regionText">
                                <div>区域id：{{region.regionId}}</div>
                                <div>所在楼层：{{region.regionFloor}}</div>
                                <div>区域名称：{{region.regionName}}</div>
                            </div>
                        </div>
                        <div class="opt_view" v-show="false">
                            <van-button round  size= small type="primary"  @click.stop="regionEditClick(`${region.regionId}`)" >编辑</van-button>
                            <van-button round  size= small type="warning" @click.stop="regionDeleteClick(`${region.regionId}`)" style="margin-left:10px;">删除</van-button>
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

    import {queryRegion,deleteRegion} from '../../network/Region'
    export default {
        name: 'RegionList',
        components: {
            NavBar,
            Scroll,
            ReturnTop
        },
        data() {
            return {
                regionList: [],  //界面需要展示的区域列表数据
                regionFloor: "", //所在楼层查询关键字

                pullFlag: true,  //是否允许下拉刷新标志
                page: 1, //默认显示结果第1页
                showQueryRegionForm: false, //是否显示查询区域表单
                isReturnTop: false,
            }
        },

        created() {
            //查询区域信息
            this.regionQuery("");
            document.getElementById("tabbar").style.display = "flex";
        },

        methods: {
           back() {
                this.$router.go(-1);
           },

            regionAdd() {
                this.$router.push('/region/add');  //打开区域添加页面
            },

            queryRegion() {
                this.page = 1;
                this.regionList = [];
                this.pullFlag = false;
                this.showQueryRegionForm = false;
                this.regionQuery("QUERY");
            },

            regionQuery(action) {
                queryRegion(this.regionFloor,this.page).then(res => {
                    //this.regionList.push(...res.data)
                    this.regionList = this.regionList.concat(res.data.list)
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

            regionDetailClick(regionId) {
                this.$router.push('/region/detail/' + regionId); //跳转到区域详情页
            },
            regionEditClick(regionId) {
                this.$router.push('/region/modify/' + regionId); //跳转到区域修改页
            },
            regionDeleteClick(regionId) {
                this.$dialog.confirm({
                    title: '删除区域:' + regionId,
                    message: '确定删除吗？'
                }).then(() => {
                    deleteRegion(regionId).then(res => {
                        console.log(res)
                        this.regionList.forEach(function(region, index, arr) {
                            if(region.regionId == regionId) arr.splice(index, 1);
                        });
                    });
                });
            },

            pullingDown() {  //下拉刷新
                this.page = 1;
                this.regionList = [];
                this.pullFlag = false;
                this.regionQuery("DOWN");
            },

            pullingUp() {  //上拉加载
                console.log("上拉加载")
                this.page = this.page + 1;
                this.$refs.scroll.finishPullUp();
                this.regionQuery("UP");
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
    @import "RegionList.css";
    #region {
        height: 100vh;
        width: 100vw;
        position: relative;
    }
</style>
