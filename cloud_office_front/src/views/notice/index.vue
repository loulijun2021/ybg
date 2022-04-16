<template>
  <div>
    <div>
      <el-tabs type="border-card" :style="{height:tabHeight+'px'}">
        <el-tab-pane label="通知1">
          x1
        </el-tab-pane>
        <el-tab-pane label="通知2">x2</el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Index',
  data() {
    return {
      tabHeight: 400
    }
  },
  created() {

  },
  mounted() {
    this.getHeight()
    window.onresize = () => {
      this.getHeight()
    }
  },
  methods: {
    getHeight() {
      const height = document.documentElement.clientHeight
      this.tabHeight = height - 100
    }
  }
}
</script>

<style scoped>

</style>
