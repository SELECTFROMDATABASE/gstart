<template>
  <div>
    <gs-radio :label="label"></gs-radio>
  </div>
</template>
<script>
  import Radio from "../../src/components/radio/radio";

  export default {
    components: {Radio},
    data(){return {
      label:"test"
    }}
  }
</script>
