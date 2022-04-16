<template>
  <div>
    <el-card class="box-card" :style="{marginTop: '10px',height:mainHeight+'px'}">
      <div style="position: absolute;right: 10px">
        <el-button type="text" @click="checkHistory">查看历史出差记录</el-button>
      </div>
      <el-form ref="form" :model="form" style="width: 700px" label-width="80px">
        <el-form-item label="请假日期">
          <el-date-picker
            v-model="form.date"
            type="date"
            placeholder="请选择日期"
          />
        </el-form-item>
        <el-form-item label="出差原因">
          <el-input
            v-model="form.textarea"
            type="textarea"
            :rows="5"
            style="width: 500px"
            placeholder="请输入出差原因..."
          />
        </el-form-item>
        <div style="margin-top: 100px" />
        <el-form-item>
          <el-button type="primary" @click="onSubmit">提交出差申请</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="50%"
    >
      <!--      <span>这是一段信息</span>-->
      <el-table
        :data="tableData"
        style="width: 100%"
      >
        <el-table-column
          prop="start"
          label="日期"
          width="180"
        >
          <template slot-scope="{row}">
            <div>{{ row.start.substring(0,11) }}</div>
          </template>
        </el-table-column>
        <el-table-column
          prop="isApproved"
          label="是否已审批"
          width="180"
        >
          <template slot-scope="{row}">
            <!--            <div v-if="row.isApproved===">未审批</div>-->
            <div v-if="row.isApproved==='0'">未审批</div>
            <div v-if="row.isApproved==='1'">已通过</div>
            <div v-if="row.isApproved==='2'">未通过</div>
          </template>
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">返回</el-button>
        <!--        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>-->
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getLeaveHistory, leave } from '@/api/clock'
import { getId, getUsername } from '@/utils/auth'

export default {
  name: 'Index',
  data() {
    return {
      form: {
        textarea: '',
        date: ''
      },
      mainHeight: 100,
      tableHeight: 100,
      dialogVisible: false,
      tableData: []
    }
  },
  created() {
    this.getHeight()
  },
  mounted() {
    window.addEventListener('resize', this.getHeight)
    this.getHeight()
  },
  methods: {
    async checkHistory() {
      this.dialogVisible = true
      const data = {
        name: getUsername()
      }
      const res = await getLeaveHistory(data)
      if (res.code === 20000) {
        console.log('请求成功！')
        this.tableData = res.data
      }
    },
    async onSubmit() {
      let d
      if (this.form.date === '' || this.form.date === null) {
        d = ''
      }
      if (this.form.date !== '' && this.form.date !== null) {
        d = this.handleDate(this.form.date.toString())
      }
      const data = {
        name: getId(),
        start: d, // 日期
        other: this.form.textarea// 请假原因
      }
      console.log(data)
      const res = await leave(data)
      if (res.code === 20000) {
        this.$message.success('已提交请假！')
      }
    },
    handleDate(date) {
      const dt = new Date(date)
      const y = dt.getFullYear()
      const m = (dt.getMonth() + 1 + '').padStart(2, '0')
      const d = (dt.getDate() + '').padStart(2, '0')

      // const hh = (dt.getHours() + '').padStart(2, '0')
      // const mm = (dt.getMinutes() + '').padStart(2, '0')
      // const ss = (dt.getSeconds() + '').padStart(2, '0')

      // return `${y}-${m}-${d} ${hh}:${mm}:${ss}`
      return `${y}-${m}-${d} 00:00:00`
    },
    getHeight() {
      this.$nextTick(() => {
        this.mainHeight = window.innerHeight - 100
        this.tableHeight = this.mainHeight - 180
      })
    }
  }
}
</script>

<style scoped>
.box-card{
  /*text-align: center;*/
}
</style>
