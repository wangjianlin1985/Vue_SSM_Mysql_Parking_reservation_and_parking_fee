<template>
  <div class="img-box">
    <van-uploader  v-model="imgList" multiple :max-count="1" :after-read="afterRead" >
      <!-- <div class="add-img" v-show="imgList.length<9">
        <span class="add-text">＋</span>
      </div> -->
    </van-uploader>
    
    <span class="img-tip">图片&nbsp;{{imgList.length}}&nbsp;/&nbsp;1</span>
    
  </div>
</template>

<script>

import {uploadImage} from 'network/upload'
export default {
  name: 'ImgUpload',
  components: {

  },
  data() {
    return {
      imgList: []
    }
  },
  props: {
    name: {
      type: String,
      default: ''
    }
  },
  methods: {
    afterRead(file) {
      //new 一个FormData格式的参数
      let formData = new FormData()
      formData.append('image', file.file)
      let config = {
        headers: { //添加请求头
          'Content-Type': 'multipart/form-data'
        }
      }

      uploadImage(formData).then(res => {
        this.$emit('uploadFinished',this.name,res.data);
        this.$notify.success("图片上传成功");
      });

      /*
      return new Promise((resolve, reject) => {
        //把 uploadUrl 换成自己的 上传路径
        axios.post('/api/upload/image', params, config).then(res => {
          if (res && res.data && res.data.status === 1) {				//如果为真 resolve出去
            resolve(res.data)
          } else {
            //否则 Toast 提示
            Toast.fail(res.data && (res.data.msg))
            reject(res.data)
          }
        }).catch(err => {
          Toast.fail('系统异常')
          reject(err)
        });
      }) */

    }
  },
  watch: {
    imgList() {
      //this.$parent.imgList = this.imgList
    },
  }

}
</script>

<style scoped>
.img-box {
  margin: 0 10px;
  background-color: #fff;
  box-shadow: 0 0 6px rgba(51, 51, 51, .12);
  border-radius: 6px;
  overflow: hidden;
  position: relative;
  padding: 10px;
  padding-bottom: 26px;
}
.add-img {
  width: 90px;
  height: 90px;
  border: 1px dashed #888;
  border-radius: 6px;
  line-height: 60px;
  text-align: center;
  font-size: 24px;
  overflow: hidden;
  position: relative;
  /* float: left; */
}
.add-img input[type="file"] {
  width: 100%;
  height: 100%;
  opacity: 0;
}
.add-text {
  font-size: 36px;
  font-weight: 200;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate3d(-50%, -50%, 0);
}
.img-tip {
  position: absolute;
  font-size: 12px;
  color: #888;
  right: 10px;
  bottom: 10px;
}
.van-uploader__preview-image {
  opacity: 0!important;
}
</style>