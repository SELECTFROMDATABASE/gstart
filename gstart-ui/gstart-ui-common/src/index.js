import Table from './components/table/table'
import Radio from './components/radio/radio'
import RadioGroup from './components/radio/radio-group'
import CheckBox from './components/check-box/check-box'
import Switch from './components/switch/switch'
import Select from './components/select/select'

import Vue from 'vue'
Vue.component("gs-table",Table);
Vue.component("gs-radio",Radio);
Vue.component("gs-radio-group",RadioGroup);
Vue.component("gs-check-box",CheckBox);
Vue.component("gs-switch",Switch);
Vue.component("gs-select",Select);
const API = {
  Table,
  Radio,
  RadioGroup,
  CheckBox,
  Switch,
  Select
}
export default API;
