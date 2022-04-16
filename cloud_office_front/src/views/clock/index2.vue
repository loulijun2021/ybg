<template>
  <div class="app-container">
    <div style="display: flex;justify-content: center;margin-top:100px;">
      <el-button type="primary" style="width: 300px;height: 300px;font-size: 38px" circle @click="clockClick">
        <div>下班打卡</div>
        <div style="font-size: 18px;margin-top: 10px">{{ date |dataFormat }}</div>
      </el-button>
    </div>
  </div>
</template>

<script>
import { singOut } from '@/api/clock'
import { getId, getUsername } from '@/utils/auth'

export default {
  name: 'Index2',
  data() {
    return {
      date: new Date()
    }
  },
  created() {
    this.currentDate()
  },
  methods: {
    async clockClick() {
      const data = {
        name: getId(),
        end: this.date
      }
      const res = await singOut(data)
      if (res.code === 20000) {
        this.$message.success('下班打卡成功！')
      } else {
        // this.$message.error('')
      }
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

      this.date = `${y}-${m}-${d} ${hh}:${mm}:${ss}`
    }
  }

}
</script>

<style scoped>

</style>
