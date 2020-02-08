<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Ticket Data</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="8">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1">
        </v-data-table>
      </v-col>
    </v-row>
    <v-row justify="center">
       <v-btn color="purple lighten-4" @click="back">ย้อนกลับ</v-btn>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";
export default {
  name: "TicketData",
  data() {
    return {
      headers: [
       { text: "Movie", value: "movie.movie.name" },
       { text: "Date", value: "movie.date" },
       { text: "Time", value: "movie.time.time" },
       { text: "Seat", value: "type.seat" }
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล VideoRental ใส่ combobox
    getTicketBookings() {
      http
        .get("/ticketBooking")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    back() {
      this.$router.push("/ticketbooking");
    },
    refreshList() {
      this.getTicketBookings();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getTicketBookings();
  }
};
</script>