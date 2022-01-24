<template>
  <div>
    <el-card class="box-card" :style="{marginTop: '10px',height:mainHeight+'px'}">
      <div style="display: flex;width: 90%">
        <el-input
          v-model="queryInfo.query"
          style="width: 205px"
          placeholder="请输入内容"
          clearable
          @clear="getTableData"
        />
        <el-select
          v-model="queryInfo.dept"
          clearable
          placeholder="请选择部门"
          style="width: 205px;margin-left: 10px;"
          @change="deptChange"
        >
          <el-option
            v-for="item in depts"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
        <el-date-picker
          v-model="queryInfo.date"
          style="margin-left: 10px"
          type="date"
          placeholder="选择日期"
        />
        <el-button style="margin-left: 10px" type="primary" icon="el-icon-search" @click="getTableData">查询</el-button>

      </div>
      <!--      表格部分-->
      <el-table
        :data="tableData"
        border
        highlight-current-row
        stripe
        :style="{marginTop:20+'px',height: tableHeight+'px'}"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" />
        <el-table-column prop="name" label="姓名" />
        <el-table-column prop="dept" label="姓名" />
        <el-table-column prop="start" label="上班打卡" />
        <el-table-column prop="end" label="下班打卡" />
        <el-table-column prop="goOut" label="出差" />
        <el-table-column prop="leave" label="请假" />
        <el-table-column prop="other" label="原因" />
        <el-table-column label="操作" />

      </el-table>
      <!--分页-->
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryInfo.pageNum"
        :limit.sync="queryInfo.pageSize"
        align="right"
        @pagination="getTableData"
      />
    </el-card>
  </div>
</template>

<script>
import { getAttendanceAll } from '@/api/clock'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

export default {
  name: 'Index2',
  components: { Pagination },
  data() {
    return {
      mainHeight: 100,
      tableHeight: 100,
      tableData: [],
      multipleSelection: [],
      queryInfo: {
        query: '', // 查询条件
        dept: '',
        date: '',
        pageNum: 1, // 当前的页数
        pageSize: 5 // 当前每页显示多少条数据
      },
      total: 10,
      depts: []

    }
  },
  created() {
    this.getHeight()
    this.getTableData()
  },
  mounted() {
    window.addEventListener('resize', this.getHeight)
    this.getHeight()
  },
  methods: {
    getHeight() {
      this.$nextTick(() => {
        this.mainHeight = window.innerHeight - 100
        this.tableHeight = this.mainHeight - 180
      })
    },
    async getTableData() {
      const data = {
        pageNum: this.queryInfo.pageNum,
        pageSize: this.queryInfo.pageSize,
        keyword: this.queryInfo.query,
        dept: this.queryInfo.dept,
        date: this.queryInfo.date
      }
      const { data: res } = await getAttendanceAll(data)
      this.tableData = res.list
      this.total = res.total
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    deptChange(a) {
      this.queryInfo.dept = a
    }
  }
}
</script>

<style scoped>

</style>
