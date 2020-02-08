<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Popcorn and Drink</h1>
      </v-flex>
    </v-layout>

     <v-row justify="center">
     <v-col cols="4">
      <v-form v-model="valid" ref="form">
        <dir>
        
          <v-row justify="center">
          <v-text> "เฉพาะผู้ที่เป็นสมาชิกเท่านั้น" </v-text>
            <v-col cols="10">
              <v-text-field label="User ID"
              outlined
                v-model="foodanddrink.userId"
                :rules="[(v) => !!v || 'Item is required']"
                required>
              </v-text-field>
              <p v-if="userCheck != ''">User Name : {{userName}}</p>
            </v-col>
            <v-col cols="2">
              <div class="my-2">
                <v-btn @click="findUser" depressed large color="primary">Search</v-btn>
              </div>
            </v-col>
          </v-row>

            
        <div v-if="userCheck">
          <v-row>
            <v-col cols="12">
              <v-select label="อาหาร"
              outlined
                  v-model="foodanddrink.food"
                  :items="food"
                  item-text="name"
                  item-value="id">
              </v-select>
            </v-col>
          </v-row>
 
          <v-row>
            <v-col cols="12">
              <v-select label="เครื่องดื่ม"
              outlined
                  v-model="foodanddrink.drink"
                  :items="drink"
                  item-text="name"
                  item-value="id">
              </v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-select label="พนักงาน"
              outlined
                  v-model="foodanddrink.employee"
                  :items="Employee"
                  item-text="employee"
                  item-value="employee_id">
              </v-select>
            </v-col>
          </v-row>
              <v-row>
                <v-col cols="12">
                  <v-text-field label="หมายเหตุ"
                  outlined
                    v-model="notes"
                    required>
                  </v-text-field>
                </v-col>
              </v-row>
           
              <v-row justify="center">
                <v-btn @click="saveFoodanddrink" :class="{ red: !valid, green: valid }">Submit</v-btn>
                <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
                <v-btn style="margin-left: 15px;" @click="Check" :class="font" >ดูข้อมูลการขาย</v-btn>
              </v-row>

              <div v-if = "Savedone == true">
                <br>
              <v-alert prominent type="success">
                <v-row align="center">
                  <v-col class="grow">
                      บันทึกข้อมูลสำเร็จ<br>
                  </v-col>
                  <v-col class="shrink">
                   <v-btn @click="CheckBuy" :class="font" >ตรวจสอบ</v-btn>
                   </v-col>
                 </v-row>
              </v-alert>
          </div>
          <br>
          <div v-if = "Alert == true"> <v-alert type="error">โปรดระบุข้อความให้ครบ</v-alert></div>
            </div>
          </dir>
        </v-form>
      </v-col>
    </v-row>
  </v-container> 
</template>

<script>
import http from "../http-common";
export default {
  name: "foodanddrink",
  data() {
    return {
      foodanddrink: {
      food : null,
      drink : null,
      notes : null,
      employee : null,
      userId : null,
      },
      valid: false,
      userCheck: false,
      //clickSave : false,
      //fdCheck : false,
      userName: "",
      food:[],
      drink:[],
      Employee:[],
    Savedone: false,
    Enternull: false,
    Alert: false,

    };
  },
  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล food ใส่ combobox
    getFood() {
      http
        .get("/food")
        .then(response => {
          this.food = response.data;
          console.log(response.data);
        })
        .catch(e => {
        console.log(e);
        });
    },
    // ดึงข้อมูล drink ใส่ combobox
    getDrink() {
      http
        .get("/drink")
        .then(response => {
          this.drink = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล employee ใส่ combobox
    getEmployee() {
      http
        .get("/employee")
        .then(response => {
          this.Employee = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // function ค้นหาผู้ใช้บริการด้วย ID
    findUser() {
      http
        .get("/user/" + this.foodanddrink.userId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.userName = response.data.name;
            this.userCheck = response.status;
          } else {
            this.clear()
          }          
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    // function เมื่อกดปุ่ม บันทึก
    saveFoodanddrink() {
      if(this.foodanddrink.userId==null||this.foodanddrink.food==null||this.foodanddrink.drink==null||this.foodanddrink.employee==null||this.notes==null){
            this.Alert = true;
            
      }else{
      
      http
        .post(
          "/Foodanddrink/" +
          this.foodanddrink.userId +
          "/" +
          this.foodanddrink.food+
          "/" + 
          this.foodanddrink.drink +
          "/" + 
          this.foodanddrink.employee +
          "/" +
          this.notes
         // this.foodanddrink,
        )
        .then(response => {
          //this.clickSave = true; 
          console.log(response);
          //this.$router.push("/foodanddrinkData");
          //alert("ทำการซื้อสำเร็จ");
          this.Savedone = true;
          this.clear();
        })
        .catch(e => {
          console.log(e);
        });
      //this.submitted = true;
      }
    },
    CheckBuy(){
        this.$router.push("/foodanddrinkData");
    },
    clear() {
    this.$refs.form.reset();
    this.customerCheck = false;
    },
    Check(){
        this.$router.push("/foodanddrinkData");
    },
    refreshList() {
      this.getFood();
      this.getDrink();
      this.getEmployee();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getFood();
    this.getDrink();
    this.getEmployee();
  }
};
</script>