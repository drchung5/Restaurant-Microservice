package com.dchung.restaurant.data;


import lombok.Data;


@Data
public class Review {

  protected Review(){}

  public Review(
      String review_text,
      int rating,
      Long restaurant_id) {
    this.review_text = review_text;
    this.rating = rating;
    this.restaurant_id = restaurant_id;
  }

  private Long review_id;

  private String review_text;

  private int rating;

  private long restaurant_id;

}

