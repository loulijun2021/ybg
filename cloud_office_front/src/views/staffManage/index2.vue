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
        <el-table-column prop="dept" label="部门" />
        <el-table-column label="日期">
          <template slot-scope="{row}">
            <div>{{ row.start.substring(0,11) }}</div>
          </template>
        </el-table-column>
        <el-table-column prop="start" label="上班时间">
          <template slot-scope="{row}">
            <div>{{ row.start.substring(11) }}</div>
          </template>
        </el-table-column>
        <el-table-column prop="end" label="下班时间">
          <template slot-scope="{row}">
            <div v-if="row.end">{{ row.end.substring(11) }}</div>
          </template>
        </el-table-column>
        <el-table-column prop="goOut" label="出差">
          <template slot-scope="{row}">
            <div v-if="row.goOut===0">未出差</div>
            <div v-if="row.goOut===1">已出差</div>
          </template>
        </el-table-column>
        <el-table-column prop="leave" label="请假">
          <template slot-scope="{row}">
            <div v-if="row.leave===1">已请假</div>
            <div v-if="row.leave===0">未请假</div>
          </template>
        </el-table-column>
        <el-table-column prop="other" label="原因" />
        <el-table-column label="操作">
          <template slot-scope="{row}">
            <div v-if="row.isApproved==='0'"><el-button type="text">未审批</el-button></div>
            <div v-if="row.isApproved==='1'"><el-button type="text">已审批</el-button></div>
          </template>
        </el-table-column>

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
import Pagination from '@/components/Pagination'
import { getDeptAll } from '@/api/user' // secondary package based on el-pagination

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
    this.getDeptAll()
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
      let d
      if (this.queryInfo.date === '' || this.queryInfo.date === null) {
        d = ''
      }
      if (this.queryInfo.date !== '' && this.queryInfo.date !== null) {
        d = this.handleDate(this.queryInfo.date.toString())
      }

      const data = {
        pageNum: this.queryInfo.pageNum,
        pageSize: this.queryInfo.pageSize,
        keyword: this.queryInfo.query,
        dept: this.queryInfo.dept,
        date: d
      }
      const { data: res } = await getAttendanceAll(data)
      this.tableData = res.list
      this.total = res.total
    },
    handleDate(date) {
      const dt = new Date(date)
      const y = dt.getFullYear()
      const m = (dt.getMonth() + 1 + '').padStart(2, '0')
      const d = (dt.getDate() + '').padStart(2, '0')
      return `${y}-${m}-${d}`
    },
    async getDeptAll() {
      const { data: res } = await getDeptAll()
      this.depts = res
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
