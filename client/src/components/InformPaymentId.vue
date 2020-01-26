<template>
<v-container>
    <div>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <br>
 <br>
 <br>
        <v-row justify="center">
        <!-- <h1 class="display-2 font-weight-bold mb-3 ">ชำระเงิน</h1> -->
        <center> <v-card-title> <font size="10">แจ้งไอดีการชำระเงิน</font> <br></v-card-title></center>
        </v-row>
      </v-flex>
    </v-layout>
 <br>
 <br>
 <br>

 <v-row justify="center">
 <v-col cols="10">
  <v-card
    class="mx-auto"
    max-width="700"
    outlined
  >

    <v-list-item three-line>
      <v-list-item-content>
        <div class="overline mb-4">Notice</div>
        <!-- <p class="text-md-left"> ชื่อ : {{item.cardHolderName}}</p> -->
        <br>
        <br>
        <h1 v-for="item in payment" v-bind:key="item.id" class="display-1">หมายเลขไอดีการชำระเงิน  :  {{ item.id }}</h1>
        <v-list-item-subtitle color="#DD2C00">*ใช้สำหรับตรวจสอบการชำระเงิน </v-list-item-subtitle><br>
 <br>
 <br>
      </v-list-item-content>
      

      <v-img
                src="https://i.ibb.co/z2qRv9X/payment-gateway-computer-icons-e-commerce-payment-system-payment-2.png" 
                 aspect-ratio="1"
                 class="grey lighten-2"
                  max-width="50"
                 max-height="50"
             ></v-img>
    </v-list-item>

  </v-card>
  </v-col>
</v-row>

<br>
 <br>

    <v-row justify="center">
      <!-- <v-col cols="6"> -->
        <v-btn @click="searchPayment" large color="purple darken-2" dark>ตรวจสอบการชำระเงิน</v-btn>
        <!-- <v-btn style="margin-left: 20px;" @click="userHome" large >กลับหน้าหลัก</v-btn> -->
      <!-- </v-col> -->
    </v-row>

  </div>
  </v-container>
    
</template>

<script>

import http from "../http-common";

export default {
  
  data() {
    return {
      payment:  {},
    };
  },
  methods: {
    /* eslint-disable no-console */
    getPayments() {
          http
            .get("/payment/"+this.payment.id)
            .then(response => {
              console.log(response);
              this.payment = response.data;
              if(response.data.payment.id == null)
                this.payment.id = 0;
              console.log(this.payment);
            })
            .catch(e => {
              console.log(e);
            });
        },
    searchPayment() {
      http
          this.$router.push("/searchPayment");
        window.location.reload()
    },
    userHome() {
      this.$router.push("/userhome/" + this.auth.id);
      window.location.reload()
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
    takePaymentsHere(){
      http
        .get("/payment")
        .then(response => {
          this.payment = response.data;
          console.log(this.payment);
        })
        .catch(e => {
          console.log(e);
        });
    },
    /* eslint-disable no-console */
  },
  mounted(){
      this.takePaymentsHere();
      this.getUser();
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