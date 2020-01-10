<template>
  <v-app id="keep">
    <v-navigation-drawer v-model="drawer" app temporary>
      <v-list dense>
        <v-list-item @click="goHomeForUser">
          <v-list-item-action>
            <v-icon>mdi-home</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-divider inset></v-divider>
        
        <v-list-item @click="goTicketBooking">
          <v-list-item-action>
            <v-icon light>mdi-paper-roll</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>จองตั๋วภาพยนตร์</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
       
        <v-divider inset></v-divider>
        <v-list-item @click="goPostPayment">
          <v-list-item-action>
            <v-icon light>mdi-wrench</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>ชำระเงิน</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        
        
        <v-divider inset></v-divider>

        <v-divider inset></v-divider>

        <v-list-item @click="logout">
          <v-list-item-action>
            <v-icon light>mdi-logout</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>ออกจากระบบ</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar app color="amber" light clipped-left>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>
        <v-icon light>mdi-account</v-icon>
        <span class="title ml-3 mr-5">
          
          
        </span>
      </v-toolbar-title>
    </v-app-bar>

    <v-content>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col class="text-center">
            <template v-if="content === 'HomeForUser'">
              <HomeForUser :user="user" />
            </template>
            <template v-else-if="content === 'TicketBookingUI'">
              <TicketBookingUI :user="user" />
            </template>
            <template v-else-if="content === 'PostPayment'">
              <PostPayment :user="user" />
            </template>
           
          </v-col>
        </v-row>
      </v-container>
    </v-content>
    <v-footer color="amber" app>
      <span
        class="white--text"
      >ระบบโรงภาพยนตร์ &copy; 2020</span>
    </v-footer>
  </v-app>
</template>

<script>
import http from "../http-common";

import TicketBookingUI from "../components/TicketBookingUI";
import PostPayment from "../components/PostPayment"

export default {
  components: {
    TicketBookingUI,
    PostPayment,
    
  },
  mounted() {
    this.user.id = this.$route.params.id;
    this.getUser();
  },
  props: {
    source: String
  },
  data: () => ({
    drawer: null,
    user: {},
    content: "userhome"
  }),
  methods: {
      /* eslint-disable no-console */
    logout() {
      if (confirm("Are you sure want to logout?")) {
        this.$router.push("/");
      }
    },
    goTicketBooking() {
     this.content = "TicketBookingUI";
      this.drawer = false;
    },
   goPostPayment() {
      this.content = "PostPayment";
      this.drawer = false;
    },
    goHomeForUser() {
      this.content = "HomeForUser";
      this.drawer = false;
    },
    getUser() {
      http
        .get("/user/" + this.user.id)
        .then(res => {
          this.user = res.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
      refreshList() {
    }
    /* eslint-enable no-console */
  }
};
</script>
