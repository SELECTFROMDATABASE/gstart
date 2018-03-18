<template>
<div>
  <gs-switch :label="label" />
</div>
</template>
<script>
  import gsSwitch from "../../src/components/switch/switch";

  export default {
    components: {gsSwitch},
    data(){return {
      label:"打开"
    }}
  }
</script>
