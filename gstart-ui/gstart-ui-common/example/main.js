import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
import Page01 from './components/test1'
import Page02 from './components/test2'
import Page03 from './components/test3'
import Page04 from './components/test4'
import Page05 from './components/test5'
import Page06 from './components/test6'
import Page07 from './components/test7'
import gstart from '../src/index'
import "../src/assets/css/index.css"
Vue.use(VueRouter)
Vue.use(gstart)
//定义路径
const routes = [
  { path: '/', component: Page01 },
  { path: '/02', component: Page02 },
  { path: '/03', component: Page03 },
  { path: '/04', component: Page04 },
  { path: '/05', component: Page05 },
  { path: '/06', component: Page06 },
  { path: '/07', component: Page07 }
]
//创建路由对象
const router = new VueRouter({
  routes
})

new Vue({
  el: '#app',
  template: '<App/>',
  components: { App },
  router
})
