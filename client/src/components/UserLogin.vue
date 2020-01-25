<template>
 <v-app id="landing-page">

    <v-content >
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="4">
            <v-card class="elevation-12">
              <v-toolbar color="#ff0d5d" dark flat>
                <i class="material-icons">account_circle</i>
                &nbsp;
                  
                <v-toolbar-title>เข้าสู่ระบบ</v-toolbar-title>
                
                <div class="flex-grow-1"></div>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field
                    v-model="username"
                    label="Username"
                    name="login"
                    type="text"
                  ></v-text-field>

                  <v-text-field
                    v-model="password"
                    id="password"
                    label="Password"
                    name="password"
                    type="password"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <div class="flex-grow-1"></div>
                <v-btn color="primary" @click="Login">เข้าสู่ระบบ</v-btn>
                <v-btn color="success" router-link to="/register">
                <v-icon>mdi-account-plus</v-icon>
                  สมัครสมาชิก
                  
                </v-btn>
                <v-btn color="warning" router-link to="/employeelogin">
                  <v-icon>mdi-hard-hat</v-icon>พนักงาน?
                </v-btn>
              </v-card-actions>
            </v-card>
            <div v-if = "loginCheck == false">
                <v-alert type="error">Username or password is in correct!</v-alert>
             </div>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import http from "../http-common";
export default {
  name: "App",
  props: {
    source: String
  },
  data: () => ({
    drawer: null,
    username: null,
    password: null,
    loginCheck: true,
    auth: {}
  }),
  methods: {
     /* eslint-disable no-console */

     callUsername(){
      return this.username;
    },

    Login() {
      if (!this.username || !this.password) {
        //alert("Please enter username and password.");
      } else {
        http
          .get("/user/" + this.username + "/" + this.password)
          .then(res => {
            console.log(JSON.parse(JSON.stringify(res.data)));
            this.auth = res.data;
            if (!this.auth.username) {
             // alert("Username or password is in correct!");
              this.loginCheck=false;
              console.log(this.auth);
            } else {
              //alert("Login successfully...\nWelcome " + this.auth.name);
              this.$router.push( "/userhome/" + this.auth.id);
            }
          })
          .catch(e => {
            console.log(e);
          });
      }
    }
  }
};
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
#landing-page {
  background-image: url(https://i.ibb.co/nBpT6H0/xK.gif);
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}
</style>