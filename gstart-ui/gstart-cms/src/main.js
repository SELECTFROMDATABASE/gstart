// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import iView from 'iview'
import 'iview/dist/styles/iview.css'
import axios from 'axios'
Vue.config.productionTip = false
Vue.use(iView)
axios.defaults.withCredentials=true;
axios.defaults.headers['Access-Control-Allow-Origin'] = 'http://localhost:8080'
axios.defaults.headers['Access-Control-Allow-Methods'] = 'GET, POST, OPTIONS, PUT, PATCH, DELETE'
axios.defaults.headers['Access-Control-Allow-Headers'] = 'X-Requested-With,content-type'
axios.defaults.headers['Access-Control-Allow-Credentials'] = 'true'
Vue.prototype.$ajax = axios
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
