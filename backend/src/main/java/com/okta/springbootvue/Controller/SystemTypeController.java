package com.okta.springbootvue.Controller;



import com.okta.springbootvue.Entity.SystemType;
import com.okta.springbootvue.Repository.SystemTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class SystemTypeController {

    @Autowired
    private SystemTypeRepository systemtypeRepository;

    public SystemTypeController(SystemTypeRepository systemtypeRepository) {
        this.systemtypeRepository = systemtypeRepository;
    }

    @GetMapping("/systemtype")
    public Collection<SystemType> SystemTypes() {
        return systemtypeRepository.findAll().stream().collect(Collectors.toList());
    }
    
}