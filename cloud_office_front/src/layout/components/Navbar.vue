<template>
  <div class="navbar">
    <hamburger :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />

    <breadcrumb class="breadcrumb-container" />

    <div style="display: flex;justify-content: flex-end;margin-top: 5px;margin-right: 10px">
      <el-button v-if="role==='0'" type="text" @click="editPassword">修改密码</el-button>
      <el-button type="info" @click="backClick">退出</el-button>
      <!--      <el-dropdown class="avatar-container" trigger="click">-->

      <!--        <div class="avatar-wrapper">-->
      <!--          &lt;!&ndash;          <div style="display: block">{{ name }}</div>&ndash;&gt;-->
      <!--          &lt;!&ndash;          <img :src="avatar+'?imageView2/1/w/80/h/80'" class="user-avatar">&ndash;&gt;-->
      <!--          <img src="https://img2.baidu.com/it/u=2187987832,3211119238&fm=253&fmt=auto&app=138&f=PNG?w=300&h=300" class="user-avatar">-->
      <!--          <i class="el-icon-caret-bottom" />-->
      <!--        </div>-->
      <!--        <el-dropdown-menu slot="dropdown" class="user-dropdown">-->
      <!--          <router-link to="/">-->
      <!--            <el-dropdown-item>-->
      <!--              首页-->
      <!--            </el-dropdown-item>-->
      <!--          </router-link>-->
      <!--          &lt;!&ndash;          <a target="_blank" href="https://github.com/PanJiaChen/vue-admin-template/">&ndash;&gt;-->
      <!--          &lt;!&ndash;            <el-dropdown-item>Github</el-dropdown-item>&ndash;&gt;-->
      <!--          &lt;!&ndash;          </a>&ndash;&gt;-->
      <!--          &lt;!&ndash;          <a target="_blank" href="https://panjiachen.github.io/vue-element-admin-site/#/">&ndash;&gt;-->
      <!--          &lt;!&ndash;            <el-dropdown-item>Docs</el-dropdown-item>&ndash;&gt;-->
      <!--          &lt;!&ndash;          </a>&ndash;&gt;-->
      <!--          <el-dropdown-item divided @click.native="logout">-->
      <!--            <span style="display:block;">退出</span>-->
      <!--          </el-dropdown-item>-->
      <!--        </el-dropdown-menu>-->
      <!--      </el-dropdown>-->
    </div>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%"
    >
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="原密码">
          <el-input v-model="form.oldPassword" type="password" />
        </el-form-item>
        <el-form-item label="新密码">
          <el-input v-model="form.newPassword" type="password" />
        </el-form-item>
        <el-form-item label="确认密码">
          <el-input v-model="form.newPassword2" type="password" />
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
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import { getId, getRole, removeId, removeRole, removeUsername } from '@/utils/auth'
import { passwordChange } from '@/api/user'

export default {
  components: {
    Breadcrumb,
    Hamburger
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar'
    ])
  },
  created() {
    this.role = getRole()
  },
  data() {
    return {
      role: '',
      dialogVisible: false,
      form: {
        oldPassword: '',
        newPassword: '',
        newPassword2: ''
      }
    }
  },
  methods: {
    editPassword() {
      this.dialogVisible = true
    },
    dialogVisibleCancel() {
      this.form = {}
      this.dialogVisible = false
    },
    async dialogVisibleConfirm() {
      if (!this.form.oldPassword) {
        this.$message.error('原密码为空')
        return
      }
      if (!this.form.newPassword) {
        this.$message.error('新密码为空')
        return
      }
      if (!this.form.newPassword2) {
        this.$message.error('确认密码为空')
        return
      }
      if (this.form.newPassword !== this.form.newPassword2) {
        this.$message.error('两次密码输入不一致')
        return
      }
      const data = {
        id: getId(),
        password: this.form.oldPassword,
        newPassword: this.form.newPassword2
      }
      const res = await passwordChange(data)
      if (res.code === 20000) {
        this.$message.success('密码修改成功')
      }
      this.dialogVisible = false
      this.form = {}
    },
    async backClick() {
      this.$router.push({ path: '/' })
      removeUsername()
      removeId()
      removeRole()
      await this.$store.dispatch('user/logout')
    },
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      removeUsername()
      removeId()
      removeRole()
      await this.$store.dispatch('user/logout')
      // this.$router.push(`/login?redirect=${this.$route.fullPath}`)
      this.$router.push(`/`)
    }
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color:transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
