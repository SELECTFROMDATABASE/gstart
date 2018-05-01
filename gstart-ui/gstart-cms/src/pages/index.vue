<template>
  <div class="layout">
    <Layout>
      <Header>
        <Menu mode="horizontal" theme="dark">
          <div class="layout-logo">
          </div>
          <div class="layout-nav" v-for="item in navItems">
            <MenuItem :name="item.navName">
              <Icon :type="item.icon"></Icon>
              {{item.text}}
            </MenuItem>
          </div>
        </Menu>
      </Header>
      <Layout :style="{padding: '0 50px'}">
        <Content :style="{padding: '24px 0', minHeight: '280px', background: '#fff'}">
          <Layout>
            <Sider hide-trigger :style="{background: '#fff'}">
              <g-nav :items="meunItems" :menu-select="menuSelect"></g-nav>
            </Sider>
            <Content :style="{paddingLeft:'20px',minHeight: '280px', background: '#fff'}">
              <router-view />
            </Content>
          </Layout>
        </Content>
      </Layout>
      <Footer class="layout-footer-center">2018 &copy; Gstart</Footer>
    </Layout>
  </div>
</template>

<script>
  import GNav from "../components/g-nav";
  import GTabs from "../components/g-tabs";
  var userStatus = {
    0: {
      value: 0,
      name: '全部'
    },
    1: {
      value: 1,
      name: '已锁定',
      color: 'red'
    },
    2: {
      value: 2,
      name: '正常',
      color: 'green'
    },
  };
  function _forEach(item,fn) {
    if(item.childrenItems){
      _forEach(item.childrenItems,fn);
    }else{
      if(item.url){
        fn(item);
      }else if (item instanceof Array) {
        for (var i in item){
          _forEach(item[i],fn)
        }
      }else {
      }

    }
  }

  function formatStatus(value, status) {
    return status[value] || {value: '', name: ''};
  }
  export default {
    components: {
      GTabs,
      GNav},
    beforeCreate(){
      var that = this;
      this.$http.get('http://localhost:9090/test/a').then((response) => {
          // 响应成功回调
        this.$data.meunItems = response.body;
        this.$data.menuSelect = function(name){
          //刷新页面
          that.$router.push(name)
        }
        _forEach(response.body,function (item) {
          if (!that.$router.options.routes[0].children){
            that.$router.options.routes[0].children = [];
          }
          //动态添加路由
          that.$router.options.routes[0].children.push({
            //插入路由
            name:item.menuNo,
            path: item.menuNo,
            //将组件用require引进来
            component: resolve => require(['./'+item.url+'.vue'], resolve),
            props : {
              columns : [
                {
                  title: '用户名',
                  key: 'username',
                  filter: {
                    type: 'Input'
                  }
                },
                {
                  title: '手机号',
                  key: 'phone',
                  filter: {
                    type: 'Input'
                  }
                },
                {
                  title: '邮箱',
                  key: 'email',
                  filter: {
                    type: 'Input'
                  }
                },
                {
                  title: '账户余额 (元)',
                  key: 'money',
                  filter: {
                    type: 'Input'
                  },
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
                    }, formatStatus(params.row.status, userStatus).name)
                  }
                }
              ],
              data : [
                {
                  username: '小明',
                  phone: '17760172601',
                  email: '1023007219@qq.com',
                  money: '50',
                  status: '1',
                }, {
                  username: '小兰',
                  phone: '17760172605',
                  email: '1023007219@qq.com',
                  money: '50',
                  status: '2',
                }, {
                  username: '小东',
                  phone: '17761232601',
                  email: '1023007219@qq.com',
                  money: '20',
                  status: '2',
                }, {
                  username: '咱三',
                  phone: '17722226011',
                  email: '1023007219@qq.com',
                  money: '20',
                  status: '1',
                }, {
                  username: '小明',
                  phone: '17760202601',
                  email: '1023007219@qq.com',
                  money: '20',
                  status: '1',
                }, {
                  username: '尼斯',
                  phone: '17760172601',
                  email: '1023007219@qq.com',
                  money: '50',
                  status: '1',
                }, {
                  username: '导航',
                  phone: '17760172601',
                  email: '1023007219@qq.com',
                  money: '20',
                  status: '1',
                }, {
                  username: '但是还是',
                  phone: '17760172601',
                  email: '1023007219@qq.com',
                  money: '50',
                  status: '1',
                },
              ]
            }
          })
        });
        that.$router.addRoutes(that.$router.options.routes);
        }, (response) => {
          // 响应错误回调
        console.log(response)
        });
    },
    data () {
      return {
        isCollapsed: true,
        navItems:[
          {navName:'1',icon:'ios-navigate',text:"test1"},
          {navName:'2',icon:'ios-keypad',text:"test2"},
          {navName:'3',icon:'ios-analytics',text:"test3"},
          {navName:'4',text:"test4"}
        ],
        test : [{label:'1',content:'<Tree :data="data4" show-checkbox multiple></Tree>',id:1},{label:'2',content:'2',id:2},{label:'3',content:'2',id:3}],
        meunItems : [],
        columns : [],
        menuSelect : function (name) {

        }/*,
        meunItems : ""*//*[
          {
            menuName : '系统设置',
            menuNo : '01',
            parentId : 0,
            childrenItems : [
              {menuName : '用户管理',
                menuNo : '0101',
                parentId : 1,
                childrenItems:[
                  {menuName : '用户管理1',
                    menuNo : '010101',
                    parentId : 1,
                    url : 'fsr/sys/user/user.jsp'
                  },
                  {menuName : '用户管理2',
                    menuNo : '010102',
                    parentId : 1
                  }
                ]
          },
              {menuName : '部门定义',
                menuNo : '0102',
                parentId : 1},
              {menuName : '岗位定义',
                menuNo : '0103',
                parentId : 1},
              {menuName : '角色定义',
                menuNo : '0104',
                parentId : 1},
            ]
          },
          {
            menuName : '系统设置2',
            menuNo : '02',
            parentId : 0,
          }
        ]*/,
        data4: [
          {
            title : '系统设置',
            menuNo : '01',
            parentId : 0,
            children : [
              {title : '用户管理',
                menuNo : '0101',
                parentId : 1,
                children:[
                  {title : '用户管理1',
                    menuNo : '010101',
                    parentId : 1,
                    url : 'fsr/sys/user/user.jsp'
                  },
                  {title : '用户管理2',
                    menuNo : '010102',
                    parentId : 1
                  }
                ]
              },
              {title : '部门定义',
                menuNo : '0102',
                parentId : 1},
              {title : '岗位定义',
                menuNo : '0103',
                parentId : 1},
              {title : '角色定义',
                menuNo : '0104',
                parentId : 1},
            ]
          },
          {
            title : '系统设置2',
            menuNo : '02',
            parentId : 0,
          }
        ]
      };
    },
    computed: {
      menuitemClasses: function () {
        return [
          'menu-item',
          this.isCollapsed ? 'collapsed-menu' : ''
        ]
      }
    },
    name : 'index'
  }
</script>

<style >
  .layout{
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
  }

  .layout-logo{
    color: aliceblue;
    background: #5b6270;
    border-radius: 3px;
    float: left;
    position: relative;
    left: 20px;
  }
  .layout-nav{
    width: 420px;
    margin: 0 auto;
    margin-right: 20px;
  }
  .layout-footer-center{
    text-align: center;
  }
</style>
