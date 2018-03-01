<template>
    <label>
      <span >
        <span ></span>
        <input type="radio"/>
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
