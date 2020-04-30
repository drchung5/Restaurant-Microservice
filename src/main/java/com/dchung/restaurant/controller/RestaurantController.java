package com.dchung.restaurant.controller;

import com.dchung.restaurant.data.BestRestaurant;
import com.dchung.restaurant.data.Restaurant;
import com.dchung.restaurant.data.Review;
import com.dchung.restaurant.data.repo.RestaurantRepository;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {

  @Autowired
  private RestaurantRepository restaurantRepository;

  @GetMapping(value= "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Restaurant> getRestaurant(@PathVariable("id") Long id) {
    Optional<Restaurant> restaurantWrapper = restaurantRepository.findById(id);
    if(!restaurantWrapper.isPresent()) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(restaurantWrapper.get(),HttpStatus.OK);
  }

  @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getAllRestaurants() {
    Iterable<Restaurant> restaurants = restaurantRepository.findAll();
    if( Iterables.size(restaurants) == 0 ) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(restaurants,HttpStatus.OK);
  }

  @GetMapping(value="/best", produces= MediaType.APPLICATION_JSON_VALUE)
  public BestRestaurant getBestRestaurantByCuisine(@RequestParam("cuisine_id") Long id) {

    RestTemplate restTemplate = new RestTemplate();

    List<Restaurant> restaurants = restaurantRepository.findByRestaurantsByCuisine(id);

    BestRestaurant bestRestaurant = new BestRestaurant();

    for( Restaurant restaurant : restaurants ) {

      System.err.println("********************");

      ResponseEntity<Review[]> response = null;
      try {
        response =
            restTemplate.getForEntity(
                "http://localhost:9002/reviews/restaurant/" + restaurant.getRestaurant_id(),
                Review[].class);
      } catch (Exception e) {
        break;
      }

      Review[] reviews = response.getBody();

      int sumRating = 0;

      for( Review review : reviews ) {
        sumRating += review.getRating();
        System.out.println( restaurant.getName() + "  rating : " + review.getRating() );
      }

      float avgRating = 0;
      if( sumRating > 0 ) {
        avgRating = (float)sumRating / (float)reviews.length;
      }

      if( avgRating > bestRestaurant.getAvgReview() ) {
        bestRestaurant.setAvgReview( avgRating );
        bestRestaurant.setRestaurant_id( restaurant.getRestaurant_id() );
      }

    }

    return bestRestaurant;
  }

  @PostMapping(consumes = "application/json")
  public ResponseEntity<Void> insertRestaurant(
      @RequestBody Restaurant restaurant,
      UriComponentsBuilder builder) {
    Restaurant r = restaurantRepository.save(restaurant);
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(builder.path("/restaurants/{id}").
        buildAndExpand(r.getRestaurant_id()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

}
