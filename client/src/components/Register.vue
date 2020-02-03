<template>

<div>
  <v-container>
      <v-card 
    class="mx-auto"
    style="max-width: 500px;"
  >
    <v-toolbar
      color="#009688"
      cards
      dark
      flat
    > <v-icon>mdi-account-plus</v-icon>&nbsp;&nbsp;
      <v-card-title class="title font-weight-regular">สมัคสมาชิก</v-card-title>
     
      <v-spacer></v-spacer>

    </v-toolbar>
<v-form
      ref="form"
      class="pa-4 pt-6"
    >
      <table style="width:100%">
    <tr>
      <th>
      <v-text-field 
      v-model="username"
      label="Usename" 
      solo
      ></v-text-field>
      </th>
      <th>
      <v-text-field 
      v-model="pw"
      label="Password" 
      solo>
       </v-text-field>
      </th>
    </tr>
      </table>

      <v-select
          v-model="selectGender"
          :items="Gender"
          item-text="gender_name"
          item-value="gender_id"
          label="Gender"
          solo
      ></v-select>

       <v-text-field 
       v-model="name"
       label="Name" 
       solo>
       </v-text-field>

      <v-select
          v-model="selectJob"
          :items="Jobs"
          item-text="job"
          item-value="job_id"
          label="Career"
          solo
      ></v-select>     

      <v-text-field 
      v-model="age"
      label="Age" 
      solo>
      </v-text-field>

      <v-select
          v-model="selectCountry"
          :items="Country"
          item-text="country"
          item-value="country_id"
          label="Country"
          solo
      ></v-select>

       <v-text-field 
       v-model="tel"
       label="Phone number" 
       solo>
       </v-text-field>
        
<table style="width:100%">
  <tr><th>

  </th>
  
  <th>
        <v-btn color="#009688" width="200" @click="SaveRe" >บันทึก</v-btn>
    </th><v-btn color="#FF6262" width="100" router-link to="/" >หน้าหลัก</v-btn></tr>
    
    </table>
          <div v-if = "clickRegister == true">
            <v-alert type="success">Register Completed</v-alert>
          </div>
          <div v-if = "UsernameCheck == true">
            <v-alert type="error">Username ถูกใช้ไปแล้ว!</v-alert>
          </div>
          <div v-if = "Enertnull == true">
            <v-alert type="error">โปรดระบุข้อมูลให้ครบถ้วน</v-alert>
          </div>
</v-form>
      </v-card>
 
  </v-container>
</div>

</template>


<script>
import http from "../http-common";
export default {
  data: () => {
   return{
    username : null,
    pw : null,
    selectGender : null,
    name : null,
    selectJob : null,
    age : null,
    selectCountry : null,
    tel : null,
    Jobs : [],
    Country : [],
    Gender : [],
     clickRegister : false,
      Enertnull: false,
      UsernameCheck: false,
      
  };
  },
  methods: {
    /* eslint-disable no-console */
   getGender() {
             http
               .get("/gender")
               .then(response => {
                this.Gender = response.data;
                console.log(response.data);
               })
               .catch(e => {
                 console.log(e);
               });
         },

   getJobs() {
             http
               .get("/jobs")
               .then(response => {
                this.Jobs = response.data;
                console.log(response.data);
               })
               .catch(e => {
                 console.log(e);
               });
         },
    getCountry() {
             http
               .get("/country")
               .then(response => {
                this.Country = response.data;
                console.log(response.data);
               })
               .catch(e => {
                 console.log(e);
               });
         },
 
    SaveRe() {
      if (this.username==null || this.pw ==null|| 
        this.selectGender==null || this.name==null ||
        this.selectJob==null ||
        this.age==null|| this.selectCountry==null ||
        this.tel==null){
         this.Enertnull = true;
      } else {
        this.checkUsername();
      }
    },

    checkUsername() {
      http
        .get("/user/check/" + this.username)
        .then(res => {
          if (!res.data) {
            this.clickRegister = true;
            this.registered();}
            else{
             this.UsernameCheck = true;
            }
        })
        .catch(e => {
          console.log(e);
        });
    },
    registered() {
         http
        .post("/user/" +
          this.username+"/"+this.pw+"/"+this.selectGender
          +"/"+this.name+"/"+this.selectJob+"/"+this.age+"/"+this.selectCountry+"/"+this.tel)
        .then(response => {
          console.log(response);

        })
        .catch(e => {
          console.log(e);
        });
     
    },
  refreshList() {
    this.getUser();
    this.getGender();
    this.getJobs();
    this.getCountry();
    }
    /* eslint-enable no-console */
},
  mounted() {
      this.getGender();
      this.getJobs();
      this.getCountry();

  },
};
</script>

<style>
@import url('https://fonts.googleapis.com/css?family=Prompt&display=swap');

    div {
    font-family: 'Prompt', sans-serif;
  
    }

</style>
