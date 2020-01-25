<template>
  <body>
  <div>
      <v-card
    class="mx-auto"
    max-width="400"
  >
    <v-img
      class="white--text align-end"
      height="200px"
      src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
    >
     <center> <v-card-title> <font size="10"> ข้อมูลส่วนตัว </font> <br></v-card-title></center>
    </v-img>
    <v-card class="text--primary">
        <v-card-actions>
      <div> 
               
                <p class="text-md-left"><i class="material-icons">person</i>  ชื่อผู้ใช้ : {{user.username}}</p>
                <p class="text-md-left"><i class="material-icons">lock</i> ระหัสผ่าน : {{user.password}}</p>
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
        user:{}
    },
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