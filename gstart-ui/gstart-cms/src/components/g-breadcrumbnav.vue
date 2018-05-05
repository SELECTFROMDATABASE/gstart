<template>
  <Breadcrumb>
    <BreadcrumbItem
      v-for="item in currentPath"
      :href="item.path"
      :key="item.name"
    >{{ item.name }}</BreadcrumbItem>
  </Breadcrumb>
</template>

<script>
    export default {
      name: "g-breadcrumbnav",
      props: {
        currentPath: Array
      },
      methods: {
        itemTitle (item) {
          console.log(item)
            return item.name;
        }
      }
    }
</script>

<style scoped>

</style>
