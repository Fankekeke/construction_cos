<template>
  <a-modal v-model="show" title="站点详情" @cancel="onClose" :width="1000">
    <template slot="footer">
      <a-button key="back" @click="onClose" type="danger">
        关闭
      </a-button>
    </template>
    <div style="font-size: 13px;font-family: SimHei" v-if="stationData !== null">
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">基础信息</span></a-col>
        <a-col :span="8"><b>站点编号：</b>
          {{ stationData.code }}
        </a-col>
        <a-col :span="8"><b>站点名称：</b>
          {{ stationData.stationName ? stationData.stationName : '- -' }}
        </a-col>
        <a-col :span="8"><b>站点地址：</b>
          {{ stationData.stationAddress ? stationData.stationAddress : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>管理人：</b>
          {{ stationData.administrator }}
        </a-col>
        <a-col :span="8"><b>联系电话：</b>
          {{ stationData.phone }}
        </a-col>
        <a-col :span="8"><b>营业状态：</b>
          <span v-if="stationData.delFlag == 0" style="color: red">关闭</span>
          <span v-if="stationData.delFlag == 1" style="color: green">正常</span>
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">图册</span></a-col>
        <a-col :span="24">
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
        </a-col>
      </a-row>
      <br/>
       <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 5px"><span style="font-size: 15px;font-weight: 650;color: #000c17">站点位置</span></a-col>
      </a-row>
      <div>
        <a-card :bordered="false" style="height: 500px">
          <div id="areas" style="width: 100%;height: 450px;box-shadow: 3px 3px 3px rgba(0, 0, 0, .2);background:#ec9e3c;color:#fff"></div>
        </a-card>
      </div>
    </div>
  </a-modal>
</template>

<script>
import baiduMap from '@/utils/map/baiduMap'
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
  name: 'stationView',
  props: {
    stationShow: {
      type: Boolean,
      default: false
    },
    stationData: {
      type: Object
    }
  },
  computed: {
    show: {
      get: function () {
        return this.stationShow
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
      previewImage: '',
      repairInfo: null,
      reserveInfo: null,
      durgList: [],
      logisticsList: [],
      userInfo: null
    }
  },
  watch: {
    stationShow: function (value) {
      if (value) {
        this.imagesInit(this.stationData.images)
        setTimeout(() => {
          baiduMap.initMap('areas')
          setTimeout(() => {
            this.local(this.stationData)
          }, 500)
        }, 200)
      }
    }
  },
  methods: {
    local (stationData) {
      baiduMap.clearOverlays()
      baiduMap.rMap().enableScrollWheelZoom(true)
      // eslint-disable-next-line no-undef
      let point = new BMap.Point(stationData.longitude, stationData.latitude)
      baiduMap.pointAdd(point)
      baiduMap.findPoint(point, 16)
      // let driving = new BMap.DrivingRoute(baiduMap.rMap(), {renderOptions:{map: baiduMap.rMap(), autoViewport: true}});
      // driving.search(new BMap.Point(this.nowPoint.lng,this.nowPoint.lat), new BMap.Point(scenic.point.split(",")[0],scenic.point.split(",")[1]));
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
    onClose () {
      this.$emit('close')
    }
  }
}
</script>

<style scoped>

</style>