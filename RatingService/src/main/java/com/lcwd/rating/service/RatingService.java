package com.lcwd.rating.service;

import com.lcwd.rating.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RatingService {

    //Create
    Rating saveRating(Rating rating);

    //get all rating
    List<Rating> getAllRatings();

    //get all by userId
    List<Rating>getRatingByUserId(String userid);

    //get all by hotelId
    List<Rating>getRatingByHotelId(String hotelId);


}
