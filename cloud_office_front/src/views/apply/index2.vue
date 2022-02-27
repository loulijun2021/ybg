<!--<template>-->
<!--  <div>-->
<!--    <el-card class="box-card" :style="{marginTop: '10px',height:mainHeight+'px'}">-->

<!--      <el-form ref="form" :model="form" label-width="80px">-->
<!--        <el-form-item label="请假日期">-->
<!--          <el-date-picker-->
<!--            v-model="form.date"-->
<!--            type="date"-->
<!--            placeholder="请选择日期"-->
<!--          />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="请假原因">-->
<!--          <el-input-->
<!--            v-model="form.textarea"-->
<!--            type="textarea"-->
<!--            :rows="5"-->
<!--            style="width: 500px"-->
<!--            placeholder="请输入请假原因..."-->
<!--          />-->
<!--        </el-form-item>-->

<!--        <el-form-item>-->
<!--          <el-button type="primary" @click="onSubmit">提交请假申请</el-button>-->
<!--          <el-button>取消</el-button>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--    </el-card>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import { leave } from '@/api/clock'-->
<!--import { getId } from '@/utils/auth'-->

<!--export default {-->
<!--  name: 'Index2',-->
<!--  data() {-->
<!--    return {-->
<!--      form: {-->
<!--        textarea: '',-->
<!--        date: ''-->
<!--      },-->
<!--      mainHeight: 100,-->
<!--      tableHeight: 100-->
<!--    }-->
<!--  },-->
<!--  created() {-->
<!--    this.getHeight()-->
<!--  },-->
<!--  mounted() {-->
<!--    window.addEventListener('resize', this.getHeight)-->
<!--    this.getHeight()-->
<!--  },-->
<!--  methods: {-->
<!--    async onSubmit() {-->
<!--      let d-->
<!--      if (this.form.date === '' || this.form.date === null) {-->
<!--        d = ''-->
<!--      }-->
<!--      if (this.form.date !== '' && this.form.date !== null) {-->
<!--        d = this.handleDate(this.form.date.toString())-->
<!--      }-->
<!--      const data = {-->
<!--        name: getId(),-->
<!--        start: d, // 日期-->
<!--        other: this.form.textarea// 请假原因-->
<!--      }-->
<!--      console.log(data)-->
<!--      const res = await leave(data)-->
<!--      if (res.code === 20000) {-->
<!--        this.$message.success('已提交请假！')-->
<!--      }-->
<!--    },-->
<!--    handleDate(date) {-->
<!--      const dt = new Date(date)-->
<!--      const y = dt.getFullYear()-->
<!--      const m = (dt.getMonth() + 1 + '').padStart(2, '0')-->
<!--      const d = (dt.getDate() + '').padStart(2, '0')-->

<!--      // const hh = (dt.getHours() + '').padStart(2, '0')-->
<!--      // const mm = (dt.getMinutes() + '').padStart(2, '0')-->
<!--      // const ss = (dt.getSeconds() + '').padStart(2, '0')-->

<!--      // return `${y}-${m}-${d} ${hh}:${mm}:${ss}`-->
<!--      return `${y}-${m}-${d} 00:00:00`-->
<!--    },-->
<!--    getHeight() {-->
<!--      this.$nextTick(() => {-->
<!--        this.mainHeight = window.innerHeight - 100-->
<!--        this.tableHeight = this.mainHeight - 180-->
<!--      })-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->
<!--.box-card{-->
<!--  /*text-align: center;*/-->
<!--}-->
<!--</style>-->
