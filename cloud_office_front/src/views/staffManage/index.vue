<template>
  <div>
    <el-card class="box-card" :style="{marginTop: '10px',height:mainHeight+'px'}">
      <!--      头部搜索和添加-->
      <div style="display: flex;justify-content: space-between;">
        <div style="display: flex;">
          <el-input v-model="queryInfo.query" placeholder="请输入内容" clearable @clear="getTableData" />
          <el-button style="margin-left: 10px" type="primary" icon="el-icon-search" @click="getTableData">查询</el-button>
          <el-button type="primary" icon="el-icon-plus" @click="addClick('添加')">添加</el-button>
          <el-button type="primary" icon="el-icon-download" @click="download">导出所选</el-button>
          <el-button type="primary" icon="el-icon-download" @click="download2">导出所有</el-button>
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
        <el-table-column prop="username" label="姓名" sortable />
        <!--        <el-table-column prop="password" label="密码" />-->
        <el-table-column prop="role" label="角色" sortable>
          <template slot-scope="{row}">
            <div v-if="row.role===1">管理员</div>
            <div v-else>员工</div>
          </template>
        </el-table-column>
        <el-table-column prop="dept" label="部门" sortable />
        <el-table-column label="操作" width="200">
          <template slot-scope="{row}">
            <el-button-group>
              <el-button style="margin-left: 10px" type="text" @click="editClick('编辑',row)">编辑</el-button>
              <el-button style="margin-left: 10px" type="text" @click="deleteClick(row.id)">删除</el-button>
              <el-button style="margin-left: 10px" type="text" @click="resetClick(row.id)">重置密码</el-button>
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
          <el-input v-model="form.name" :disabled="title==='编辑'" style="width: 205px" />
        </el-form-item>
        <!--        <el-form-item label="密码">-->
        <!--          <el-input v-model="form.password" style="width: 205px" />-->
        <!--        </el-form-item>-->
        <el-form-item label="角色">
          <el-radio-group v-model="form.role" @change="roleChange">
            <el-radio label="0">员工</el-radio>
            <el-radio label="1">管理员</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="部门">
          <el-select v-model="form.dept" placeholder="请选择" style="width: 205px" @change="deptChange">
            <el-option
              v-for="item in depts"
              :key="item.id"
              :label="item.name"
              :value="item.id"
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
import {
  deleteUserInfo,
  download,
  getDeptAll,
  getUserInfoAll,
  insertUserInfo,
  resetPassword,
  updateUserInfo
} from '@/api/user'

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
      depts: [],
      editId: 0,
      isDisable: false,
      arrId: []
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
    async download() {
      const res = await download(this.arrId)
      if (res.code === 20000) {
        this.$message.success('下载成功！')

        setTimeout(() => {
          this.$notify.success({
            title: '下载成功！',
            message: '文件保存在E盘Excel下'
          })
        }, 2000)
      }
    },
    async download2() {
      const res = await download()
      if (res.code === 20000) {
        this.$message.success('下载成功！')

        setTimeout(() => {
          this.$notify.success({
            title: '下载成功！',
            message: '文件保存在E盘Excel下'
          })
        }, 2000)
      }
    },
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
    async getDeptAll() {
      const { data: res } = await getDeptAll()
      this.depts = res
    },
    handleSelectionChange(val) {
      this.arrId = []
      this.multipleSelection = val
      for (const valElement of val) {
        this.arrId.push(valElement.id)
      }
    },
    roleChange(a) {
      this.form.role = a
    },
    addClick(op) {
      this.form = {}
      this.dialogVisible = true
      this.title = op
    },
    editClick(op, row) {
      console.log(row)
      // this.isDisable = true
      this.dialogVisible = true
      this.title = op
      this.form.name = row.username
      this.form.role = row.role.toString()
      this.form.dept = row.deptId
      this.editId = row.id
    },
    deptChange(val) {
      this.form.dept = val
    },
    async deleteClick(id) {
      const res = await deleteUserInfo({ id })
      if (res.code === 20000) {
        this.$message.success('删除成功！')
      }
      await this.getTableData()
    },
    async batchDeleteClick() {
      if (this.arrId.length <= 0) {
        return this.$message.error('请至少选择一个')
      }
      const res = await deleteUserInfo({ id: this.arrId.join(',') })
      if (res.code === 20000) {
        this.$message.success('批量删除成功！')
      }
      await this.getTableData()
    },
    async resetClick(id) {
      const res = await resetPassword({ id })
      if (res.code === 20000) {
        this.$message.success('密码重置成功！')
      }
      await this.getTableData()
    },
    async batchResetClick() {
      if (this.arrId.length <= 0) {
        return this.$message.error('请至少选择一个')
      }
      const res = await resetPassword({ id: this.arrId.join(',') })
      if (res.code === 20000) {
        this.$message.success('批量重置成功！')
      }
      await this.getTableData()
    },
    dialogVisibleCancel() {
      this.dialogVisible = false
    },
    async dialogVisibleConfirm() {
      if (this.form.name !== undefined && this.form.role !== undefined && this.form.dept !== undefined) {
        if (this.title === '添加') {
          const data = {
            username: this.form.name,
            role: this.form.role,
            dept: this.form.dept
          }
          const res = await insertUserInfo(data)
          if (res.code === 20000) {
            this.$message.success('添加成功！')
          }
        }
        if (this.title === '编辑') {
          const data = {
            id: this.editId,
            role: this.form.role,
            dept: this.form.dept
          }
          const res = await updateUserInfo(data)
          if (res.code === 20000) {
            this.$message.success('修改成功！')
          }
          // this.isDisable = false
        }
        await this.getTableData()
        this.dialogVisible = false
      } else {
        this.$message.warning('信息未填写完整！')
      }
    }
  }
}
</script>

<style scoped>

</style>
