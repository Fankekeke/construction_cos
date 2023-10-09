<template>
  <a-modal v-model="show" title="评价详情" @cancel="onClose" :width="1000">
    <template slot="footer">
      <a-button key="back" @click="onClose" type="danger">
        关闭
      </a-button>
    </template>
    <div style="font-size: 13px;font-family: SimHei" v-if="evaluateData !== null">
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">员工信息</span></a-col>
        <a-col :span="8"><b>员工姓名：</b>
          {{ evaluateData.name }}
        </a-col>
        <a-col :span="8"><b>联系方式：</b>
          {{ evaluateData.phone }}
        </a-col>
        <a-col :span="8"><b>性别：</b>
            <span v-if="evaluateData.sex == 1">男</span>
            <span v-if="evaluateData.sex == 2">女</span>
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>员工编号：</b>
          {{ evaluateData.staffCode }}
        </a-col>
        <a-col :span="8"><b>工作得分：</b>
          {{ evaluateData.workScore }} 分
        </a-col>
        <a-col :span="8"><b>考勤得分：</b>
          {{ evaluateData.attendanceScore }} 分
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>质量得分：</b>
          {{ evaluateData.qualityScore }} 分
        </a-col>
        <a-col :span="8"><b>评价年份：</b>
          {{ evaluateData.year }}
        </a-col>
        <a-col :span="8"><b>评价月份：</b>
          {{ evaluateData.month }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>评价人：</b>
          {{ evaluateData.appraiser }}
        </a-col>
        <a-col :span="8"><b>备注：</b>
          {{ evaluateData.remark }}
        </a-col>
        <a-col :span="8"><b>评价时间：</b>
          {{ evaluateData.createDate }}
        </a-col>
      </a-row>
      <a-row v-if="loading" style="padding: 15px">
        <a-col>
          <apexchart type="radar" height="350" :options="chartOptions" :series="series"></apexchart>
        </a-col>
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
      previewImage: '',
      series: [{
        name: '得分',
        data: []
      }],
      chartOptions: {
        chart: {
          height: 380,
          type: 'radar'
        },
        title: {
          text: '评价得分'
        },
        xaxis: {
          categories: ['质量得分', '工作得分', '考勤得分', '综合得分']
        }
      }
    }
  },
  watch: {
    evaluateShow: function (value) {
      if (value) {
        this.checkEvaluate(this.evaluateData)
      }
    }
  },
  methods: {
    checkEvaluate (score) {
      this.loading = false
      let data = [score.workScore, score.attendanceScore, score.qualityScore, 100]
      this.series[0].data = data
      this.chartOptions.title.text = score.name + '评价得分'
      setTimeout(() => {
        this.loading = true
      }, 500)
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
      setTimeout(() => {
        this.loading = false
      }, 200)
      this.$emit('close')
    }
  }
}
</script>

<style scoped>

</style>
