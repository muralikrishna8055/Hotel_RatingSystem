package com.lcwd.hotel.controller;

import com.lcwd.hotel.entity.Hotel;
import com.lcwd.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService service;

    //create
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){

        return  ResponseEntity.status(HttpStatus.CREATED).body(service.create(hotel));

    }

    //show all data
    @GetMapping
    public ResponseEntity<List<Hotel>> showAll(){

        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }


    //show single data
    @PostMapping("/{id}")
    public ResponseEntity<Hotel> getById(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(hotelId));
    }


}
