package com.okta.springbootvue.Controller;

import com.okta.springbootvue.Entity.Drink;
import com.okta.springbootvue.Repository.DrinkRepository;

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
public class DrinkController {

    @Autowired
    private final DrinkRepository drinkRepository;

    public DrinkController(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @GetMapping("/drink")
    public Collection<Drink> drinks() {
        return drinkRepository.findAll().stream().collect(Collectors.toList());
    }

}