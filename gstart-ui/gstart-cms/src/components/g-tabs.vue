<template>
    <div>
      <Tabs type="card" closable @on-tab-remove="handleTabRemove">
        <TabPane :data-tabid="i.id" :label="i.label" v-for="i in tabs">{{i.content}}</TabPane>
        <Button type="ghost" size="small" slot="extra">增加</Button>
      </Tabs>
    </div>
</template>

<script>
    export default {

      name: "g-tabs",
      props : ['tabs'],
      methods: {
        handleTabRemove (name) {
          this['tab' + name] = false;
        },
        handleTabsAdd () {
          this.tabs ++;
        }
      }
    }
</script>

<style scoped>

</style>
