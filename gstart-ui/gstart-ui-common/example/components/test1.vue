<template>
  <div>
    <Table :table-heads="colums" :data-list="data"></Table>
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
