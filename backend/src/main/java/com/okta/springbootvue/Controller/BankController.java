package com.okta.springbootvue.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.okta.springbootvue.Entity.Bank;
import com.okta.springbootvue.Repository.*;


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
public class BankController {
   
   
@Autowired
private final BankRepository bankRepository;

public BankController(BankRepository bankRepository){
this.bankRepository = bankRepository;
}

@GetMapping("/bank")
public Collection<Bank>bank(){
    return bankRepository.findAll().stream().collect(Collectors.toList());
}

@GetMapping("/bank/{id}")
public Optional<Bank> bank(@PathVariable Long id) {
    Optional<Bank> bank = bankRepository.findById(id);
    return bank;
}


}