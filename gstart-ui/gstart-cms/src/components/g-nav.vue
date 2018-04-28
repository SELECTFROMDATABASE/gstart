<template>
  <div>
    <Menu @on-select="menuSelected" theme="light" width="auto" v-for="item in items" >
      <Submenu :name="item.menuNo">
        <template slot="title">
          <Icon type="ios-navigate"></Icon>
          {{item.menuName}}
        </template>
        <g-navsubitem :items="item.childrenItems"></g-navsubitem>
      </Submenu>
    </Menu>
  </div>
</template>

<script>
    import GNavsubitem from "./g-navsubitem";
    export default {
        name: "g-nav",
      components: {GNavsubitem},
      props:["items"],
      methods:{
        menuSelected :function(name){
          console.log(name)
        }
      }
    }
</script>

<style>

</style>
