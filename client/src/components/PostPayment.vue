<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">ชำระเงิน</h1>
      </v-flex>
    </v-layout>
    <v-form v-model="valid" ref="form">
    <v-row justify="center">
      <v-col cols="6">
        <v-select
          label="หมายเลขคำสั่งซื้อ"
          outlined
          v-model="Payment.ticketBooking"
          :items="ticketbookings"
          item-text="bookingDate"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-select>
      </v-col>
    </v-row>

    <!-- <v-row justify="center">
    <v-date-picker v-model="picker"></v-date-picker>
  </v-row> -->

    <v-row justify="center">
      <v-col  cols="6">
        <div >
        <v-select
          label="วิธีการชำระ"
          outlined
          v-model="Payment.paymentOption"
          :items="paymentOptions"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          
        ></v-select>
        </div>
      </v-col>
    </v-row>
   

    <div v-if="Payment.paymentOption == 2">

        <v-row justify="center">
      <v-col cols="6">
        
        <v-select
          label="ธนาคาร"
          outlined
          v-model="Payment.bank"
          :items="banks"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          
        ></v-select>
      
      </v-col>
      
    </v-row>

    
    </div>


</v-form>
    <v-row justify="center">
      <v-col cols="6">
        <v-btn @click="savePayment" :class="{ red: !valid, green: valid }">submit</v-btn>
        <v-btn style="margin-left: 20px;" @click="clear">clear</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>

import http from "../http-common";


export default {
  
  name: "Payment",
  data() {
    return {
      Payment: {
        ticketBooking: "",
        paymentOption: "",
        bank: 0,
        
        
        
        

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
      paymentOptions: [],
      banks: [],
      
      prop:[]
          
    };
    
  },
  methods: {


    // get1() {
    //   http
    //     .get("/payment/")
    //     .then(response => {
    //       this.payment = response.data;
    //       console.log(response.data);
    //     })
    //     .catch(e => {
    //       console.log(e);
    //     });
    // },
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
    getPaymentOptions() {
      http
        .get("/paymentOption")
        .then(response => {
          this.paymentOptions = response.data;
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
    
    
    // ดึงข้อมูล Video ใส่ combobox
    

    
    
    savePayment() {
      http
        .post(
          "/paymentPost/" +
            this.Payment.ticketBooking + "/" +
            this.Payment.paymentOption + "/" + 
            this.Payment.bank,
            this.Payment
        )
        .then(response => {
          console.log(response);
         alert("ชำระเงินสำเร็จ");
         this.clear();
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },

    clear() {
      this.$refs.form.reset();
     // this.pclear = false;
    },
    refreshList() {
      this.getTicketBookings();
      this.getPaymentOptions();
      this.getBanks();
      
      
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getTicketBookings();
    this.getPaymentOptions();
    this.getBanks();
    
    
    
  },
  
};

</script>
