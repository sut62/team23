<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">เพิ่มภาพยนตร์</h1>
      </v-flex>
    </v-layout>

          <v-row justify="center">
              <v-col cols="10">
                <v-select
                  label="ชื่อพนักงาน"
                  outlined
                  v-model="movieadd.employeeId"
                  :items="employees"
                  item-text="employee"
                  item-value="employee_id"
                  :rules="[(v) => !!v || 'กรุณาเลือกข้อมูล']"
                ></v-select>
              </v-col>
            </v-row>

            <v-row justify="center">
            <v-col cols="10">
               <v-textarea
                outlined
                label="ชื่อภาพยนตร์"
                v-model="movieadd.name"
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                required
                auto-grow
                rows="3"
                row-height="25"
                shaped
                 ></v-textarea>
            </v-col>
          </v-row>

          <v-row justify="center">
              <v-col cols="10">
                <v-select 
                  label="ประเภทภาพยนตร์"  
                  outlined
                  v-model="movieadd.moviegenreId"
                  :items="moviegenres"
                  item-text="movieGenre"
                  item-value="movieGenre_id"
                  :rules="[(v) => !!v || 'กรุณาเลือกข้อมูล']"
                ></v-select>
              </v-col>
            </v-row>
        
          <v-row justify="center">
            <v-col cols="10">
               <v-textarea
                outlined
                label="เนื้อเรืองย่อ"
                v-model="movieadd.synopsis"
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                required
                auto-grow
                rows="3"
                row-height="25"
                shaped
                 ></v-textarea>
            </v-col>
          </v-row>
            
            <v-row justify="center">
            <v-col cols="10">
               <v-textarea
                outlined
                label="ผู้กำกับ"
                v-model="movieadd.director"
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                required
                auto-grow
                rows="3"
                row-height="25"
                shaped
                 ></v-textarea>
            </v-col>
          </v-row>

          <v-row justify="center">
            <v-col cols="10">
               <v-textarea
                outlined
                label="นักแสดงนำ"
                v-model="movieadd.actor"
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                required
                auto-grow
                rows="3"
                row-height="25"
                shaped
                 ></v-textarea>
            </v-col>
          </v-row>

          <v-row justify="center">
            <v-col cols="10">
               <v-textarea
                outlined
                label="ค่ายภาพยนตร์"
                v-model="movieadd.studio"
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                required
                auto-grow
                rows="3"
                row-height="25"
                shaped
                 ></v-textarea>
            </v-col>
          </v-row>

          <v-row justify="center">
              <v-col cols="10">
                <v-select 
                  label="เรทผู้ชม"  
                  outlined
                  v-model="movieadd.movierateId"
                  :items="movierates"
                  item-text="movieRate"
                  item-value="movieRate_id"
                  :rules="[(v) => !!v || 'กรุณาเลือกข้อมูล']"
                ></v-select>
              </v-col>
            </v-row>

            <v-row justify="center">
            <v-col cols="10">
               <v-textarea
                outlined
                label="ความยาวภาพยนตร์(นาที)"
                v-model="movieadd.length"
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                required
                auto-grow
                rows="3"
                row-height="25"
                shaped
                 ></v-textarea>
            </v-col>
          </v-row>

          <v-row justify="center">
              <v-col cols="10">
                <v-select 
                  label="ระบบฉาย"  
                  outlined
                  v-model="movieadd.systemtypeId"
                  :items="systemtypes"
                  item-text="systemType"
                  item-value="systemType_id"
                  :rules="[(v) => !!v || 'กรุณาเลือกข้อมูล']"
                ></v-select>
              </v-col>
              </v-row>

