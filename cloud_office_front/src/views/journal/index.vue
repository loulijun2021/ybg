<template>
  <div>
    <div style="width: 50%;margin-top: 30px">
      <el-form ref="form" :model="form" label-width="200px">
        <el-form-item label="时间">
          <div>{{ form.date|dataFormat }}</div>
        </el-form-item>
        <el-form-item label="日志内容" required>
          <el-input
            v-model="form.textarea"
            :disabled="isDisabled"
            type="textarea"
            :rows="5"
            placeholder="请输入日志内容"
          />
        </el-form-item>
      </el-form>
      <div style="display: flex;justify-content: center">
        <el-button type="info" @click="clearClick">清空内容</el-button>
        <el-button type="primary" @click="submitClick">提交日志</el-button>
      </div>
    </div>
    <div style="width: 50%;">
      <el-timeline>
        <el-timeline-item v-for="item in recordAll" :timestamp="item.date" placement="top">
          <el-card>
            <div>{{ item.record }}</div>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
  </div>
</template>

<script>
import { getId, getUsername } from '@/utils/auth'
import { addRecord, getRecordAllByName } from '@/api/journal'

export default {
  name: 'Index',
  data() {
    return {
      form: {
        date: new Date(),
        textarea: ''
      },
      isDisabled: false,
      recordAll: []
    }
  },
  created() {
    this.currentDate()
    this.getRecordAllByName()
  },
  methods: {
    async getRecordAllByName() {
      const data = {
        name: getUsername()
      }
      const res = await getRecordAllByName(data)
      if (res.code === 20000) {
        this.recordAll = res.data
      } else {
        this.$message.error(res.message)
      }
    },
    async submitClick() {
      if (this.form.textarea.trim().length === 0) {
        return this.$message.error('日志内容不能为空！')
      }

      const data = {
        name: getId(),
        record: this.form.textarea
      }
      const res = await addRecord(data)
      if (res.code === 20000) {
        this.$message.success('日志添加成功！')
        this.form.textarea = ''
        this.isDisabled = true
        await this.getRecordAllByName()
      } else {
        this.$message.error(res.message)
      }
    },
    clearClick() {
      this.form.textarea = ''
      this.$message.info('日志内容清空成功！')
      // this.$notify.success('日志内容清空成功！')
    },
    currentDate() {
      setInterval(this.getTime, 500)
    },
    async getTime() {
      const dt = await new Date()

      const y = dt.getFullYear()
      const m = (dt.getMonth() + 1 + '').padStart(2, '0')
      const d = (dt.getDate() + '').padStart(2, '0')

      const hh = (dt.getHours() + '').padStart(2, '0')
      const mm = (dt.getMinutes() + '').padStart(2, '0')
      const ss = (dt.getSeconds() + '').padStart(2, '0')

      this.form.date = `${y}-${m}-${d} ${hh}:${mm}:${ss}`
    }
  }

}
</script>

<style scoped>

</style>
