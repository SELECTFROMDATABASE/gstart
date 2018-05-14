import Vue from 'vue'
import Router from 'vue-router'
import index from '@/pages/index'
import login from '@/pages/login'
Vue.use(Router)

// noinspection JSAnnotator
export default new Router({
  mode: 'history',
  routes: [

    {
      path:'/',
      redirect: 'login'

    },
    {
      path:'/login',
      name: '登录',
      component: login

    },
    {
      path: '/home',
      name: '首页',
      component: index
    }

  ]
})
