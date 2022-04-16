<template>
  <div style="margin: 20px">
    <div>
      <el-form ref="form" :inline="true" :model="form" label-width="80px">
        <el-form-item label="关键字">
          <el-input v-model="form.keyword" style="width:500px;" placeholder="请输入（姓名/日志内容/时间）等关键字" clearable />
        </el-form-item>
        <el-button type="primary">查询</el-button>
      </el-form>
    </div>
    <el-table
      :data="tableData"
      border
      style="width: 100%"
    >
      <el-table-column prop="name" label="姓名" />
      <el-table-column prop="record" label="日志" />
      <el-table-column prop="date" label="日期" />
      <el-table-column label="操作" width="100">
        <template slot-scope="{row}">
          <!--          <el-button type="text">查看</el-button>-->
          <el-button type="text">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getRecordAll } from '@/api/journal'

export default {
  name: 'Index',
  data() {
    return {
      form: {
        keyword: ''
      },
      tableData: []
    }
  },
  created() {
    this.getRecordAll()
  },
  methods: {
    getRecordAll() {
      getRecordAll().then(res => {
        if (res.code === 20000) {
          this.tableData = res.data
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
