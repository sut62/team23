<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Manage Movie Show</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="8">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1">
        </v-data-table>
      </v-col>
      </v-row>
      <v-row justify="center">
    <v-btn color="#FF6262" width="100" router-link to="/empoyeehome" >ย้อนกลับ</v-btn>
     </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";
export default {
  name: "ShowManageMovie",
  data() {
    return {
      headers: [
        {
          text: "Movie",
          align: "left",
          sortable: false,
          value: "movie.name"
        },
        { text: "พากย์", value: "lan.langauge" },
        { text: "Room", value: "ro.room" },
        { text: "Date", value: "date" },
        { text: "Time", value: "time.time" },
        { text: "Note", value: "note" },
        
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล VideoRental ใส่ combobox
    getManageMovies() {
      http
        .get("/managemovie")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getManageMovies();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getManageMovies();
  }
};
</script>