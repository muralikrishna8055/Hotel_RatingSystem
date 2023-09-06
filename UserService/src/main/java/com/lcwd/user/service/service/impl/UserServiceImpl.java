package com.lcwd.user.service.service.impl;

import com.lcwd.user.service.entity.Hotel;
import com.lcwd.user.service.entity.Rating;
import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.service.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.spi.ResourceBundleControlProvider;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        //generate a unique id
       String randomUserId = UUID.randomUUID().toString();
       user.setUserId(randomUserId);
        return repository.save(user);
    }

    @Override
    public List<User> showUser() {

       // return repository.findAll();
        List<User> user=repository.findAll();

        List<User> collect = user.stream().map(user1 -> {
            ArrayList<Rating> ratingOfUser =
                    restTemplate.getForObject("http://RATING-SERVICE/rating/users/" + user1.getUserId(), ArrayList.class);
            user1.setRatings(ratingOfUser);
            return user1;
        }).collect(Collectors.toList());

       return collect;
    }

    @Override
    public User getUserById(String userId) {
        // fetch data from db
       User user= repository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("Requested Resource Is Not Available"+userId));
        //fetch rating service data from here

        //http://localhost:8083/rating/users/063abb34-e100-42d5-9f67-02038c92a2c2
        // below method will convert it to linked hash set
        // ArrayList <Rating> ratingOfUser=
        // restTemplate.getForObject("http://localhost:8083/rating/users/"+user.getUserId(), ArrayList.class);

        Rating[] ratingOfUser= restTemplate.getForObject("http://RATING-SERVICE/rating/users/"+user.getUserId(), Rating[].class);
        logger.info("{} ",ratingOfUser);

        //converting rating array to list
        List<Rating> ratingList1 = Arrays.stream(ratingOfUser).toList();

        List<Rating> ratingList=  ratingList1.stream().map(rating -> {

          //http://localhost:8082/hotels/f75314a1-66f4-4110-b560-1bda8090ac63
          //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
          //set data from response entity
          //Hotel hotel=forEntity.getBody();
         // logger.info("{}",forEntity.getStatusCode());


            Hotel hotel= hotelService.getHotelByHotelId(rating.getHotelId());
          //set data to rating
          rating.setHotel(hotel);

          return rating;

          //converting to a new list
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
       return user;
    }
}
