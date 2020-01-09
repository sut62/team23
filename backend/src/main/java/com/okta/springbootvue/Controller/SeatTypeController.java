package com.okta.springbootvue.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;


import com.okta.springbootvue.Entity.SeatType;
import com.okta.springbootvue.Repository.SeatTypeRepository;


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class SeatTypeController {

    @Autowired
    private final SeatTypeRepository seatTypeRepository;

    public SeatTypeController(SeatTypeRepository seatTypeRepository) {
        this.seatTypeRepository = seatTypeRepository;
    }

    @GetMapping("/seatType")
    public Collection<SeatType> SeatTypes() {
        return seatTypeRepository.findAll().stream().collect(Collectors.toList());
    }

}