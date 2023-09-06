package com.lcwd.user.service.external.service;

import com.lcwd.user.service.entity.Hotel;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotels/{hotelId}")
   Hotel getHotelByHotelId(@PathVariable String hotelId);

}
