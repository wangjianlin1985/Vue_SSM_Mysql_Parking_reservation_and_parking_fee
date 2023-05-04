<template>
    <div id="region">
        <nav-bar class="nav">
            <template v-slot:left><van-icon name="arrow-left" size="25"  @click.stop="back" /></template>
            <template v-slot:center>编辑区域</template>
            <template v-slot:right></template>
        </nav-bar>

        <scroll class="content" top="1" bottom="50" ref="scroll" style="padding: 0px 5px">
            <van-form @submit="onSubmit">
                <van-field readonly name="region.regionId" v-model="regionId" label="区域id：" placeholder="请输入区域id" :rules="[{ required: true, message: '请填写区域id' }]" />
                <van-field autosize clearable name="region.regionFloor" v-model="regionFloor" label="所在楼层：" placeholder="请输入所在楼层" :rules="[{ required: true, message: '请填写所在楼层' }]" />
                <van-field autosize clearable name="region.regionName" v-model="regionName" label="区域名称：" placeholder="请输入区域名称" :rules="[{ required: true, message: '请填写区域名称' }]" />
                <van-field autosize clearable name="region.regionDesc" v-model="regionDesc" label="区域说明：" placeholder="请输入区域说明" :rules="[{ required: true, message: '请填写区域说明' }]" />
                <div style="margin: 16px;">
                    <van-button round block type="info" native-type="submit">更新</van-button>
                </div>
            </van-form>
        </scroll>
    </div>
</template>

<script>
    import NavBar from '../../components/common/navbar/NavBar'
    import Scroll from '../../components/common/scroll/Scroll'
    import { updateRegion} from "../../network/Region";
    import { getRegion } from "../../network/Region";
    export default {
        name: "RegionAdd",
        components: {
            NavBar,
            Scroll,
        },
        data() {
            return {
                regionId: "",  //区域id
                regionFloor: "", //所在楼层
                regionName: "", //区域名称
                regionDesc: "", //区域说明
            }
        },

        mounted() {

        },

        created() {
            this.regionId = this.$route.params.regionId;  //接收区域id参数
            //向后端请求单条区域记录
            getRegion(this.regionId).then(res => {
                var region = res.data;
                this.regionId = region.regionId; //区域id
                this.regionFloor = region.regionFloor;  //所在楼层
                this.regionName = region.regionName;  //区域名称
                this.regionDesc = region.regionDesc;  //区域说明
            })
            document.getElementById("tabbar").style.display = "flex";
        },

        methods: {
            back() {
                this.$router.go(-1);
            },

            onSubmit(region) {  //向后台请求更新区域
                updateRegion(region).then(res => {
                    console.log(res)
                    this.$notify.success('更新成功~')
                }).catch(error => { this.$notify.error(error)})
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

    #region {
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

    .avatar {
        width: 100px;
        height: 100px;
        background-color: #f3f3f3;
        float: left;
        top: 5px;
        right: 5px;
        border-radius: 3px;
       margin-bottom: 26px;
    }
</style>

