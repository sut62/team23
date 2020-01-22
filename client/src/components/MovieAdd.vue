<template>
  <v-card max-width="800" class="mx-auto" color="ffffff">
    <v-app-bar
      absolute
      color="#01579B"
      dark
      shrink-on-scroll
      prominent
      scroll-target="#scrolling-techniques" 
    > 
    
      <v-toolbar-title>เพิ่มภาพยนตร์</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-app-bar>
    <v-sheet
      id="scrolling-techniques"
      class="overflow-y-auto"
      max-height="600"
    >
      <v-container style="height: 150px;"></v-container>
    </v-sheet>
     <v-form v-model="valid">
      

    <v-container>

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
                label="ความยาวภาพยนตร์"
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
          <v-row justify="center">
            <v-btn @click="saveMovieAdd" :class="{ red: !valid, green: valid }">บันทึก</v-btn>
            <v-btn style="margin-left: 15px;" @click="clear">ยกเลิก</v-btn>
          </v-row>
</v-container>
  </v-form>
  </v-card>
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
          console.log(response.data);
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
      if (this.movieadd.employeeId==null || this.movieadd.name ==null|| 
        this.movieadd.moviegenreId ==null || this.movieadd.synopsis==null ||
        this.movieadd.director==null ||
        this.movieadd.actor==null|| this.movieadd.studio==null ||
        this.movieadd.movierateId==null || this.movieadd.length==null  || this.movieadd.systemtypeId==null){
        alert("กรุณากรอกข้อมูลให้ครบถ้วน!");
      } else {
        this.checkName();
      }
    },

    checkName() {
      http
        .get("/movieadd/check/" + this.movieadd.name)
        .then(res => {
          if (!res.data) {
            this.add();
          } else {
            alert("ชื่อหนังเรื่องนี้ถูกใช้แล้ว!");
          }
        })
        .catch(e => {
          console.log(e);
        });
    },
    add() {
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
          alert("บันทึกข้อมูลสำเร็จ");
          window.location.reload()
        })
        .catch(e => {
          console.log(e);
        });
    },
    clear() {
      this.$refs.form.reset();
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