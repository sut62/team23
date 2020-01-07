package com.okta.springbootvue.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import com.okta.springbootvue.Entity.Room;
import com.okta.springbootvue.Repository.RoomRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RoomController {

    @Autowired
    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/theater")
    public List<Room> Rooms() {
        return roomRepository.findAll().stream().collect(Collectors.toList());
    }

}