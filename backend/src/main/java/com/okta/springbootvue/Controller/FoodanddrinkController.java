package com.okta.springbootvue.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import com.okta.springbootvue.Entity.Food;
import com.okta.springbootvue.Entity.Drink;
import com.okta.springbootvue.Entity.Employee;
import com.okta.springbootvue.Entity.Foodanddrink;
import com.okta.springbootvue.Repository.FoodRepository;
import com.okta.springbootvue.Repository.DrinkRepository;
import com.okta.springbootvue.Repository.EmployeeRepository;
import com.okta.springbootvue.Repository.FoodanddrinkRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FoodanddrinkController {
    @Autowired
    private final FoodanddrinkRepository foodanddrinkRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private DrinkRepository drinkRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    FoodanddrinkController(FoodanddrinkRepository foodanddrinkRepository) {
        this.foodanddrinkRepository = foodanddrinkRepository;
    }

    @GetMapping("/foodanddrink")
    public Collection<Foodanddrink> foodanddrink() {
        return foodanddrinkRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/videoRental/{customer_id}/{employee_id}/{video_id}/{rentalType}")
    public Foodanddrink newFoodanddrink(Foodanddrink newFoodanddrink,
    @PathVariable long food_id,
    @PathVariable long drink_id,
    @PathVariable long employee_id) {
    //VideoRental newVideoRental = new VideoRental();

    Food food = foodRepository.findById(food_id);
    Drink drink = drinkRepository.findById(drink_id);
    Employee employee = employeeRepository.findById(employee_id);

    newFoodanddrink.setFood(food);
    newFoodanddrink.setDrink(drink);
    //newFoodanddrink.setRentDate(new Date());
    newFoodanddrink.setEmployee(employee);

    return foodanddrinkRepository.save(newFoodanddrink); //บันทึก Objcet ชื่อ Foodanddrink
    
    }
}