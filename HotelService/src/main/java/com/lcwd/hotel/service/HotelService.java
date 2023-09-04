package com.lcwd.hotel.service;

import com.lcwd.hotel.entity.Hotel;

import java.util.List;

public interface HotelService {
    //create

    public Hotel create(Hotel hotel);


    //get All
    public List<Hotel> getAll();

    //get single
    public Hotel getById(String hotelId);

}
