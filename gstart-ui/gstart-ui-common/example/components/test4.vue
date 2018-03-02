<template>
<div>
  <gs-check-box :label="label"></gs-check-box>
</div>
</template>
<script>
  import CheckBox from "../../src/components/check-box/check-box";

  export default {
    components: {CheckBox},
    data(){return {
      label:"test"
    }}
  }
</script>
