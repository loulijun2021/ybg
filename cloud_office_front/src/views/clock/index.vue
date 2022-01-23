<template>
  <div class="app-container">
    <div style="display: flex;justify-content: space-between">
      <el-button type="primary" style="width: 300px;height: 300px;font-size: 38px" circle @click="clockClick">
        <div>上班打卡</div>
        <div style="font-size: 18px;margin-top: 10px">{{ date |dataFormat }}</div>
      </el-button>
      <!--      <el-button type="primary" style="width: 300px;height: 300px;font-size: 38px" circle>-->
      <!--        <div>下班打卡</div>-->
      <!--        <div style="font-size: 18px;margin-top: 10px">{{ date |dataFormat }}</div>-->
      <!--      </el-button>-->
    </div>
  </div>
</template>
<script>
import { singIn } from '@/api/clock'
import { getUsername } from '@/utils/auth'

export default {
  name: 'Index',
  data() {
    return {
      date: new Date()
    }
  },
  created() {
    this.currentDate()
    // this.getUserInfoAll()
  },
  methods: {
    async getUserInfoAll() {
      // const data = {
      //   pageNum: 1,
      //   pageSize: 5,
      //   keyword: ''
      // }
      // await getUserInfoAll(data)
      //
      // const data = {
      //   username: 'xxx3',
      //   password: '123',
      //   role: 0,
      //   dept: 1
      // }
      // const data = { id: '18,19' }
      // await insertUserInfo(data)
      // await deleteUserInfo(data)
      // const data = {
      //   id: 9,
      //   username: 'hjy2',
      //   password: '123',
      //   role: 0,
      //   dept: 1
      // }
      // await updateUserInfo(data)
    },
    async clockClick() {
      const data = {
        name: getUsername(),
        start: this.date
      }
      await singIn(data)
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
