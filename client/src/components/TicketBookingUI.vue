<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight mb-2">Ticket Booking</h1>
      </v-flex>
    </v-layout>
      <v-row align="center" justify="center"> 
        <v-img
            src="https://i.ibb.co/6N1dkWC/SEAT.png" 
              lazy-src="SEAT.png" 
              aspect-ratio="1"
              class="grey lighten-2"
              max-width="800"
              max-height="500"
          ></v-img>
     </v-row>
      <v-row justify="center">
         <v-col cols="7">
          <v-form v-model="valid" ref="form">
            
             <v-row justify="center">
              <v-col cols="6">
              <v-text-field label="user ID"
                outlined
                v-model="ticketBooking.userId"
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                required>
              </v-text-field>
              <p v-if="userCheck != ''">username : {{userName}}</p>
               </v-col>
                 <v-col cols="2">
                   <div class="my-2">
                     <v-btn @click="findUser" depressed large color="primary">Search</v-btn>
                  </div>
               </v-col> 
              </v-row>
             <div v-if="userCheck"> 
              <v-row>
              <v-col cols="10">
                <v-select
                  label="ภาพยนตร์"
                  outlined
                  v-model="ticketBooking.movieName"
                  :items="movie"
                  item-text="movie.name"
                  item-value="id"
                  :rules="[(v) => !!v || 'กรุณาเลือกภาพยนตร์']"
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
                    :rules="[(v) => !!v || 'กรุณาเลือกวันที่ฉาย']"
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
                    :rules="[(v) => !!v || 'กรุณาเลือกเวลาที่ฉาย']"
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
                    :rules="[(v) => !!v || 'กรุณาเลือกที่นั่ง']"
                    required 
                  ></v-select>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="10">
                  <v-text-field label="หมายเหตุ [กรุณากรอกข้อมูลไม่เกิน 50 ตัว]"
                  outlined
                    v-model="ticketBooking.note"
                    required>
                  </v-text-field>
                </v-col>
              </v-row><br>

                <v-row justify="center"> 
                <v-btn @click="saveTicketBooking" :class="{ red: !valid, green: valid }">submit</v-btn>
                </v-row> <br> <br>
              
          <div v-if = "clickBooking == true">
            <div v-if = "bookingCheck == true">
               <v-row justify="center">  
                 <v-col cols="10"> 
                  <v-alert 
                   prominent
                   type="success">
                    <v-row align="center">
                    <v-col class="grow">จองตั๋วสำเร็จ<br></v-col>
                    <v-col class="shrink">
                    <v-btn @click="ticketData " :class="font" >รายละเอียดการจองตั๋ว</v-btn>
                    </v-col>
                    </v-row>
                  </v-alert> 
                  </v-col> 
                </v-row>  
              </div>
             <div v-if = "bookingCheck == false">
              <v-row justify="center">
              <v-col cols="10">
                <v-alert 
                   prominent
                   type="error">
                    <v-row align="center">
                    <v-col class="grow">จองตั๋วไม่สำเร็จ<br></v-col>
                    <v-col class="shrink">
                    <v-btn @click="ticketData " :class="font" >รายละเอียดการจองตั๋ว</v-btn>
                    </v-col>
                    </v-row>
                  </v-alert>
              </v-col>
              </v-row>
              </div>
            </div>
           </div>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "ticketBooking",
  data() {
    return {
     // user: [],
      ticketBooking: [{
        userId: null,
        movieName: null,
        movieDay: null,
        movieTime: null,
        seatType: null,
        note: null
      }
      ],
      valid: false,
      userCheck: false,
      userName: "",
      movie: [],
      seatType: [],
      clickBooking: false,
      bookingCheck: false,
      
    };
  },
  methods: {
    /* eslint-disable no-console */

    
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
    findUser() {
      http
        .get("/user/" + this.ticketBooking.userId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.userName = response.data.name;
            this.userCheck = response.status;
          } else {
            this.clear()
          }          
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },  
    saveTicketBooking() { 
      this.clickBooking = true;
      this.bookingCheck = true;
      http
        .post(
          "/ticketBooking/" +
            this.ticketBooking.userId + "/" + 
            this.ticketBooking.movieName + "/" + 
            this.ticketBooking.movieDay + "/" + 
            this.ticketBooking.movieTime + "/" + 
            this.ticketBooking.seatType + "/" + 
            this.ticketBooking.note,
            this.ticketBooking
        )
        .then(response => {
          console.log(response);
          this.clickBooking = true;
          this.bookingCheck = true; 
          //this.$router.push("/ticketData");
        })
        .catch(e => {
          console.log(e); 
          this.clickBooking = true;
          this.bookingCheck = false;
        });
       this.submitted = true; 
    },

     ticketData() {
      this.$router.push("/ticketData");
      window.location.reload()
    },

    refreshList() {
    //this.getUser();
    this.getManageMovie();
    this.getSeatType();
    }
  },
  
  mounted() {
   // this.getUser();
    this.getManageMovie();
    this.getSeatType();
  }
};
</script>
<style>
@import url('https://fonts.googleapis.com/css?family=Mitr&display=swap');
font {
  font-family: 'Mitr', sans-serif;
}
div {
  font-family: 'Mitr', sans-serif;
}
</style>