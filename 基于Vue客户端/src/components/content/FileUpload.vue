<template>
  <div class="file-box">
    <van-uploader accept="file" v-model="fileList" multiple :max-count="1" :after-read="afterRead" >
      <!-- <div class="add-file" v-show="fileList.length<9">
        <span class="add-text">＋</span>
      </div> -->
    </van-uploader>
    
    <span class="file-tip">文件&nbsp;{{fileList.length}}&nbsp;/&nbsp;1</span>
    
  </div>
</template>

<script>

import {uploadFile} from 'network/upload'

export default {
  name: 'FileUpload',
  components: {

  },
  data() {
    return {
      fileList: []
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
      formData.append('file', file.file)

      uploadFile(formData).then(res => {
        this.$emit('uploadFinished',this.name,res.data);
        this.$notify.success("文件上传成功");
      });


    }
  },
  watch: {
    fileList() {
      //this.$parent.fileList = this.fileList
    },
  }

}
</script>

<style scoped>
.file-box {
  margin: 0 10px;
  background-color: #fff;
  box-shadow: 0 0 6px rgba(51, 51, 51, .12);
  border-radius: 6px;
  overflow: hidden;
  position: relative;
  padding: 10px;
  padding-bottom: 26px;
}
.add-file {
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
.add-file input[type="file"] {
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
.file-tip {
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