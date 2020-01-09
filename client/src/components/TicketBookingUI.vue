<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Ticket Booking</h1>
      </v-flex>
    </v-layout>


          <v-row align="center" justify="center"> 
            <v-img
                src="SEAT.png" 
                 lazy-src="SEAT.png" 
                 aspect-ratio="1"
                 class="grey lighten-2"
                  max-width="800"
                 max-height="500"
             ></v-img>
          </v-row>
    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">

          
          
            <v-row>
              <v-col cols="10">
                <v-select
                  label="ภาพยนตร์"
                  outlined
                  v-model="ticketBooking.movieName"
                  :items="movie"
                  item-text="movie.name"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>
            </v-row> 

            <v-row>
              <v-col cols="10">
                <v-select
                  label="วันฉาย"
                  outlined
                  v-model="ticketBooking.movieDay"
                  :items="movie"
                  item-text="date"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>
            </v-row> 


            <v-row>
              <v-col cols="10">
                <v-select
                  label="เวลาฉาย"
                  outlined
                  v-model="ticketBooking.movieTime"
                  :items="movie"
                  item-text="time.time"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>
            </v-row> 

            <v-row>
              <v-col cols="10">
                <v-select
                  label="ที่นั่ง"
                  outlined
                  v-model="ticketBooking.seatType"
                  :items="seatType"
                  item-text="seat"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>
            </v-row>
            <v-row justify="center">
              <v-col cols="12">
                <v-btn @click="saveTicketBooking" :class="{ red: !valid, green: valid }">submit</v-btn>
               <!-- <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn> -->
              </v-col>
            </v-row>
            <br />
          
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";
export default {
 // name: "ticketBooking",
  data() {
    return {
      //user: [],
      movie: [],
      seatType: [],
      ticketBooking: [{
        //user_id: "",
        movieName: "",
        movieDay: "",
        movieTime: "",
        seatType: ""
      }
      ],
      valid: false,
    };
  },
  methods: {
    /* eslint-disable no-console */

    /*getUser() {
      http
        .get("/user")
        .then(response => {
          this.user = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },*/
    getManageMovie() {
      http
        .get("/managemovie")
        .then(response => {
          this.movie = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },  
    getSeatType() {
      http
        .get("/seatType")
        .then(response => {
          this.seatType = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }, 
    saveTicketBooking() { //this.ticketBooking.user_id +"/" +
      http
        .post(
          "/ticketBooking/" +
           this.ticketBooking.movieName +
            "/" + 
             this.ticketBooking.movieDay +
            "/" + 
             this.ticketBooking.movieTime +
            "/" + 
            this.ticketBooking.seatType
        )
        .then(response => {
          console.log(response);
          alert("จองตั๋วสำเร็จ");
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  
  mounted() {
    //this.getUser();
    this.getManageMovie();
    this.getSeatType();
  }
};
</script>