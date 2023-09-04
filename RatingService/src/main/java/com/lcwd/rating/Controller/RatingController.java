package com.lcwd.rating.Controller;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")

public class RatingController {
    @Autowired
    private RatingService service;

    //create Rating
    @PostMapping
    public ResponseEntity<Rating> saveRating(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveRating(rating));
    }

    //get all rating
    @GetMapping
    public ResponseEntity<List<Rating>>getAllRating(){
        return ResponseEntity.ok(service.getAllRatings());
    }

    //get by user Id
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>>getByUserId(@PathVariable String userId){
        return ResponseEntity.ok(service.getRatingByUserId(userId));
    }

    //get by hotel id
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>>getByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(service.getRatingByHotelId(hotelId));
    }

}
