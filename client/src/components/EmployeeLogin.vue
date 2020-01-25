<template>
 <v-app id="landing-page">
 
    <v-content>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="4">
            <v-card class="elevation-12">
              <v-toolbar color="warning" dark flat>
                <v-toolbar-title>เข้าสู่ระบบในฐานะพนักงาน</v-toolbar-title>
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
                <v-btn color="warning" @click="employeeLogin">Login</v-btn>
                <v-btn color="primary" router-link to="/">ลูกค้า?</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
  
 </v-app>
</template>

<script>
import http from "../http-common";
export default {
  props: {
    source: String
  },
  data: () => ({
    drawer: null,
    username: null,
    password: null,
    auth: {}
  }),
  methods: {
    /* eslint-disable no-console */
    employeeLogin() {
      if (!this.username || !this.password) {
        //alert("Please enter username and password.");
      } else {
        http
          .get("/employee/"+this.username+"/"+ this.password)
          .then(res => {
            console.log(JSON.parse(JSON.stringify(res.data)));
            this.auth = res.data;
            if (!this.auth.username) {
             // alert("Username or password is in correct!");
              console.log(this.auth);
            } else {
             // alert("Login successfully...\nWelcome " + this.auth.employee);
              this.$router.push("/empoyeehome");
            }
          })
          .catch(e => {
            console.log(e);
          });
      }
    },
    refreshList() {
    }
    /* eslint-enable no-console */
  }
};
</script>

<style>
#landing-page {
  background-image: url(https://i.ibb.co/nBpT6H0/xK.gif);
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}
</style>