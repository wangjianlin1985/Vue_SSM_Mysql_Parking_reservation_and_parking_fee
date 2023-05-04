<template>
    <div id="carModel">
        <nav-bar class="nav">
            <template v-slot:left><van-icon name="arrow-left" size="25"  @click.stop="back" /></template>
            <template v-slot:center>编辑车型</template>
            <template v-slot:right></template>
        </nav-bar>

        <scroll class="content" top="1" bottom="50" ref="scroll" style="padding: 0px 5px">
            <van-form @submit="onSubmit">
                <van-field readonly name="carModel.modelId" v-model="modelId" label="车型id：" placeholder="请输入车型id" :rules="[{ required: true, message: '请填写车型id' }]" />
                <van-field autosize clearable name="carModel.modelName" v-model="modelName" label="车型名称：" placeholder="请输入车型名称" :rules="[{ required: true, message: '请填写车型名称' }]" />
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
    import { updateCarModel} from "../../network/CarModel";
    import { getCarModel } from "../../network/CarModel";
    export default {
        name: "CarModelAdd",
        components: {
            NavBar,
            Scroll,
        },
        data() {
            return {
                modelId: "",  //车型id
                modelName: "", //车型名称
            }
        },

        mounted() {

        },

        created() {
            this.modelId = this.$route.params.modelId;  //接收车型id参数
            //向后端请求单条车型记录
            getCarModel(this.modelId).then(res => {
                var carModel = res.data;
                this.modelId = carModel.modelId; //车型id
                this.modelName = carModel.modelName;  //车型名称
            })
            document.getElementById("tabbar").style.display = "flex";
        },

        methods: {
            back() {
                this.$router.go(-1);
            },

            onSubmit(carModel) {  //向后台请求更新车型
                updateCarModel(carModel).then(res => {
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

    #carModel {
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

