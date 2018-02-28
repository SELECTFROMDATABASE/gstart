<template>
    <label>
      <span class="gs-radio">
        <span class="gs-radio-inner"></span>
        <input class="gs-radio-input" type="radio"/>
      </span>
      {{label}}
    </label>
</template>

<script>
    export default {
      props:{label:String},
        name: "gs-radio"
    }
</script>

<style>

</style>
