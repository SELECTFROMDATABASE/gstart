<template>


  <div class="layout" style="height: 100%;">
    <Layout style="height: 100%;">
      <Header style="height: 10%;">
        <div>
          <h1>XXXXXXXXXXXXXXXXXXXXXXXXXXX</h1>
        </div>
      </Header>
      <Content style="height: 80%">
        <div class="login" @keydown.enter="handleSubmit">
          <div class="login-con">
            <Card :bordered="false">
              <p slot="title">
                <Icon type="log-in"></Icon>
                欢迎登录
              </p>
              <div class="form-con">
                <Form ref="loginForm" :model="form" :rules="rules">
                  <FormItem prop="userName">
                    <Input v-model="form.userName" placeholder="请输入会员帐号">
                    <span slot="prepend">
                      <Icon :size="16" type="person"></Icon>
                                </span>
                    </Input>
                  </FormItem>
                  <FormItem prop="password">
                    <Input type="password" v-model="form.password" placeholder="请输入密码">
                    <span slot="prepend">
                    <Icon :size="14" type="locked"></Icon>
                </span>
                    </Input>
                  </FormItem>
                  <FormItem>
                    <Button @click="handleSubmit" type="primary" long>登录</Button>
                  </FormItem>
                </Form>
              </div>
            </Card>
          </div>
        </div>
      </Content>
      <Footer style="height: 10%" class="layout-footer-center">
        <p>联系方式：13502401393 QQ：2849485657  地址：广州市荔湾区光复北路558号椰城宾馆</p>
        <p>版权所有：广东烹饪协会烧腊专业委员会    备案序号： 粤ICP备17008925号-1</p></Footer>
    </Layout>
  </div>

</template>

<script>
  import Cookies from 'js-cookie';
  import host from '../api/host';
  export default {
    name: 'login',
    data () {
      return {
        form: {
          userName: '',
          password: ''
        },
        rules: {
          userName: [
            { required: true, message: '账号不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      handleSubmit () {
        const loading = this.$Message.loading({
          content: '登录中...',
          duration: 0
        });
        const removeLoading = function () {
          setTimeout(loading,1)
        }
        this.$refs.loginForm.validate((valid) => {
          if (valid) {
           const  that = this;
           this.$ajax({
             method: 'post',
             url: host.login()+"/sso/login",
             data: {
               account:this.$refs.loginForm.model.userName,
               password:this.$refs.loginForm.model.password}
           })
            .then(function (res) {
              removeLoading();
              if (res.data.success){
                that.$Message.success({content:res.data.message,duration:2,onClose:function () {
                  window.localStorage.setItem("authCode",JSON.parse(res.data.data).authCode)
                  that.$router.push("/home")
                  }});
              }else {
                that.$Message.error(res.data.message);
              }
            })
              .catch(function (error) {
                console.log(document.cookie)
                that.$Message.error(error);
              });

          }
        });
      }
    }
  };
</script>

<style>
  .login {
    width: 100%;
    height: 100%;
    background-image: url('http://123.207.99.81/images/zzpic2648.jpg');
    background-size: cover;
    background-position: center;
    position: relative;
  }
  .login-con {
    position: absolute;
    right: 160px;
    top: 50%;
    transform: translatey(-60%);
    width: 300px;
  }
  .login-con-header {
    font-size: 16px;
    font-weight: 300;
    text-align: center;
    padding: 30px 0;
  }
  .login-con .form-con {
    padding: 10px 0 0;
  }
  .login-con .login-tip {
    font-size: 10px;
    text-align: center;
    color: #c3c3c3;
  }
  .layout{
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
  }
  .layout-logo{
    width: 110px;
    height: 101px;
    background: white;
    border-radius: 3px;
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
  }
  .layout-nav{
    width: 420px;
    margin: 0 auto;
    margin-right: 20px;
  }
  .layout-footer-center{
    text-align: center;
    padding: 8px 50px;
  }
  .ivu-layout-header{
    background: white;
  }
</style>
