<template>
  <a-modal v-model="show" title="新增评价" @cancel="onClose" :width="800">
    <template slot="footer">
      <a-button key="back" @click="onClose">
        取消
      </a-button>
      <a-button key="submit" type="primary" :loading="loading" @click="handleSubmit">
        提交
      </a-button>
    </template>
    <a-form :form="form" layout="vertical">
      <a-row :gutter="20">
        <a-col :span="12">
          <a-form-item label='选择员工' v-bind="formItemLayout">
            <a-select allowClear v-decorator="[
              'staffCode',
              { rules: [{ required: true, message: '请输入员工姓名!' }] }
              ]">
              <a-select-option :value="item.code" v-for="(item, index) in staffList" :key="index">{{ item.name }}</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='工作得分' v-bind="formItemLayout">
            <a-input v-decorator="[
            'workScore',
            { rules: [{ required: true, message: '请输入工作得分!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='考勤得分' v-bind="formItemLayout">
            <a-input v-decorator="[
            'attendanceScore',
            { rules: [{ required: true, message: '请输入考勤得分!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='质量得分' v-bind="formItemLayout">
            <a-input v-decorator="[
            'qualityScore',
            { rules: [{ required: true, message: '请输入质量得分!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='评价年份' v-bind="formItemLayout">
            <a-input v-decorator="[
            'year',
            { rules: [{ required: true, message: '请输入评价年份!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='评价月份' v-bind="formItemLayout">
            <a-input v-decorator="[
            'month',
            { rules: [{ required: true, message: '请输入评价月份!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='评价人' v-bind="formItemLayout">
            <a-input v-decorator="[
            'appraiser',
            { rules: [{ required: true, message: '请输入评价月份!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='备注' v-bind="formItemLayout">
            <a-input v-decorator="[
            'remark'
            ]"/>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-modal>
</template>

<script>
import {mapState} from 'vuex'
function getBase64 (file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onload = () => resolve(reader.result)
    reader.onerror = error => reject(error)
  })
}
const formItemLayout = {
  labelCol: { span: 24 },
  wrapperCol: { span: 24 }
}
export default {
  name: 'evaluateAdd',
  props: {
    evaluateAddVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.evaluateAddVisiable
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      formItemLayout,
      form: this.$form.createForm(this),
      loading: false,
      fileList: [],
      previewVisible: false,
      previewImage: '',
      staffList: [],
      stationList: []
    }
  },
  mounted () {
    this.getStaff()
    this.getStation()
  },
  methods: {
    getStaff () {
      this.$get('/cos/staff-info/list').then((r) => {
        this.staffList = r.data.data
      })
    },
    getStation () {
      this.$get('/cos/station-info/list').then((r) => {
        this.stationList = r.data.data
      })
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
    reset () {
      this.loading = false
      this.form.resetFields()
    },
    onClose () {
      this.reset()
      this.$emit('close')
    },
    handleSubmit () {
      // 获取图片List
      let images = []
      this.fileList.forEach(image => {
        images.push(image.response)
      })
      this.form.validateFields((err, values) => {
        values.images = images.length > 0 ? images.join(',') : null
        if (!err) {
          values.publisher = this.currentUser.userId
          this.loading = true
          this.$post('/cos/evaluate-info', {
            ...values
          }).then((r) => {
            this.reset()
            this.$emit('success')
          }).catch(() => {
            this.loading = false
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
