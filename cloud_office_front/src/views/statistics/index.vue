<template>
  <div>
    <div style="margin: 50px">
      <div id="echartsId" style="width:100%;height: 600px;margin: 30px;" />
    </div>
  </div>
</template>

<script>

export default {
  name: 'Index',
  data() {
    return {}
  },
  created() {
  },
  mounted() {
    this.initCharts()
  },
  methods: {
    initCharts() {
      const myChart = this.$echarts.init(document.getElementById('echartsId'))
      myChart.setOption({
        barWidth: 100,
        // grid: {
        //   top: 25,
        //   left: 20,
        //   right: 20,
        //   bottom: 20,
        //   containLabel: true
        // },
        tooltip: {
          show: true,
          trigger: 'axis', // axis , item
          backgroundColor: 'RGBA(0, 0, 0, 0.3)',
          borderColor: 'rgba(0, 151, 251, 0.6)',
          borderWidth: 1,
          borderRadius: 0,
          textStyle: {
            color: '#BCE9FC',
            fontSize: 14,
            align: 'left'
          },
          formatter: function(params) {
            var res = '' + params[0].name
            var valueMap = new Map()
            for (var i = 0, l = params.length; i < l; i++) {
              if (params[i].value !== '-') {
                valueMap.set(params[i].seriesName, params[i])
              }
            }
            valueMap.forEach((value, key) => {
              res += '<br/>' + value.marker + '出勤天数' + ' : ' + value.value
            })
            return res
          }
        },
        xAxis: {
          type: 'category',
          name: '姓名',
          data: ['zjq', 'llj', 'cyx', 'lisi', 'kss', 'cak', 'qkz']
        },
        yAxis: {
          type: 'value',
          name: '出勤天数',

          axisLabel: {
            textStyle: {
              fontSize: 14
            }
          },
          // nameTextStyle: {
          //   color: '#fff',
          //   fontSize: 14,
          //   lineHeight: 40
          // },
          splitLine: {
            show: true
          },
          axisLine: {
            show: true
          },
          axisTick: {
            show: true
          }

        },
        series: [
          {
            data: [28, 30, 29, 25, 26, 27, 28],
            type: 'bar',
            showBackground: true,
            backgroundStyle: {
              color: 'rgba(180, 180, 180, 0.2)'
            }
          }
        ]
      })
    }
    //   myChart.setOption(
    //     {
    //
    //       tooltip: {
    //
    //         trigger: 'axis',
    //
    //         axisPointer: { // 坐标轴指示器，坐标轴触发有效
    //
    //           type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
    //
    //         }
    //
    //       },
    //
    //       legend: {
    //
    //         data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎', '百度', '谷歌', '必应', '其他']
    //
    //       },
    //
    //       grid: {
    //
    //         left: '3%',
    //
    //         right: '4%',
    //
    //         bottom: '3%',
    //
    //         containLabel: true
    //
    //       },
    //
    //       xAxis: [
    //
    //         {
    //
    //           type: 'category',
    //
    //           data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
    //
    //         }
    //
    //       ],
    //
    //       yAxis: [
    //
    //         {
    //
    //           type: 'value'
    //
    //         }
    //
    //       ],
    //
    //       series: [
    //
    //         {
    //           name: '直接访问',
    //           type: 'bar',
    //           data: [320, 332, 301, 334, 390, 330, 320]
    //         },
    //
    //         {
    //           name: '邮件营销',
    //           type: 'bar',
    //           stack: '广告',
    //           data: [120, 132, 101, 134, 90, 230, 210]
    //         },
    //
    //         {
    //           name: '联盟广告',
    //           type: 'bar',
    //           stack: '广告',
    //           data: [220, 182, 191, 234, 290, 330, 310]
    //         },
    //
    //         {
    //           name: '视频广告',
    //           type: 'bar',
    //           stack: '广告',
    //           data: [150, 232, 201, 154, 190, 330, 410]
    //         },
    //
    //         {
    //           name: '搜索引擎',
    //           type: 'bar',
    //           data: [862, 1018, 964, 1026, 1679, 1600, 1570],
    //           markLine: {
    //             lineStyle: {
    //               type: 'dashed'
    //             },
    //             data: [
    //               [{ type: 'min' }, { type: 'max' }]
    //             ]
    //           }
    //         },
    //
    //         {
    //           name: '百度',
    //           type: 'bar',
    //           barWidth: 5,
    //           stack: '搜索引擎',
    //           data: [620, 732, 701, 734, 1090, 1130, 1120]
    //         },
    //
    //         {
    //           name: '谷歌',
    //           type: 'bar',
    //           stack: '搜索引擎',
    //           data: [120, 132, 101, 134, 290, 230, 220]
    //         },
    //
    //         {
    //           name: '必应',
    //           type: 'bar',
    //           stack: '搜索引擎',
    //           data: [60, 72, 71, 74, 190, 130, 110]
    //         },
    //
    //         {
    //           name: '其他',
    //           type: 'bar',
    //           stack: '搜索引擎',
    //           data: [62, 82, 91, 84, 109, 110, 120]
    //         }
    //
    //       ]
    //
    //     })
    // }
  }
}
</script>

<style scoped>

</style>
