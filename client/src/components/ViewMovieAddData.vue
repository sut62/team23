<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">ข้อมูลการเพิ่มภาพยนตร์</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="10">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1">
        </v-data-table>
      </v-col>
      </v-row>
    
    <v-row justify="center">
       <v-btn color="amber" @click="back">ย้อนกลับ</v-btn>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";
export default {
  name: "ViewMovieAddData",
  data() {
    return {
      error: null,
      headers: [
        { text: "Employee",value: "employee.employee"},
        { text: "Name",value: "name" },
        { text: "Genre",value: "movieGenre.movieGenre" },
        { text: "Synopsis",value: "synopsis"},
        { text: "Director",value: "director"},
        { text: "Actor",value: "actor"},
        { text: "Studio",value: "studio"},
        { text: "Rate",value: "movieRate.movieRate"},
        { text: "Length",value: "length"},
        { text: "System Type",value: "systemType.systemType"}
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล VideoRental ใส่ combobox
    getMovieadd() {
      http
        .get("/movieadd")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
   back() {
      this.$router.push("/empoyeehome");
    },
    refreshList() {
      this.getMovieadd();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getMovieadd();
  }
};
</script>