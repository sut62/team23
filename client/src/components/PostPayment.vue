<template>
  <v-container>
    <div>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br>
        <br>
        <v-row justify="center">
        <!-- <h1 class="display-2 font-weight-bold mb-3 ">ชำระเงิน</h1> -->
        <center> <v-card-title> <font size="10"> ชำระเงิน </font> <br></v-card-title></center>
        </v-row>
      </v-flex> 
    </v-layout>
    <br>
    <br>
    <v-form v-model="valid" ref="form">
    <v-row justify="center">
      <v-col cols="6">
        <v-select
          label="หมายเลขคำสั่งซื้อ"
          outlined
          v-model="payment.ticketBooking"
          :items="ticketbookings"
          item-text="bookingDate"
          item-value="id"
          :rules="[(v) => !!v || 'กรุณาเลือกข้อมูล']"
          required
        ></v-select>
      </v-col>
    </v-row>

    <!-- <v-row align="center" justify="center"> 
            <v-img
                src="paymentbar_2.png" 
                 
                 aspect-ratio="1"
                 class="grey lighten-2"
                  max-width="600"
                 max-height="49"
             ></v-img>
          </v-row> -->

    <v-row justify="center">
      <v-col  cols="6">
        <div >
        <v-select
          label="ประเภทของบัตร"
          outlined
          v-model="payment.cardType"
          :items="cardTypes"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'กรุณาเลือกข้อมูล']"
          required
          
        ></v-select>
        </div>
      </v-col>
    </v-row>

    <v-row justify="center">
                    <v-col cols="5">
                      <v-text-field
                              class="font"
                              outlined
                              label="หมายเลขบัตรเครดิต/เดบิต"
                              v-model="payment.cardNumber"
                              :rules="[(v) => !!v || 'ตัวเลข 16 ตัวหน้าบัตรเครดิต/เดบิต']"
                              required
                      ></v-text-field>
                    </v-col>

                    <v-col cols="1">
                      <v-text-field
                              class="font"
                              outlined
                              label="Security Code (CVV2)"
                              v-model="payment.securityCode"
                              :rules="[(v) => !!v || 'last 3 digits on the back of card']"
                              required
                      ></v-text-field>
                    </v-col>
                  </v-row>

  <v-row justify="center">
    <v-col cols="6">
       <v-text-field
           class="font"
           outlined
           label="ชื่อผู้ถือบัตร"
           v-model="payment.cardHolderName"
           :rules="[(v) => !!v || 'กรุณากรอกชื่อผู้ถือบัตร']"
           required
       ></v-text-field>
    </v-col>  
  </v-row>

  <v-row justify="center">
      <v-col cols="6">
        
        <v-select
          label="ธนาคาร"
          outlined
          v-model="payment.bank"
          :items="banks"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'กรุณาเลือกข้อมูล']"
          required
          
        ></v-select>
      
      </v-col>
      
    </v-row>


      <!-- <v-row justify="center">
        <v-col cols="6">
            <v-textarea
              outlined
              label="หมายเหตุ"
              v-model="payment.note"
            >
            </v-textarea>
          </v-col>
      </v-row> -->

<v-row justify="center">
      <!-- <v-col cols="6"> -->
        <v-btn @click="savePayment" :class="{ red: !valid, green: valid }">ชำระเงิน</v-btn>
        <v-btn style="margin-left: 20px;" @click="clear">ล้างข้อมูล</v-btn>
      <!-- </v-col> -->
    </v-row>
  <br>

  
    <div v-if = "clickPayment == true">

          <div v-if = "paymentCheck == true">
          <v-row justify="center">
    <v-col cols="6">
    <v-alert prominent type="success">
      <v-row align="center">
        <v-col class="grow">
          ชำระเงินสำเร็จ<br>
        </v-col>
        
        <v-col class="shrink">
          <v-btn @click="informPaymentId " :class="font" >แจ้งหมายเลขการชำระเงิน</v-btn>
        </v-col>
      </v-row>
    </v-alert>
    </v-col>
    </v-row>
  </div>

        <div v-if = "paymentCheck == false">
        <v-row justify="center">
        <v-col cols="6">
          <v-alert type="error">ชำระเงินไม่สำเร็จ</v-alert>
          </v-col>
          </v-row>
        </div>
      
        </div>
        

</v-form>

    
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
        ticketBooking: "",
        cardType: "",
        bank: "",
        cardHolderName: "",
        cardNumber: "",
        securityCode: "",
        note: "",
        bookingDate: ""
      },

      check: false,
      check2: false,
      val:"",
      
      components: {
      },

      picker: new Date().toISOString().substr(0, 10),
      valid: false,
      pclear: false,
      ticketbookings: [],
      cardTypes: [],
      banks: [],
      paymentCheck: false,
      clickPayment: false,
      
      prop:[]
          
    };
    
  },
  methods: {


    
    /* eslint-disable no-console */

    // ดึงข้อมูล Employee ใส่ combobox
    
   getTicketBookings() {
      http
        .get("/ticketBooking")
        .then(response => {
          this.ticketbookings = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getCardTypes() {
      http
        .get("/cardType")
        .then(response => {
          this.cardTypes = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
   getBanks() {
      http
        .get("/bank")
        .then(response => {
          this.banks = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    
    savePayment() {
      this.clickPayment = true;
      this.paymentCheck = true;
      http
        .post(
          "/paymentPost/"+
            this.payment.ticketBooking + "/" +
            this.payment.cardType + "/" + 
            this.payment.cardNumber + "/" +
            this.payment.securityCode + "/" +
            this.payment.cardHolderName + "/" +
            this.payment.bank,
            this.payment
        )
        .then(response => {
          console.log(response);
          this.clickPayment = true;
          this.paymentCheck = true;
          this.clear();
        })
        .catch(e => {
          console.log(e);
          this.clickPayment = true;
          this.paymentCheck = false;
          this.clear();
        });

      this.submitted = true;
    },

    informPaymentId() {
      this.$router.push("/informPaymentId");
      window.location.reload()
    },

    // informPaymentId() {
    //   http
    //     .then(response => {
    //       console.log(response);
    //       this.$router.push("/informPaymentId");
    //     })
    //     .catch(e => {
    //       console.log(e);
    //     });
    //   this.submitted = true;
    // },

    clear() {
      this.$refs.form.reset();
     // this.pclear = false;
    },
    refreshList() {
      this.getTicketBookings();
      this.getCardTypes();
      this.getBanks();
      
      
    }
    /* eslint-enable no-console */
  },

  mounted() {
    this.getTicketBookings();
    this.getCardTypes();
    this.getBanks();
    
    
    
  },
  
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