<template>
  <div>
    <el-card class="box-card" :style="{marginTop: '10px',height:mainHeight+'px'}">
      <!--      头部搜索和添加-->
      <div style="display: flex;justify-content: space-between;">
        <div style="display: flex;width: 500px">
          <el-input v-model="queryInfo.query" placeholder="请输入内容" clearable @clear="getTableData" />
          <el-button style="margin-left: 10px" type="primary" icon="el-icon-search" @click="getTableData">查询</el-button>
          <el-button type="primary" icon="el-icon-plus" @click="addClick('添加')">添加</el-button>
        </div>
        <div style="display: flex">
          <el-button-group>
            <el-button type="text" @click="batchDeleteClick">批量删除</el-button>
            <el-button type="text" style="margin-left: 10px" @click="batchResetClick">批量重置</el-button>
          </el-button-group>
        </div>
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
        <el-table-column prop="username" label="姓名" />
        <!--        <el-table-column prop="password" label="密码" />-->
        <el-table-column prop="role" label="角色">
          <template slot-scope="{row}">
            <div v-if="row.role===1">管理员</div>
            <div v-else>员工</div>
          </template>
        </el-table-column>
        <el-table-column prop="dept" label="部门" />
        <el-table-column label="操作" width="200">
          <template slot-scope="{row}">
            <el-button-group>
              <el-button style="margin-left: 10px" type="text" @click="editClick('编辑',row)">编辑</el-button>
              <el-button style="margin-left: 10px" type="text" @click="deleteClick(row.id)">删除</el-button>
              <el-button style="margin-left: 10px" type="text" @click="deleteClick(row.id)">重置密码</el-button>
            </el-button-group>
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

    <el-dialog
      :title="title"
      :visible.sync="dialogVisible"
      width="30%"
    >
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="姓名">
          <el-input v-model="form.name" style="width: 205px" />
        </el-form-item>
        <!--        <el-form-item label="密码">-->
        <!--          <el-input v-model="form.password" style="width: 205px" />-->
        <!--        </el-form-item>-->
        <el-form-item label="角色">
          <el-input v-model="form.role" style="width: 205px" />
        </el-form-item>
        <el-form-item label="部门">
          <el-select v-model="form.dept" placeholder="请选择" style="width: 205px">
            <el-option
              v-for="item in depts"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisibleCancel">取 消</el-button>
        <el-button type="primary" @click="dialogVisibleConfirm">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import { getUserInfoAll } from '@/api/user'

export default {

  name: 'Index',
  components: { Pagination },
  data() {
    return {
      mainHeight: 100,
      tableHeight: 100,
      tableData: [],
      queryInfo: {
        query: '', // 查询条件
        pageNum: 1, // 当前的页数
        pageSize: 5 // 当前每页显示多少条数据
      },
      total: 0,
      multipleSelection: [],
      title: '',
      dialogVisible: false,
      form: {
        name: '',
        // password: '',
        role: '',
        dept: ''
      },
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
        keyword: this.queryInfo.query
      }
      const { data: res } = await getUserInfoAll(data)
      this.tableData = res.list
      this.total = res.total
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    addClick(op) {
      this.dialogVisible = true
      this.title = op
    },
    editClick(op, row) {

    },
    deleteClick(id) {

    },
    batchDeleteClick() {

    },
    batchResetClick() {

    },
    dialogVisibleCancel() {
      this.dialogVisible = false
    },
    dialogVisibleConfirm() {
      this.dialogVisible = false
    }
  }
}
</script>

<style scoped>

</style>
