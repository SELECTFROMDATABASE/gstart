<template>
  <label >
    <span class="gs-checkbox gs-checkbox-indeterminate">
      <span class="gs-checkbox-inner "></span>
      <input type="checkbox" class="gs-checkbox-input"/>
    </span>
    {{label}}
  </label>
</template>

<script>
    export default {
      props:{
        label:String
      },
        name: "check-box"
    }
</script>

<style>

</style>
