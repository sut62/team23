package com.okta.springbootvue.Controller;



import com.okta.springbootvue.Entity.Jobs;
import com.okta.springbootvue.Repository.JobsRepository;

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
public class JobsController {

    @Autowired
    private JobsRepository jobsRepository;

    public JobsController(JobsRepository jobsRepository) {
        this.jobsRepository = jobsRepository;
    }

    @GetMapping("/jobs")
    public Collection<Jobs> jobs() {
        return jobsRepository.findAll().stream().collect(Collectors.toList());
    }

}