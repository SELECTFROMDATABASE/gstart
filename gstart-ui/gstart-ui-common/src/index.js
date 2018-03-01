import Table from './components/table/table'
import Radio from './components/radio/radio'
import RadioGroup from './components/radio/radio-group'

import Vue from 'vue'
Vue.component("gs-table",Table);
Vue.component("gs-radio",Radio);
Vue.component("gs-radio-group",RadioGroup);
const API = {
  Table,
  Radio,
  RadioGroup
}
export default API;
