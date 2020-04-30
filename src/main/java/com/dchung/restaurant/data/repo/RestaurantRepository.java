package com.dchung.restaurant.data.repo;

import com.dchung.restaurant.data.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository
    extends CrudRepository<Restaurant, Long> {

  @Query("select r from Restaurant r where r.cuisine_id=:cuisine_id")
  public List<Restaurant> findByRestaurantsByCuisine(@Param("cuisine_id")Long cuisine_id);

}

