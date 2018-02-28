<template>
  <div>
    <gs-table :table-heads="colums" :data-list="data"></gs-table>
  </div>
</template>
<script>
  export default {
    data(){return {
      colums:[{value: "test1"},{value: "test1"},{value: "test1"}],
      data : [{1:"test1",2:"test2",3:"test3"},{1:1,2:2,3:3},{1:1,2:2,3:3},{1:1,2:2,3:3}]
    }}
  }
</script>
