package com.dchung.restaurant.data.repo;

import com.dchung.restaurant.data.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository
    extends CrudRepository<Restaurant, Long> {

  @Query("select r from Restaurant r where r.cuisine=:cuisine")
  public List<Restaurant> findByRestaurantsByCuisine(@Param("cuisine")String cuisine);

}

