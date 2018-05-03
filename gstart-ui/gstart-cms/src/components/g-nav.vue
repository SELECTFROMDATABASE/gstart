<template>
  <div style="background: #ccc">
    <Menu :mode="mode" :theme="theme" @on-select="menuSelected" width="auto" v-for="item in items" >
      <Submenu :name="item.menuNo">
        <template slot="title">
          <Icon :type="item.iconClass" v-if="item.iconClass != null"></Icon>
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
      props:{
        items : Array,
        menuSelect : Function,
        mode : {
          type : String,
          default : "vertical"
        },
        theme : {
          type : String,
          default : "light"
        }
      },
      created(){

      },
      methods:{
        menuSelected :function(name){
          try {
            if (this.$props.menuSelect != undefined && (typeof this.$props.menuSelect) == "function"){
              this.$props.menuSelect(name);
            }
          }catch (e) {
          }
        }
      }
    }
</script>

<style>

</style>
