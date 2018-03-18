<template>
  <div class="gs-select gs-select-single">
    <div class="gs-select-selection">
      <input type="hidden"/>
      <span class="gs-select-palceholder" >请输入</span>
      <span class="gs-select-selected-value" style="display: none">test</span>
      <i class="gs-jiantouxia gs-select-arrow gs-icon"></i>
    </div>
    <gs-drop></gs-drop>
  </div>

</template>

<script>
  import GsDrop from './drop'
    export default {
      components: {GsDrop},
      props: {
          label:String
        },
        name: "gs-select"
    }
</script>

<style>

</style>
