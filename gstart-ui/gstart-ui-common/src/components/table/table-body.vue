<template>
  <table>
    <tbody>
      <template v-for="(list) in dataList">
        <tr class="gs-table-tr">
          <template v-for="(item,index) in list">
            <td><table-cell :cell-data="item"></table-cell></td>
          </template>
        </tr>
      </template>
    </tbody>
  </table>
</template>

<script>
    import TableTr from "./table-tr";
    import TableCell from "./table-cell";

    export default {
      props:{dataList:Array},
      components: {
        TableCell,
        TableTr},
      name: "gs-table-body"
    }
</script>

<style>

</style>
