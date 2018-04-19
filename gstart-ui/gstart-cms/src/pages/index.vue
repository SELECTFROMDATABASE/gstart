<template>
  <div class="layout">
    <Layout>
      <Header>
        <Menu mode="horizontal" theme="dark">
          <div class="layout-logo">
          </div>
          <div class="layout-nav" v-for="item in navItems">
            <MenuItem :name="item.navName" >
              <Icon :type="item.icon"></Icon>
              {{item.text}}
            </MenuItem>

          </div>
        </Menu>
      </Header>
      <Layout :style="{padding: '0 50px'}">
        <Breadcrumb :style="{margin: '16px 0'}">
          <BreadcrumbItem>Home</BreadcrumbItem>
          <BreadcrumbItem>Components</BreadcrumbItem>
          <BreadcrumbItem>Layout</BreadcrumbItem>
        </Breadcrumb>
        <Content :style="{padding: '24px 0', minHeight: '280px', background: '#fff'}">
          <Layout>
            <Sider hide-trigger :style="{background: '#fff'}">
              <g-nav :items="meunItems"></g-nav>
            </Sider>
            <Content :style="{padding: '24px', minHeight: '280px', background: '#fff'}">
              <Tree :data="data4" show-checkbox multiple></Tree>
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
  export default {
    components: {GNav},

    data () {
      return {
        isCollapsed: true,
        navItems:[
          {navName:'1',icon:'ios-navigate',text:"test1"},
          {navName:'2',icon:'ios-keypad',text:"test2"},
          {navName:'3',icon:'ios-analytics',text:"test3"},
          {navName:'4',text:"test4"}
        ],
        meunItems : [
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
        ],
        data4: [
          {
            title: 'parent 1',
            expand: true,
            selected: true,
            children: [
              {
                title: 'parent 1-1',
                expand: true,
                children: [
                  {
                    title: 'leaf 1-1-1',
                    disabled: true
                  },
                  {
                    title: 'leaf 1-1-2'
                  }
                ]
              },
              {
                title: 'parent 1-2',
                expand: true,
                children: [
                  {
                    title: 'leaf 1-2-1',
                    checked: true
                  },
                  {
                    title: 'leaf 1-2-1'
                  }
                ]
              }
            ]
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
