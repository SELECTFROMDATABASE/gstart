import Table from './components/table/table'
import Radio from './components/radio/radio'

import Vue from 'vue'
Vue.component("gs-table",Table);
Vue.component("gs-radio",Radio);
const API = {
  Table,
  Radio
}
export default API;
