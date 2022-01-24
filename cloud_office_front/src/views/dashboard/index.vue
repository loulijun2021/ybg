<template>
  <div class="dashboard-container">
    <!--    <div class="dashboard-text">name: {{ name }}</div>-->
    <div style="font-size: 30px;text-align: center;margin-top: 15%">欢迎&nbsp;&nbsp;&nbsp;&nbsp;{{ name }}&nbsp;&nbsp;&nbsp;&nbsp;来到云办公系统</div>

  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { getUsername } from '@/utils/auth'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  data() {
    return {
      name: getUsername()

    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
