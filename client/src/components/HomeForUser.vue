<template>

  <v-app>
    <v-navigation-drawer v-model="drawer" app temporary>

        
        <v-list-item @click="goUserProfile">
          <v-list-item-action>
            <i class="material-icons">assignment</i>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>ประวัติส่วนตัว</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      <v-divider inset></v-divider>
      <v-list dense>
        <v-list-item @click="goTicketBooking">
          <v-list-item-action>
            <i class="material-icons">local_activity</i>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>จองตั๋วภาพยนตร์</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
       
        <v-divider inset></v-divider>
        <v-list-item @click="goPostPayment">
          <v-list-item-action>
            <i class="material-icons">local_atm</i>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>ชำระเงิน</v-list-item-title>
          </v-list-item-content>
        </v-list-item>


        <v-divider inset></v-divider>
        <v-list-item @click="goSearchPayment">
          <v-list-item-action>
            <i class="material-icons">check_circle_outline</i>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>ตรวจสอบการชำระเงิน</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        
        
        <v-divider inset></v-divider>

        <v-divider inset></v-divider>

        <v-list-item @click="logout">
          <v-list-item-action>
            <i class="material-icons">clear</i>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>ออกจากระบบ</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar app color="amber" light clipped-left>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"><i class="material-icons">menu</i></v-app-bar-nav-icon>
      <v-toolbar-title>
       
        <span
        class="black--text"
      >ยินดีต้อนรับคุณ {{this.user.name}}</span>
        <span class="title ml-3 mr-5">
        </span>
      </v-toolbar-title>
    </v-app-bar>

    <!-- <v-content> -->
      <!--<v-container class="fill-height" fluid>-->
       <!-- <v-row align="center" justify="center">-->
          <!-- <v-col class="text-center"> -->
           <template v-if="content === 'userhome'">
            <br><br>
              <UserProfile :user="user" />
             
           </template>
            <template v-if="content === 'HomeForUser'">
               
              <HomeForUser :user="user" />
             
            </template>
            <template v-else-if="content === 'TicketBookingUI'">
              
              <TicketBookingUI :user="user" />
              
            </template>
            <template v-else-if="content === 'PostPayment'">
               
              <PostPayment :user="user" />
             
            </template>

            <template v-else-if="content === 'searchPayment'">
               
              <SearchPayment :user="user" />
             
            </template>
           
          <!-- </v-col> -->
        <!-- </v-row> -->
      <!--</v-container>-->
    <!-- </v-content> -->
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
import UserProfile from "../components/UserProfile"
import SearchPayment from "../components/SearchPayment.vue";


export default {
  components: {
    TicketBookingUI,
    PostPayment,
    UserProfile,
    SearchPayment,
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
    goUserProfile() {
      this.content = "userhome";
      this.drawer = false;
    },
    goSearchPayment(){
      this.content = "searchPayment";
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

<style>
#landing-page {
  background-image: url(https://i.ibb.co/nBpT6H0/xK.gif);
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}
</style>
