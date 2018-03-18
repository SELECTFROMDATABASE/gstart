<template>
<div>
    <gs-select style="width: 200px"></gs-select>
</div>
</template>
<script>
  import gsSelect from "../../src/components/select/select";

  export default {
    components: {gsSelect},
    data(){return {
      label:"打开"
    }}
  }
</script>
