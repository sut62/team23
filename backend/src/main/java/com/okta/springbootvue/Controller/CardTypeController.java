package com.okta.springbootvue.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.okta.springbootvue.Entity.CardType;
import com.okta.springbootvue.Repository.*;


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
public class CardTypeController {
   
   
@Autowired
private final CardTypeRepository cardTypeRepository;

public CardTypeController(CardTypeRepository cardTypeRepository){
    this.cardTypeRepository = cardTypeRepository;
}

@GetMapping("/cardType")
public Collection<CardType>  cardTypes(){
    return cardTypeRepository.findAll().stream().collect(Collectors.toList());
}

@GetMapping("/cardType/{id}")
public Optional<CardType> cardType(@PathVariable Long id) {
    Optional<CardType> cardType = cardTypeRepository.findById(id);
    return cardType;
}



}