<template>
    <div>
      <Button>
        <Icon type="android-add"></Icon>
        新增
      </Button>
      <Button>
        <Icon type="android-remove"></Icon>
        删除
      </Button>
    </div>
</template>

<script>
    export default {
      name: "g-bar",
      props : {
        editUrl : String
      }
    }
</script>

<style scoped>

</style>
