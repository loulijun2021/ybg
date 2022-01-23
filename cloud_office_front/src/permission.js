import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getRole, getToken, removeRole } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'
import de from 'element-ui/src/locale/lang/de'
import { constantRoutes, userRoutes, adminRoutes } from '@/router'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login'] // no redirect whitelist

router.beforeEach(async(to, from, next) => {
  // start progress bar
  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)

  const hasToken = 'admin-token'
  if (hasToken) {
    next()
    // 判断权限
    if (getRole() === '1') {
      await store.dispatch('permission/generateRoutes', ['1'])
    } else if (getRole() === '0') {
      await store.dispatch('permission/generateRoutes', ['0'])
    }
    // 动态添加可访问路由表
    router.addRoutes(store.getters.addRouters)
  }

  // determine whether the user has logged in
  // const hasToken = getToken()
  // if (hasToken) {
  //   if (to.path === '/login') {
  //     // if is logged in, redirect to the home page
  //     next({ path: '/' })
  //     NProgress.done()
  //   } else {
  //     // try {
  //     // 拉取info
  //     // const info = await store.dispatch('user/getInfo')
  //
  //     // 生成可访问的路由表
  //     if (getRole() === '1') {
  //       await store.dispatch('permission/generateRoutes', ['1'])
  //     } else if (getRole() === '0') {
  //       await store.dispatch('permission/generateRoutes', ['0'])
  //     }
  //     // 动态添加可访问路由表
  //     router.addRoutes(store.getters.addRouters)
  //
  //     // const s = store.getters.addRouters
  //     // router.addRoutes(s)
  //     // if (s) {
  //     // }
  //     // hack方法 确保addRoutes已完成
  //     // next({ ...to, replace: true })
  //     // const accessRoutes = await store.dispatch("permission/generateRoutes", info);
  //     // dynamically add accessible routes
  //     // router.addRoutes(accessRoutes);
  //     // hack method to ensure that addRoutes is complete
  //     // set the replace: true, so the navigation will not leave a history record
  //     // next({ ...to, replace: true })
  //     // next()
  //     // } catch (error) {
  //     //   // remove token and go to login page to re-login
  //     //   await store.dispatch('user/resetToken')
  //     //   Message.error(error || 'Has Error')
  //     //   next(`/login`)
  //     //   NProgress.done()
  //     // }
  //     next()
  //   }
  // } else {
  //   /* has no token*/
  //
  //   if (whiteList.indexOf(to.path) !== -1) {
  //     // in the free login whitelist, go directly
  //     next()
  //   } else {
  //     // other pages that do not have permission to access are redirected to the login page.
  //     next(`/login?redirect=${to.path}`)
  //     NProgress.done()
  //   }
  // }
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
