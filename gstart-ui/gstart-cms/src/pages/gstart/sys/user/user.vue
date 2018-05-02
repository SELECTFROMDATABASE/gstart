<template>
    <div>
      <g-table :columns="columns" :data="userData" :search="search" @load="loadData"/>
    </div>
</template>

<script>

    import GTable from "../../../../components/g-table";
    function formatStatus(value, status) {
      return status[value] || {value: '', name: ''};
    }

    var userStatus = {
      0: {
        value: 0,
        label: '全部'
      },
      1: {
        value: 1,
        label: '管理员',
        color: 'red'
      },
      2: {
        value: 2,
        label: '会员',
        color: 'green'
      },
    };

    export default {
      components: {GTable},
      name: "user",
      props: [
        //表格数据
        'search'//过滤条件保存的对象,就是保存Input框和Select中值
      ]
      ,data(){
        return {
          columns : [
            /*{
              filter: {
                type: 'Checkbox'
              },
              type: 'selection',
              width: 60,
              align: 'center'
            },*/
            {
              title: '帐号',
              key: 'account',
              filter: {
                type: 'Input'
              }
            },
            {
              title: 'ID',
              key: 'id',
              filter: {
                type: 'Input'
              }
            },
            {
              title: '姓名',
              key: 'name',
              filter: {
                type: 'Input'
              }
            },
            {
              title: '状态',
              key: 'status',
              render: (h, params) => {
                return h('Tag', {
                  slot: 'context',
                  props: {
                    color: formatStatus(params.row.status, userStatus).color
                  }
                }, formatStatus(params.row.status, userStatus).label)
              },
              filter: {
                type: 'Select',
                option : userStatus,
                props : {
                  value : 1
                }
              }
            }
          ],
          userData :[]
        }
      }
      ,
      mounted() {
          this.$http.get('http://localhost:8899/test/getUser').then((response) => {
             this.$data.userData = response.body;
          });
      },
      methods : {
        loadData() {
          //这个search应该是传到后台,然后台来根据条件查询数据库
          this.$Message.info('查询条件'+JSON.stringify(this.search));
        }
      }
    }
</script>

<style>

</style>
