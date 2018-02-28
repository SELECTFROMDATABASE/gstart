<template>
    <table>
      <colgroup></colgroup>
      <thead >
        <th v-for="head in tableHeads">
         <table-cell :cell-data="head.value"></table-cell>
        </th>
      </thead>
    </table>
</template>

<script>
  import TableCell from './table-cell'
    export default {
      props: {tableHeads:Array},
      components: {TableCell},
      name: "gs-table-head"
    }
</script>

<style>

</style>
