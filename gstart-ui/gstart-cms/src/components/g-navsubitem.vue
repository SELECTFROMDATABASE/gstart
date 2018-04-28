<template>
  <div>
  <MenuItem v-if="!(child.childrenItems) || child.childrenItems.length == 0" v-for="child in items" :name="child.menuNo">
      <Icon :type="child.iconClass"></Icon>
      {{child.menuName}}
  </MenuItem>
  <Submenu v-if="child.childrenItems && child.childrenItems.length > 0" v-for="child in items" :name="child.menuNo">
    <template slot="title">{{child.menuName}}</template>
    <g-navsubitem :items="child.childrenItems"/>
  </Submenu>
  </div>
</template>

<script>
    export default {
        name: "g-navsubitem",
        props:['items','show-checkbox']
    }
</script>

<style>

</style>
