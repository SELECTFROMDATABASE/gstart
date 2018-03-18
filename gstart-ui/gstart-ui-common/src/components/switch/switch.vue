<template>
  <span class="gs-switch gs-switch-disabled">
    <input type="hidden"/>
    <span class="gs-switch-inner">
      <span v-if="label != null">{{label}}</span>
    </span>
  </span>
</template>

<script>
    export default {
        props: {
          label:String
        },
        name: "gs-switch"
    }
</script>

<style>

</style>
