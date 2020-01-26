package com.okta.springbootvue;
import com.okta.springbootvue.Entity.*;
import com.okta.springbootvue.Repository.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootRestapiH2DatabaseApplication {

public static void main(String[] args) {
        SpringApplication.run(SpringBootRestapiH2DatabaseApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init(CountryRepository countryRepository , JobsRepository jobsRepository,
	GenderRepository genderRepository,EmployeeRepository employeeRepository,FoodRepository foodRepository, DrinkRepository drinkRepository,
	DubRepository dubRepository, RoomRepository roomRepository, MovieTimeRepository movieTimeRepository,
	MovieGenreRepository moviegenreRepository,MovieRateRepository movierateRepository,SystemTypeRepository systemtypeRepository,
	SeatTypeRepository seatTypeRepository,CardTypeRepository cardTypeRepository,BankRepository bankRepository) {
   
        return args -> {
			Stream.of("ไทย","อินเดีย","จีน","บรูไน","ญี่ปุ่น","เกาหลีใต้").forEach(con -> {
			Country country = new Country();
			country.setCountry(con);
			countryRepository.save(country);
		});

		Stream.of("ชาย","หญิง","LGBT").forEach(gen -> {
			Gender gender = new Gender();
			gender.setGender_name(gen);
			genderRepository.save(gender);
		});

		Stream.of("ตำรวจ","ทหาร","พยาบาล","หมอ","ครู","วิศวะกร","สถาปนิก","โปรแกรมเมอร์","ข้าราชการ","เกษตรกร","นักเรียน","นักศึกษา").forEach(jo -> {
			Jobs jobs = new Jobs();
			jobs.setJob(jo);
			jobsRepository.save(jobs);
		});

		Employee emp1 = new Employee();
		emp1.setUsername("A01");
		emp1.setPassword("1234");
		emp1.setEmployee("ภานุพงศ์  อินทริง");
		employeeRepository.save(emp1);
		
		Employee emp2 = new Employee();
		emp2.setUsername("A02");
		emp2.setPassword("1234");
		emp2.setEmployee("สุบรรชา สวัสดี");
		employeeRepository.save(emp2);

		Employee emp3 = new Employee();
		emp3.setUsername("A03");
		emp3.setPassword("1234");
		emp3.setEmployee("ปิย์รดา แก้วไพฑูรย์");
		employeeRepository.save(emp3);


		Stream.of("ป็อปคอร์นขนาดใหญ่ , 120", "ป็อปคอร์นขนาดใหญ่พิเศษ , 150", "ไส้กรอกแฮม , 45", "ไส้กรอกสไปซี่ , 45" , "ไส้กรอกชีสดีไลท์ , 45" , "นาโชชีส , 179").forEach(name -> {
			Food food = new Food(); // สร้าง Object Customer
			food.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Customer
			if (name == "ป็อปคอร์นขนาดใหญ่ , 120") 
			food.setPrice(120);
			else if (name == "ป็อปคอร์นขนาดใหญ่พิเศษ , 150")
			food.setPrice(150);
			else if (name == "ไส้กรอกแฮม , 45")
			food.setPrice(45);
			else if (name == "ไส้กรอกสไปซี่ , 45")
			food.setPrice(45);
			else if (name == "ไส้กรอกชีสดีไลท์ , 45")
			food.setPrice(45);
			else if (name == "นาโชชีส , 179")
			food.setPrice(179);
			foodRepository.save(food); // บันทึก Objcet ชื่อ Customer
		});

		Stream.of("โค้ก 32 ออนซ์ , 75", "โค้ก 44 ออนซ์ , 85", "น้ำดื่ม , 25", "น้ำแร่ , 30", "โค้ก กระป๋อง , 40", "อิชิตัน กรีนที , 35", "โออิชิจับใจ , 25").forEach(name -> {
			Drink drink = new Drink(); // สร้าง Object Employee
			drink.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Employee
			if (name == "โค้ก 32 ออนซ์ , 75") 
			drink.setPrice(75);
			else if (name == "โค้ก 44 ออนซ์ , 85")
			drink.setPrice(85);
			else if (name == "น้ำดื่ม , 25")
			drink.setPrice(25);
			else if (name == "น้ำแร่ , 30")
			drink.setPrice(30);
			else if (name == "โค้ก กระป๋อง , 40")
			drink.setPrice(40);
			else if (name == "อิชิตัน กรีนที , 35") 
			drink.setPrice(35);
			else if (name == "โออิชิจับใจ , 25")
			drink.setPrice(25);
			drinkRepository.save(drink); // บันทึก Objcet ชื่อ Employee
		});

		Stream.of("Thai", "English").forEach(lan -> {
			Dub dub = new Dub();
			dub.setLangauge(lan);
			dubRepository.save(dub);
		});

		Stream.of("room1", "room2").forEach(ro -> {
			Room room = new Room();
			room.setRoom(ro);
			roomRepository.save(room); 
		});
		Stream.of("11:30", "14:10", "16:50", "19:30", "22:10").forEach(time -> {
			MovieTime movieTime = new MovieTime();
			movieTime.setTime(time);
			movieTimeRepository.save(movieTime); 
		});


		Stream.of("horror","romance","adventure").forEach(gen -> {
			MovieGenre moviegenre = new MovieGenre();
			moviegenre.setMovieGenre(gen);
			moviegenreRepository.save(moviegenre);
		});

		Stream.of("ท-ทั่วไป","น13+","น15+","น18+","ฉ20-").forEach(rate -> {
			MovieRate movierate = new MovieRate();
			movierate.setMovieRate(rate);
			movierateRepository.save(movierate);
		});

		Stream.of("2D","3D","4DX").forEach(systyp -> {
			SystemType systemtype = new SystemType();
			systemtype.setSystemType(systyp);
			systemtypeRepository.save(systemtype);
		});

		Stream.of("A1", "A2", "A3", "B1", "B2", "B3", "B4").forEach(seat -> {
			SeatType seatType = new SeatType(); // สร้าง Object Employee
			seatType.setSeat(seat); // set ชื่อ (name) ให้ Object ชื่อ Employee
			if (seat == "A1") 
			seatType.setPrice(300);
			else if (seat == "A2")
			seatType.setPrice(300);
			else if (seat == "A3")
			seatType.setPrice(300);
			else if (seat == "B1")
			seatType.setPrice(300);
			else if (seat == "B2")
			seatType.setPrice(300);
			else if (seat == "B3")
			seatType.setPrice(300);
			else if (seat == "B4")
			seatType.setPrice(300);
			seatTypeRepository.save(seatType);
		});

		Stream.of("VISA","MasrterCard","JCB").forEach(name -> {
			CardType cardType = new CardType(); // สร้าง Object Employee
			cardType.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Employee
			cardTypeRepository.save(cardType); // บันทึก Objcet ชื่อ Employee
		});
		
		Stream.of("KBank","KTB","SCB").forEach(name -> {
			Bank bank = new Bank(); // สร้าง Object Employee
			bank.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Employee
			bankRepository.save(bank); // บันทึก Objcet ชื่อ Employee
		});


		dubRepository.findAll().forEach(System.out::println); 
		roomRepository.findAll().forEach(System.out::println); 
		movieTimeRepository.findAll().forEach(System.out::println);
	};
	}
}