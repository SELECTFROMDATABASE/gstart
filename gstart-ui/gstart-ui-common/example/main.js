import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
import Page01 from './components/test1'
import Page02 from './components/test2'
import Page03 from './components/test3'
import gstart from '../src/index'
import "../src/assets/css/index.css"
Vue.use(VueRouter)
Vue.use(gstart)
//定义路径
const routes = [
  { path: '/', component: Page01 },
  { path: '/02', component: Page02 },
  { path: '/03', component: Page03 }
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
