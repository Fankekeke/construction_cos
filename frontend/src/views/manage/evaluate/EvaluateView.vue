<template>
  <a-modal v-model="show" title="评价详情" @cancel="onClose" :width="800">
    <template slot="footer">
      <a-button key="back" @click="onClose" type="danger">
        关闭
      </a-button>
    </template>
    <div style="font-size: 13px;font-family: SimHei" v-if="evaluateData !== null">
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">基础信息</span></a-col>
        <a-col :span="8"><b>员工编号：</b>
          {{ evaluateData.staffCode }}
        </a-col>
        <a-col :span="8"><b>站点编号：</b>
          {{ evaluateData.stationCode }}
        </a-col>
        <a-col :span="8"><b>检查类型：</b>
            <span v-if="evaluateData.checkType == 1">早</span>
            <span v-if="evaluateData.checkType == 2">中</span>
            <span v-if="evaluateData.checkType == 3">晚</span>
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>打卡时间：</b>
          {{ evaluateData.checkDate }}
        </a-col>
        <a-col :span="8"><b>员工姓名：</b>
          {{ evaluateData.name }}
        </a-col>
        <a-col :span="8"><b>联系方式：</b>
          {{ evaluateData.phone }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>站点名称：</b>
          {{ evaluateData.stationName }}
        </a-col>
        <a-col :span="16"><b>站点地址：</b>
          {{ evaluateData.stationAddress }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">打卡图片</span></a-col>
        <span v-if="fileList.length === 0">暂无图片</span>
        <a-upload
          name="avatar"
          action="http://127.0.0.1:9527/file/fileUpload/"
          list-type="picture-card"
          :file-list="fileList"
          @preview="handlePreview"
          @change="picHandleChange"
        >
        </a-upload>
        <a-modal :visible="previewVisible" :footer="null" @cancel="handleCancel">
          <img alt="example" style="width: 100%" :src="previewImage" />
        </a-modal>
      </a-row>
    </div>
  </a-modal>
</template>

<script>
import moment from 'moment'
moment.locale('zh-cn')
function getBase64 (file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onload = () => resolve(reader.result)
    reader.onerror = error => reject(error)
  })
}
export default {
  name: 'evaluateView',
  props: {
    evaluateShow: {
      type: Boolean,
      default: false
    },
    evaluateData: {
      type: Object
    }
  },
  computed: {
    show: {
      get: function () {
        return this.evaluateShow
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      loading: false,
      fileList: [],
      previewVisible: false,
      previewImage: ''
    }
  },
  watch: {
    evaluateShow: function (value) {
      if (value) {
        this.fileList = []
        this.imagesInit(this.evaluateData.images)
      }
    }
  },
  methods: {
    handleCancel () {
      this.previewVisible = false
    },
    async handlePreview (file) {
      if (!file.url && !file.preview) {
        file.preview = await getBase64(file.originFileObj)
      }
      this.previewImage = file.url || file.preview
      this.previewVisible = true
    },
    picHandleChange ({ fileList }) {
      this.fileList = fileList
    },
    imagesInit (images) {
      if (images !== null && images !== '') {
        let imageList = []
        images.split(',').forEach((image, index) => {
          imageList.push({uid: index, name: image, status: 'done', url: 'http://127.0.0.1:9527/imagesWeb/' + image})
        })
        this.fileList = imageList
      }
    },
    onClose () {
      this.$emit('close')
    }
  }
}
</script>

<style scoped>

</style>
