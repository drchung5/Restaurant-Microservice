package com.dchung.restaurant.data;

import lombok.Data;

@Data
public class BestRestaurant {

  private Long restaurant_id;

  private float avgReview;

  public BestRestaurant() {
    restaurant_id = null;
    avgReview = 0.0f;
  }

}
