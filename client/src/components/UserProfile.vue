<template>
  <body>
    <div>

 <v-row justify="center">
     <v-col cols="4">
      <v-form v-model="valid" ref="form">
        <dir>
<v-row justify="center">       
  <v-text> "โปรดใส่รหัสผ่านเพื่อดูข้อมูล" </v-text>
            <v-col cols="10" >
              <v-text-field label="Please Enter a password"
                outlined
                v-model="PasswordCheck"
                :rules="[(v) => !!v || 'Item is required']"
                required
                
                type="password">
              </v-text-field>
            </v-col>
            <v-col cols="2">
              <div class="my-2">
                <v-btn @click="findPasswod" depressed large color="primary">ตกลง</v-btn>
              </div>
            </v-col>
          </v-row>
           <div v-if = "Alert == true">
    <v-alert type="error">
      incorrect password
    </v-alert>
    </div>
        </dir></v-form></v-col></v-row>
    </div>
    
  <div v-if ="passwordR">
      <v-card
    class="mx-auto"
    max-width="400"
  >
    <v-img
      class="white--text align-end"
      height="150px"
      src="http://s18292.pcdn.co/wp-content/uploads/2015/12/tumblr_nx7k5nDuDA1s2wlrxo1_540.gif"
    >
     <center> <v-card-title> <font size="10" color = "black"> ข้อมูลส่วนตัว </font> <br></v-card-title></center>
    </v-img>
    <v-card class="text--primary">
        <v-card-actions>
      <div> 
                <p class="text-md-left"><i class="material-icons">vpn_key</i>  ID : {{user.id}}</p>
                <p class="text-md-left"><i class="material-icons">person</i>  ชื่อผู้ใช้ : {{user.username}}</p>
                <p class="text-md-left"><i class="material-icons">lock</i> รหัสผ่าน : {{user.password}}</p>
                <p class="text-md-left"><i class="material-icons">assignment_ind</i> ชื่อ : {{this.user.name}}</p>
                <p class="text-md-left"><i class="material-icons">access_alarm</i> อายุ : {{user.age}}</p>
                <p class="text-md-left"><i class="material-icons">accessibility</i> เพศ : {{this.user.gender.gender_name}}</p>
                <p class="text-md-left"><i class="material-icons">business</i> อาชีพ : {{user.jobs.job}}</p>
                <p class="text-md-left"><i class="material-icons">flag </i> ประเทศ : {{user.country.country}}</p>
                <p class="text-md-left"><i class="material-icons">call</i> เบอร์โทร : {{user.tel}}</p>
</div>
        </v-card-actions>
    </v-card>
  </v-card>
  </div>

  </body>
 
</template>
<script>

export default {
    props:{
        user:{},
         
    },
    data(){
      return{
        PasswordCheck: null,
        passwordR: false,
        Alert: false,
      };
    },
    methods: {
      findPasswod() {
        if(this.user.password == this.PasswordCheck){
          this.passwordR = true;
          this.Alert = false;
        }else{
          this.passwordR = false;
           this.Alert = true;
        }
    },
      }
}
</script>
<style>
@import url('https://fonts.googleapis.com/css?family=Prompt&display=swap');

    p {
    font-family: 'Prompt', sans-serif;
  
    }
    font{
        font-family: 'Prompt', sans-serif;
    }


</style>