import Table from './components/table/table'
import Radio from './components/radio/radio'
import RadioGroup from './components/radio/radio-group'
import CheckBox from './components/check-box/check-box'
import Switch from './components/switch/switch'
import Select from './components/select/select'
import Button from './components/button/button'

import Vue from 'vue'
Vue.component("gs-table",Table);
Vue.component("gs-radio",Radio);
Vue.component("gs-radio-group",RadioGroup);
Vue.component("gs-check-box",CheckBox);
Vue.component("gs-switch",Switch);
Vue.component("gs-select",Select);
Vue.component("gs-button",Button);
const API = {
  Table,
  Radio,
  RadioGroup,
  CheckBox,
  Switch,
  Select,
  Button
}
export default API;
