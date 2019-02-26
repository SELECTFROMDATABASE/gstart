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
      <Layout :style="{padding: '0 20px'}">
        <Content :style="{padding: '24px 0', minHeight: '500px', background: '#fff'}">
          <Layout>
            <Sider hide-trigger :style="{background: '#fff'}">
              <g-nav :items="meunItems" :menu-select="menuSelect"></g-nav>
            </Sider>
            <Content :style="{paddingLeft:'20px',minHeight: '280px', background: '#fff'}">
              <g-breadcrumbnav :current-path="currentPath"/>
              <router-view></router-view>
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
  import GBreadcrumbnav from "../components/g-breadcrumbnav";
  import host from '../api/host';
  import axios from 'axios';

  function _forEach(item,fn) {
    if(item.childrenItems && item.childrenItems.length != 0){
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


  export default {
    components: {
      GBreadcrumbnav,
      GTabs,
      GNav},
    methods : {
      initMenu () {
        var that = this;
        this.$ajax({
          method: 'get',
          params:{auth:window.localStorage.getItem("authCode")},
          url: host.manage() + '/manage/menu',
        }).then(function(response) {
            //响应成功回调
            that.$data.meunItems = response.data;
            //菜单选中事件
            that.$data.menuSelect = function(name){
              //刷新页面
              that.$router.push("/home/"+name)
              //刷新面包屑
            }
            //当前路由改变
            that.$router.afterEach((to, from, next) => {
              that.$data.currentPath = that.$router.currentRoute.matched
            });
            _forEach(that.$data.meunItems,function (item) {
              if (!that.$router.options.routes[0].children){
                that.$router.options.routes[0].children = [];
              }
              //动态添加路由
              that.$router.options.routes[0].children.push({
                //插入路由
                name:item.menuName,
                path: item.menuNo,
                //将组件用require引进来
                component: resolve => require(['./'+item.url+'.vue'], resolve),
                props : {
                  search : {}
                }
              })
            });
            that.$router.addRoutes(that.$router.options.routes);

          });
      }
    },
    mounted(){
      this.initMenu();
    },
    data () {
      return {
        currentPath : [],
        isCollapsed: true,
        navItems:[
          {navName:'user',icon:'ios-navigate',text:"帐号"}
        ],
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