<div v-if = "clickSave == true">
          <div v-if = "movieaddCheck == true">
          <v-row justify="center">
    <v-col cols="6">
    <v-alert type="success">  บันทึกข้อมูลสำเร็จ</v-alert>
    </v-col>
    </v-row>
  </div>

        <div v-if = "movieaddCheck == false">
        <v-row justify="center">
        <v-col cols="6">
          <v-alert type="error">โปรดกรอกข้อมูลให้ครบ</v-alert>
          </v-col>
          </v-row>
        </div>

        <div v-if = "nameCheck == false">
        <v-row justify="center">
        <v-col cols="6">
          <v-alert type="error">ชื่อหนังเรื่องนี้ถูกใช้ไปแล้ว</v-alert>
          </v-col>
          </v-row>
        </div>
      
        </div>


          <v-row justify="center">
            <v-btn style="margin-left: 15px;" color="amber" @click="saveMovieAdd">บันทึก</v-btn>
            <v-btn style="margin-left: 15px;" color="green" @click="show">แสดงข้อมูล</v-btn>
            <v-btn style="margin-left: 15px;" @click="clear">ยกเลิก</v-btn>
          </v-row>
          
</v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "movieadd",
  data(){
    return{
      movieadd: {
        employeeId: null,
        name: null,
        moviegenreId: null,
        synopsis: null,
        director: null,
        actor: null,   
        studio: null,
        movierateId: null,
        length: null,
        systemtypeId: null,
        release: null, 
      },
      items: [],
      valid : false,
      clickSave : false,
      movieaddCheck : false,
      nameCheck : false,
      employees : [],
      moviegenres : [],
      movierates : [],
      systemtypes : [],
    };
  },
  methods: {
    /* eslint-disable no-console */
    getEmployees() {
      http
        .get("/employee")
        .then(response => {
          this.employees = response.data; 
          console.log(response.date);
        })
        .catch(e => {
          console.log(e);
        });
    },
     getMovieGenres() {
      http
        .get("/moviegenre")
        .then(response => {
          this.moviegenres = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getMovieRates() {
      http
        .get("/movierate")
        .then(response => {
          this.movierates = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getSystemTypes() {
      http
        .get("/systemtype")
        .then(response => {
          this.systemtypes = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
     saveMovieAdd() {
       this.clickSave = true;
      if (this.movieadd.employeeId==null || this.movieadd.name ==null|| 
        this.movieadd.moviegenreId ==null || this.movieadd.synopsis==null ||
        this.movieadd.director==null ||
        this.movieadd.actor==null|| this.movieadd.studio==null ||
        this.movieadd.movierateId==null || this.movieadd.length==null  || this.movieadd.systemtypeId==null){
        //alert("กรุณากรอกข้อมูลให้ครบถ้วน!");
      } else {
        this.checkName();
      }
    },

    checkName() {
      this.clickSave = true;
      http
        .get("/movieadd/check/" + this.movieadd.name)
        .then(res => {
          if (!res.data) {
            this.add();
          } else {
            this.nameCheck = false;
          }
        })
        .catch(e => {
          console.log(e);
        });
    },
    add() {
          // this.clickSave = true;
          // this.movieaddCheck = true;
         http
        .post(
          "/movieadd/" +
            this.movieadd.employeeId +
            "/"+
            this.movieadd.name +
            "/" +
            this.movieadd.moviegenreId +
            "/" +
            this.movieadd.synopsis +
            "/" +
            this.movieadd.director +
            "/" +
            this.movieadd.actor +
            "/" +
            this.movieadd.studio +
            "/" +
            this.movieadd.movierateId +
            "/" +
            this.movieadd.length +
            "/" +
            this.movieadd.systemtypeId,
          this.movieadd
        )
          .then(response => {
          console.log(response);
          this.clickSave = true;
          this.movieaddCheck = true;
          //alert("บันทึกข้อมูลสำเร็จ");
        })
        .catch(e => {
          console.log(e);
          this.clickSave = true;
          this.movieaddCheck = false;
        });
    },
    clear() {
      this.$refs.form.reset();
    },
    show() {
      this.$router.push("/viewmovieadddata");
    },
    refreshList() {
      this.getEmployees();
      this.getMovieGenres();
      this.getMovieRates();
      this.getSystemTypes();
    }
    /* eslint-enable no-console */
  },
  mounted() {
      this.getEmployees();
      this.getMovieGenres();
      this.getMovieRates();
      this.getSystemTypes();
      
  }
};

</script>