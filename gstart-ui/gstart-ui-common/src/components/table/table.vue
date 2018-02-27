<template>
  <div class="gs-table">
    <div class="gs-table-head">
      <table-head :table-heads="tableHeads"></table-head>
    </div>
    <div class="gs-table-tbody">
      <table-body :data-list="dataList"></table-body>
    </div>
  </div>
</template>

<script>
  import TableHead from './table-head';
  import TableBody from "./table-body";
    export default {
      props: {tableHeads:Array,dataList:Array},
      components: {
        TableBody,
        TableHead},
      name: "Test"
    }
</script>

<style >
</style>
