import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/clock/index',
    hidden: true,
    children: [{
      path: 'dashboard',
      name: '首页',
      component: () => import('@/views/dashboard/index'),
      // meta: { title: '首页', icon: 'dashboard' }
      meta: { title: '首页', icon: '' }
    }]
  }
]
export const asyncRoutes = [
  {
    path: '/clock',
    component: Layout,
    redirect: '/clock/index',
    meta: { title: '员工打卡', icon: '', roles: ['0'] },
    children: [
      {
        path: 'index',
        name: '上班打卡',
        component: () => import('@/views/clock/index'),
        meta: { title: '上班打卡', icon: '', roles: ['0'] }
      }, {
        path: 'index2',
        name: '下班打卡',
        component: () => import('@/views/clock/index2'),
        meta: { title: '下班打卡', icon: '', roles: ['0'] }
      }
    ]
  },
  {
    path: '/apply',
    component: Layout,
    meta: { title: '员工申请', icon: '', roles: ['0'] },
    children: [
      {
        path: 'index',
        name: '申请请假',
        component: () => import('@/views/apply/index'),
        meta: { title: '申请请假', icon: '', roles: ['0'] }
      }, {
        path: 'index2',
        name: '申请出差',
        component: () => import('@/views/apply/index2'),
        meta: { title: '申请出差', icon: '', roles: ['0'] }
      }
    ]
  },
  {
    path: '/journal',
    component: Layout,
    children: [
      {
        path: 'index',
        name: '员工日志',
        component: () => import('@/views/journal/index'),
        meta: { title: '员工日志', icon: '', roles: ['0'] }
      }
    ]
  },
  {
    path: '/notice',
    component: Layout,
    children: [
      {
        path: 'index',
        name: '员工公告',
        component: () => import('@/views/notice/index'),
        meta: { title: '员工公告', icon: '', roles: ['0'] }
      }
    ]
  }, {
    path: '/staffManage',
    component: Layout,
    meta: { title: '员工管理', icon: '', roles: ['1'] },
    children: [
      {
        path: 'index',
        name: '基本信息',
        component: () => import('@/views/staffManage/index'),
        meta: { title: '基本信息', icon: '', roles: ['1'] }
      }, {
        path: 'index2',
        name: '考勤情况',
        component: () => import('@/views/staffManage/index2'),
        meta: { title: '考勤情况', icon: '', roles: ['1'] }
      }
    ]
  }, {
    path: '/journalManage',
    component: Layout,
    children: [
      {
        path: 'index',
        name: '日志管理',
        component: () => import('@/views/journalManage/index'),
        meta: { title: '日志管理', icon: '', roles: ['1'] }
      }
    ]
  }, {
    path: '/noticeManage',
    component: Layout,
    children: [
      {
        path: 'index',
        name: '公告管理',
        component: () => import('@/views/noticeManage/index'),
        meta: { title: '公告管理', icon: '', roles: ['1'] }
      }
    ]
  },
  {
    path: '/statistics',
    component: Layout,
    children: [
      {
        path: 'index',
        name: '数据统计',
        component: () => import('@/views/statistics/index'),
        meta: { title: '数据统计', icon: '', roles: ['1'] }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
