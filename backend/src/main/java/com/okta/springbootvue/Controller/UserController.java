package com.okta.springbootvue.Controller;

import com.okta.springbootvue.Entity.Country;
import com.okta.springbootvue.Entity.User;
import com.okta.springbootvue.Entity.Gender;
import com.okta.springbootvue.Entity.Jobs;
import com.okta.springbootvue.Repository.CountryRepository;
import com.okta.springbootvue.Repository.UserRepository;
import com.okta.springbootvue.Repository.JobsRepository;
import com.okta.springbootvue.Repository.GenderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private JobsRepository jobsRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public Collection<User> user() {
        return userRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/user/check/{username}")
    public User checkUser(@PathVariable String username){
        return userRepository.findByUsername(username);

    }

    @GetMapping("/user/{id}")
    public Optional<User> Users(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }
    @GetMapping("/user/{username}/{password}")
    public User Login(@PathVariable String username, @PathVariable String password){
        return userRepository.findByUsernameAndPassword(username,password);}

    @PostMapping("/user/{username}/{password}/{gender_id}/{name}/{job_id}/{age}/{country_id}/{tel}")
    public User newUser(User newUser,
                        @PathVariable String username,
                        @PathVariable String password,
                        @PathVariable String name,
                        @PathVariable int age,
                        @PathVariable long gender_id,
                        @PathVariable long job_id,
                        @PathVariable long country_id,
                            @PathVariable String tel) {

                                Gender gender = genderRepository.findById(gender_id);
                                Country country = countryRepository.findById(country_id);
                                Jobs jobs = jobsRepository.findById(job_id);
                                User user = new User();
                                user.setUsername(username);
                                user.setPassword(password);
                                user.setName(name);
                                user.setAge(age);
                                user.setGender(gender);
                                user.setJobs(jobs);
                                user.setCountry(country);
                                user.setTel(tel);
                                userRepository.save(user);
              
                                return userRepository.save(user);
    }
}