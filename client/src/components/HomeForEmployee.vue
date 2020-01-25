<template>
  <v-app id="keep">
    <v-navigation-drawer v-model="drawer" app temporary>
      <v-list dense>
        <v-list-item @click="goHomeForEmployee">
          <v-list-item-action>
            <v-icon>mdi-home</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-divider inset></v-divider>


        <v-list-item @click="goAddmovie">
          <v-list-item-action>
            <v-icon light>movie_filter</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>เพิ่มภาพยนตร์</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
       
        <v-divider inset></v-divider>
        <v-list-item @click="goManage">
          <v-list-item-action>
            <v-icon light>mdi-wrench</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>จัดการรอบฉายภาพยนตร์</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        
        <v-divider inset></v-divider>
        <v-list-item @click="goFaD">
          <v-list-item-action>
            <v-icon light>mdi-food</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Food And Drink</v-list-item-title>
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

    <v-app-bar app color="amber"  light clipped-left>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>
        <v-icon light>mdi-account</v-icon>
        <span class="title ml-3 mr-5">
        </span>
      </v-toolbar-title>
    </v-app-bar>

    <!-- <v-content>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col class="text-center"> -->
            <template v-if="content === 'moviepics'">
              <MoviePics :employee="employee" />
            </template>
            <template v-else-if="content === 'moviepics'">
              <HomeForEmployee :employee="employee" />
            </template>
            <template v-else-if="content === 'MovieAdd'">
              <MovieAdd :employee="employee" />
            </template>
            <template v-else-if="content === 'ManageMovie'">
              <ManageMovie :employee="employee" />
            </template>
           <template v-else-if="content === 'Foodanddrink'">
              <Foodanddrink :employee="employee" />
            </template>
           
          <!-- </v-col>
        </v-row>
      </v-container>
    </v-content> -->
    <v-footer color="amber" app>
      <span
        class="white--text"
      >ระบบโรงภาพยนตร์ &copy; 2020</span>
    </v-footer>
  </v-app>
</template>

<script>
import http from "../http-common";

import ManageMovie from "../components/ManageMovie";
import MovieAdd from "../components/MovieAdd";
import Foodanddrink from "../components/Foodanddrink"
import MoviePics from "../components/MoviePics"

export default {
  components: {
    ManageMovie,
    MovieAdd,
    Foodanddrink,
    MoviePics,
  },
  mounted() {
    this.employee.employee_id = this.$route.params.employee_id;
    this.getEmployee();
  },
  props: {
    source: String
  },
  data: () => ({
    drawer: null,
    employee: {},
    content: "moviepics"
  }),
  methods: {
      /* eslint-disable no-console */
    logout() {
      if (confirm("Are you sure want to logout?")) {
        this.$router.push("/");
      }
    },
    goAddmovie() {
     this.content = "MovieAdd";
      this.drawer = false;
    },
    goManage() {
     
      this.content = "ManageMovie";
      this.drawer = false;
    },
   goFaD() {
      this.content = "Foodanddrink";
      this.drawer = false;
    },
    goHomeForEmployee() {
      this.content = "moviepics";
      this.drawer = false;
    },
    getEmployee() {
      http
        .get("/employee/" + this.employee.emplyee_id)
        .then(res => {
          this.employee = res.data;
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

