<template>
    <div>
      <Table border ref="selectbar" :data="filters" :columns="tableColumnsFilters" stripe>
      </Table>

      <Table @on-selection-change="dataOnChecked" ref="selection" :show-header="false" border :data="data" :columns="columns" stripe>
      </Table>
    </div>
</template>

<script>
    export default {
    name: "g-table",
    props: [
      'columns',//列描述数据对象
      'data',   //表格数据
      'search'  //过滤条件保存的对象,就是保存Input框和Select中值
    ],
      data() {
      return {
        filters: [{
          title: ''
        }],
        tableColumnsFilters: [],
        checkedAll : false
      }
    },
      created() {
      for (let index in this.columns) {
        let filter = {};
        /**
         * 因为是采用的两个表的形式,过滤表中显示查询的Input,Select条件输组件,表头显示的是数据表的表头,渲染的数据是传入的columns中的filter字段
         * 数据表隐藏表头,只显示数据,渲染的数据是传入的columns中的key值
         */
        //将传入的列描述数据对象(columns) 的title和width 复制给 过滤表的列描述数据对象(tableColumnsFilters)
        this.$set(filter, 'title', this.columns[index].title);
        if (this.columns[index].width) {
          this.$set(filter, 'width', this.columns[index].width);
        }
        let render = (h) => {
        };
        /**
         * 需要有搜索的列, 只要在 列描述数据对象columns中配置 filter属性就行如下:
         {
            title: '名字',
            key: '_id',
            filter: {
              type: 'Input'
            }
          },
         {
           title: '交易金额 (元)',
           key: 'money',
           filter: {
             type: 'Select',
             option:status //选项
           }
         },
         */
        //如果存在 过滤选项
        if (this.columns[index].filter) {
          //过滤为 下拉选择框
          if (this.columns[index].filter.type && this.columns[index].filter.type === 'Select') {
            render = (h) => {
              /*var defaultProps = this.columns[index].filter.props == undefined ? {value : 0} :
                this.columns[index].filter.props;*/
              return h(this.columns[index].filter.type, {
                props: {
                  value : 0
                },
                on: {
                  'on-change': (val) => {
                    if (val === 0) {
                      //当选项是全部的时候删除 search中该字段的过滤条件
                      this.$delete(this.search, this.columns[index].key);
                      this.load(this.search);
                      return;
                    }
                    //添加字段过滤条件
                    this.$set(this.search, this.columns[index].key, val);
                    this.load(this.search);
                  }
                }
              }, this.createOptionsRender(index, h));
            }
          } else if(this.columns[index].filter.type && this.columns[index].filter.type === 'Checkbox'){
            render = (h) => {
              return h(this.columns[index].filter.type, {
                props: {
                  value: 0,//Select选项列表一般 0 为全部
                },
                on: {
                  'on-change': (val) => {
                      this.$refs.selection.selectAll(val)
                  }
                }
              }, this.createOptionsRender(index, h));
            }
          } else{
            //如果是输入框
            render = (h) => {
              //获取输入框的值,为了减少数据库的压力,我这里是
              //通过回车和点击搜索按钮才进行数据过滤查询,如果
              //要输入框变化就进行过滤,把 this.load()放到
              //input事件方法就行了
              let inputValue = {};
              return h(this.columns[index].filter.type, {
                props: {
                  placeholder: '输入' + this.columns[index].title,
                  icon: 'ios-search-strong'
                },
                on: {
                  input: val => {
                    inputValue = val;
                    if (!inputValue) {
                      this.validInputValue(index, inputValue);
                    }
                  },
                  //input框后面的搜索按钮的点击事件
                  'on-click': () => {
                    this.validInputValue(index, inputValue);
                  },
                  'on-enter': () => {
                    this.validInputValue(index, inputValue);
                  }
                }
              })
            };
          }
        }
        this.$set(filter, 'render', render);
        this.tableColumnsFilters.push(filter);
      }
    },
      methods: {
        createOptionsRender(index, h) {
          //选项渲染
          let optionRender = [];
          if (this.columns[index].filter.option) {
            let option = this.columns[index].filter.option;
            for (let i in option) {
              optionRender.push(h('Option', {
                props: {
                  value: option[i].value,
                  key : option[i].value
                }
              }, option[i].label))
            }
          }
          return optionRender;
        },
        //重新加载数据
        load(search) {
          console.log(search)
          //会执行一个load的事件
          this.$emit('load');
        },
        //验证输入框的值
        validInputValue(index, inputValue) {
          if (!inputValue) {
            this.$delete(this.search, this.columns[index].key);
            this.load(this.search);
            return;
          }
          this.$set(this.search, this.columns[index].key, inputValue);
          this.load(this.search);
        },
        dataOnChecked(selection){
          //长度是否与数据长度一致
          if (this.$refs.selection.getSelection().length == this.$refs.selection.data.length){

          }else{

          }
          console.log(this.$refs.selectbar)
          this.$refs.selectbar.selectAll(false)
        }
      }
    }
</script>

<style scoped>

</style>
