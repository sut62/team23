package com.okta.springbootvue.Controller;

import com.okta.springbootvue.Entity.Food;
import com.okta.springbootvue.Repository.FoodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FoodController {

    @Autowired
    private final FoodRepository foodRepository;

    public FoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @GetMapping("/food")
    public Collection<Food> foods() {
        return foodRepository.findAll().stream().collect(Collectors.toList());
    }

}