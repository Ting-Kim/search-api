package com.kth.searchapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class Restaurant {

  private String id;

  private String name;

  private String category1;

  private String category2;

  private String category3;

  private String city;

  private String area;

  private String description;

  public static Restaurant create(
      String name, String category1, String category2, String category3, String city, String area,
      String description
  ) {
    return new Restaurant(null, name, category1, category2, category3, city, area, description);
  }
}
