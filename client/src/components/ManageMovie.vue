<template>
  <div class="main">
  <v-form>
    <v-container fluid>

      <center>
        <h1>จัดการรอบฉายภาพยนตร์</h1>
      </center>

      <v-row align="center">
        <label>ภาพยนตร์</label>
        <v-col class="d-flex" cols="12">
          <v-select
            label="เลือกภาพยนตร์"
            outlined
            v-model="managemovie.movie_id"
            :items="movieAdd"
            item-text="name"
            item-value="id"
            :rules="[(v) => !!v || 'Item is required']"
            required
            solo
          ></v-select>
        </v-col>

        <label>ภาษา</label>
        <v-col class="d-flex" cols="12">
          <v-select
            label="เลือกภาษา"
            outlined
            v-model="managemovie.dub_id"
            :items="dub"
            item-text="langauge"
            item-value="idL"
            :rules="[(v) => !!v || 'Item is required']"
            required
            solo
          ></v-select>
        </v-col>

        <label>ห้องฉาย</label>
        <v-col class="d-flex" cols="12">
          <v-select
            label="เลือกห้องฉายภาพยนตร์"
            outlined
            v-model="managemovie.room_id"
            :items="room"
            item-text="room"
            item-value="idR"
            :rules="[(v) => !!v || 'Item is required']"
            required
            solo
          ></v-select>
        </v-col>

          <v-col cols="12" lg="6">
            <label>วันที่ฉาย:</label>
            <v-menu
              ref="menu1"
              v-model="menu1"
              :close-on-content-click="false"
              transition="scale-transition"
              offset-y
              full-width
              max-width="290px"
              min-width="290px"
            >
            <template v-slot:activator="{ on }">
              <v-text-field
                v-model="dateFormatted"
                label="กรุณากรอกวันที่"
                @blur="date = parseDate(dateFormatted)"
                v-on="on"
              ></v-text-field>
            </template>
                <v-date-picker v-model="date" no-title @input="menu1 = false"></v-date-picker>
            </v-menu>
          </v-col>

        <label>เวลาฉาย</label>
        <v-col class="d-flex" cols="5">
          <v-select
            label="--:--"
            outlined
            v-model="managemovie.movie_time"
            :items="movieTime"
            item-text="time"
            item-value="idT"
            :rules="[(v) => !!v || 'Item is required']"
            required
            solo
          ></v-select>
          </v-col>
          
          <v-text-field 
          v-model="notes"
          label="หมายเหตุ" 
          solo>
          </v-text-field>
        </v-row>
 
          <v-row justify="center">
        <v-col class="d-flex" cols="50" sm="30">
          <v-bottom-sheet v-model="alwayselect">
            <template v-slot:activator="{ on }">
              <v-btn @click="saveManageMovie" :class="{ green: !valid, blue: valid }">บันทึก</v-btn>
            </template>
            <v-sheet class="text-center" height="200px">
              <div v-if="checkSave==true" class="py-3" cols="12" lg="6" >
              บันทึกข้อมูลเสร็จสิ้น
              </div>
              <div v-if="checkSave==false" class="py-3" cols="12" lg="6">กรุณากรอกข้อมูลให้ครบ</div>
              <v-btn color="red" text @click="alwayselect = false">Close</v-btn>
            </v-sheet>
          </v-bottom-sheet>
            <v-btn color="#FF6262" width="100" router-link to="/showmm" >แสดงข้อมูล</v-btn>
        </v-col>
        <br />
        <br />
      </v-row>
      <br />
    </v-container>
    </v-form>    
  </div>
</template>


<script>
import http from "../http-common";
export default {
  data () {
    return {
    movieAdd: [],
    dub: [],
    room: [],
    movieTime: [],
    notes: null,
    date: new Date().toISOString().substr(0, 10),
    dateFormatted: this.formatDate(new Date().toISOString().substr(0, 10)),
    menu1: false,  
    checkSave: false,
    alwayselect: false,
    valid: false,
    managemovie: [
      {
      movie_id: '',
      dub_id: '',
      room_id: '',
      movie_time: '',
      
      }
    ]
    };
  },
  computed: {
    computedDateFormatted() {
      return this.formatDate(this.date);
    }
  },
  watch: {
    date() {
      this.dateFormatted = this.formatDate(this.date);
    }
  },
  methods: {
    /* eslint-disable no-console */
    getMovieAdd() {
      http
        .get("/movieadd")
        .then(response => {
          this.movieAdd = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getDub() {
      http
        .get("/language")
        .then(response => {
          this.dub = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getRoom() {
      http
        .get("/theater")
        .then(response => {
          this.room = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getTime() {
      http
        .get("/time")
        .then(response => {
          this.movieTime = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    /*checkData(){
      http
        .get("/managemovie/checkData/" + this.managemovie.room_id +"/"+this.managemovie.movie_time +"/" +this.date)
        .then(res => {
          if (res.data == null) {
            this.saveManageMovie();
          } else {
            alert("มีรอบการฉายอยู่แล้ว");
          }
        })
        .catch(e => {
          console.log(e);
        });
    },*/
    saveManageMovie() {
      http
        .post(
          "/managemovie/" +
            this.managemovie.movie_id +
            "/" +
            this.managemovie.dub_id +
            "/" +
            this.managemovie.room_id +
            "/" + 
            this.managemovie.movie_time +
            "/" + 
            this.notes+
            "/" +
            this.date

        )
        .then(response => {
          console.log(response);
         this.alwayselect = true;
          this.checkSave = true;
        })
        .catch(e => {
          this.alwayselect = true;
          this.checkSave = false;
  
          console.log(e);
        });
    
  },
  
  formatDate(date) {
      if (!date) return null;
      const [year, month, day] = date.split("-");
      return `${month}/${day}/${year}`;
    },
    parseDate(date) {
      if (!date) return null;
      const [month, day, year] = date.split("/");
      return `${year}-${month.padStart(2, "0")}-${day.padStart(2, "0")}`;
    }
},
  mounted() {
    this.getMovieAdd();
    this.getDub();
    this.getRoom();
    this.getTime();
  }
};
</script>
<style scoped>
.main {
  margin: 100px 300px;
}
.center {
  margin: 0px 160px;
}
</style>