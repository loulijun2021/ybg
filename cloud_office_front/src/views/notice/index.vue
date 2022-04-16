<template>
  <div>
    <div>
      <el-tabs type="border-card" :style="{height:tabHeight+'px'}">
        <el-tab-pane label="通知">
          <div style="margin: 30px">
            公司春节放假通知：

            各部门员工：

            　　新春佳节渐近，为欢度中华民族的传统佳节，使全体人员能够开开心心地过上一个欢乐、祥和的2012年春节，尽可能的与家人一起享受节日的欢乐和喜悦;结合生产情况，经公司研究决定：

            　　公司定于20xx年1月xx日起开始放年假，年假共计x天，截止时间为x月x日，x月x日正式上班。

            　　回家的员工建议通过各种途径提前预订好往返的车票，以免到时“一票难求”，计算好返乡的行程安排，并做好回家的理财准备;同时，公司要求所有的员工必须在20xx年x月x日到达深圳，确保x月x日能够正常上班;届时公司一律不接受电话请假，逾期未到者按旷工处理。

            　　为了让不回家的员工过一个欢乐祥和的春节，同时也方便公司在春节期间的食宿安排和管理工作的开展，特通告好下：

            　　1、决定不回家并留厂的员工(包括外宿员工)如果仍未登记的员工，请尽快到各部门主管/文员处登记，登记时注明员工姓名及部门/级别以及是否在厂内食宿，是享用管理餐还是员工餐等。

            　　2、各部门文员/主管请将增加的统计名单于元月XX日前将留厂人中名单交到行政部

            　　3、行政部将根据留厂人员情况安排用餐及住宿保安相关事宜，请员工决定后再行登记

            　　4、所有留厂员工在春节期间用餐不再扣除伙食费，由公司全额承担，除夕夜与大年初一中餐加餐。

            　　5、留厂员工可以自发组织唱歌、跳舞、游戏等娱乐活动，不得在厂内进行赌博、吸毒、酗酒、打架斗殴等违法违纪活动，违者将按厂规处理。

            　　xx有限责任公司

            　　20xx年x月x日
          </div>

        </el-tab-pane>
        <!--        <el-tab-pane label="通知2">x2</el-tab-pane>-->
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
