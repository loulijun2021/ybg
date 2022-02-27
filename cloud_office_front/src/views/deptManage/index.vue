<template>
  <div>
    <div style="display: flex;justify-content: flex-end;margin:0px 60px 0px 0">
      <el-button
        type="text"
        icon="el-icon-plus"
        @click="addClick"
      >新增部门
      </el-button>
    </div>
    <el-table
      :data="tableData"
      style="width: 100%"
      border
      highlight-current-row
    >
      <el-table-column type="index" />
      <el-table-column
        prop="name"
        label="部门名称"
      />
      <el-table-column label="操作">
        <template slot-scope="{row}">
          <el-button type="text" @click="editClick(row)">修改</el-button>
          <el-button type="text" @click="deleteClick(row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
      :title="title"
      :visible.sync="dialogVisible"
      width="30%"
      @close="inputValue=''"
    >
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="部门名称">
          <el-input v-model="form.inputValue" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisibleConfirm">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { deleteDept, getDeptAll, insertDept, updateDept } from '@/api/user'

export default {
  name: 'Index',
  data() {
    return {
      tableData: [],
      dialogVisible: false,
      form: {
        inputValue: ''
      },
      title: '',
      thisId: 0
    }
  },
  created() {

  },
  mounted() {
    this.getDepts()
  },
  methods: {
    async dialogVisibleConfirm() {
      if (this.title === '新增') {
        await insertDept({ name: this.form.inputValue })
      }
      if (this.title === '修改') {
        await updateDept({ id: this.thisId, name: this.form.inputValue })
      }
      setTimeout(() => {
        this.getDepts()
      }, 100)
      this.dialogVisible = false
    },
    addClick() {
      this.title = '新增'
      this.dialogVisible = true
    },
    async editClick(row) {
      this.title = '修改'
      this.dialogVisible = true
      this.form.inputValue = row.name
      this.thisId = row.id
    },
    async deleteClick(id) {
      await deleteDept({ id })
      setTimeout(() => {
        this.getDepts()
      }, 100)
    },
    async getDepts() {
      const { data: res } = await getDeptAll()
      this.tableData = res
    }
  }
}
</script>

<style scoped>

</style>
