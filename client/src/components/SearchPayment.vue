<template>
  <v-container>
    <div>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <v-row justify="center">
        <!-- <h1 class="display-2 font-weight-bold mb-3 ">ชำระเงิน</h1> -->
        <center> <v-card-title> <font size="10"> ตรวจสอบการชำระเงิน </font> <br></v-card-title></center>
        </v-row>
      </v-flex>
    </v-layout>
 <br>
          <br>
       <v-row justify="center">
         <v-col cols="10">
          <v-form v-model="valid" ref="form">
            <dir>

             <v-row justify="center">
            <v-col cols="6">
              <v-text-field label="ไอดีการชำระเงิน"
                outlined
                v-model="payment.id"
                color="purple darken-2"
                >
              </v-text-field>
              <!-- <p v-if="idCheck != ''"></p> -->
            </v-col>
          </v-row>
          <v-row justify="center">
            <v-col cols="6">
              <div class="my-2">
                <!-- <v-btn @click="findTicketbookingId" block large load color="purple darken-2" dark>ตรวจสอบ</v-btn> -->
                <v-btn @click="findId" block large color="purple darken-2" dark>ตรวจสอบ</v-btn>
              </div>
            </v-col>
          </v-row>
        
          <br>


        <div v-if="idCheck">
          <v-row justify="center">
            <v-col cols="10">
          
            <v-card class="mx-auto" max-width="700" >
              <v-card-text>
                <div>ประวัติการชำระเงินหมายเลขไอดี: {{payment.id}}</div>
               <br><br>
               <div class="display-2">
                    <p>ชำระแล้ว</p>
               </div>
                

               <div class="text--primary">
                 <br>
                 <h1 v-for="item in payment" v-bind:key="item.id" >ผ่านธนาคาร  :  {{ item.bank.name }}</h1><br>
                 <h1 v-for="item in payment" v-bind:key="item.id" >ผู้ชำระ  :  {{ item.cardHolderName }}</h1>
               </div>
             </v-card-text>
           </v-card>
           
             
             </v-col>
             </v-row>
            </div>

            <div v-if = "Alert == true"> 
              <v-row justify="center">
              <v-col cols="6">
              <v-alert type="error">ไม่พบข้อมูลการชำระเงิน
                </v-alert>
              </v-col>
              </v-row>
                </div>
                


          </dir>

          
        </v-form>
      </v-col>
    </v-row>

    </div>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "payment",
  data() {
    return {
     
      payment: {
        id:"",
        cardHolderName: "",
        bank:{
          id:"",
          name:"",
        }
      },

      valid: false,
      idCheck: false,
      cardHolderName: "",
      Alert: false,
    };
  },
  methods: {
    /* eslint-disable no-console */

    getBanks() {
          http
            .get("/payment/"+this.$route.params.id)
            .then(response => {
              console.log(response);
              this.payment = response.data;
              if(response.data.bank.id == null)
                this.payment.bank.id = 0;
              console.log(this.payment);
              
            })
            .catch(e => {
              console.log(e);
            });
        },
    
    findId() {
      http
        .get("/payment/" + this.payment.id)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.cardHolderName = response.data.cardHolderName;
            this.idCheck = response.status;
          } else {
            this.clear();
            this.Alert = true;
            
          }          
        })
        .catch(e => {
          console.log(e);
          
        });
        this.Alert = false;
      // this.submitted = true;
    },

    clear() {
      this.$refs.form.reset();
      this.idCheck=false;
     // this.pclear = false;
    },
    mounted() {
    this.getTicketBookings();
    this.getCardTypes();
    this.getBanks();
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
  }


};
</script>

<style>
@import url('https://fonts.googleapis.com/css?family=Mitr&display=swap');
font {
  font-family: 'Mitr', sans-serif;
}
</style>