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
import org.springframework.web.bind.annotation.PathVariable;

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
import com.okta.springbootvue.Entity.User;
import com.okta.springbootvue.Repository.FoodRepository;
import com.okta.springbootvue.Repository.DrinkRepository;
import com.okta.springbootvue.Repository.EmployeeRepository;
import com.okta.springbootvue.Repository.FoodanddrinkRepository;
import com.okta.springbootvue.Repository.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FoodanddrinkController {
    @Autowired
    private FoodanddrinkRepository foodanddrinkRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private DrinkRepository drinkRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;

    FoodanddrinkController(FoodanddrinkRepository foodanddrinkRepository) {
        this.foodanddrinkRepository = foodanddrinkRepository;
    }

    @GetMapping("/foodanddrink")
    public Collection<Foodanddrink> foodanddrinks() {
        return foodanddrinkRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/Foodanddrink/{user_id}/{food_id}/{drink_id}/{employee_id}/{note}")
    public Foodanddrink newFoodanddrink(Foodanddrink newFoodanddrink,
    @PathVariable long food_id,
    @PathVariable long drink_id,
    @PathVariable long employee_id,
    @PathVariable long user_id, 
    @PathVariable String note) {
    //VideoRental newVideoRental = new VideoRental();

    Food food = foodRepository.findById(food_id);
    Drink drink = drinkRepository.findById(drink_id);
    Employee employee = employeeRepository.findById(employee_id);
    User user = userRepository.findById(user_id);

    newFoodanddrink.setFood(food);
    newFoodanddrink.setDrink(drink);
    //newFoodanddrink.setRentDate(new Date());
    newFoodanddrink.setNote(note);
    newFoodanddrink.setEmployee(employee);
    newFoodanddrink.setUser(user);
    


    return foodanddrinkRepository.save(newFoodanddrink); //บันทึก Objcet ชื่อ Foodanddrink
    
    }
}