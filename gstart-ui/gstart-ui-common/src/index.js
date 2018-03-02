import Table from './components/table/table'
import Radio from './components/radio/radio'
import RadioGroup from './components/radio/radio-group'
import CheckBox from './components/check-box/check-box'
import Vue from 'vue'
Vue.component("gs-table",Table);
Vue.component("gs-radio",Radio);
Vue.component("gs-radio-group",RadioGroup);
Vue.component("gs-check-box",CheckBox);
const API = {
  Table,
  Radio,
  RadioGroup,
  CheckBox
}
export default API;
