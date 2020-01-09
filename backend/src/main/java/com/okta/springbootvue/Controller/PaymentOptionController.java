package com.okta.springbootvue.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.okta.springbootvue.Entity.PaymentOption;
import com.okta.springbootvue.Repository.*;


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
public class PaymentOptionController {
   
   
@Autowired
private final PaymentOptionRepository paymentOptionRepository;

public PaymentOptionController(PaymentOptionRepository paymentOptionRepository){
    this.paymentOptionRepository = paymentOptionRepository;
}

@GetMapping("/paymentOption")
public Collection<PaymentOption>  paymentOptions(){
    return paymentOptionRepository.findAll().stream().collect(Collectors.toList());
}

@GetMapping("/paymentOption/{id}")
public Optional<PaymentOption> paymentOption(@PathVariable Long id) {
    Optional<PaymentOption> paymentOption = paymentOptionRepository.findById(id);
    return paymentOption;
}



}