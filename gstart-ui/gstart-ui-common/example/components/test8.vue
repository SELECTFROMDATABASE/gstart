<template>
<div>
  <gs-button></gs-button>
</div>
</template>
<script>
  import gsButton from "../../src/components/button/button";

  export default {
    components: {gsButton},
    data(){return {
      label:"打开"
    }}
  }
</script>
