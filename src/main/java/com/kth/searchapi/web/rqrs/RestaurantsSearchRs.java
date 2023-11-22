package com.kth.searchapi.web.rqrs;

import com.kth.searchapi.domain.Restaurant;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantsSearchRs {

  private List<RestaurantSearchRs> restaurants;

  public static RestaurantsSearchRs from(List<Restaurant> restaurants) {
    return new RestaurantsSearchRs(
        restaurants.stream()
                   .map(r -> new RestaurantSearchRs(
                       r.getId(),
                       r.getName(),
                       r.getCategory1(),
                       r.getCategory2(),
                       r.getCategory3(),
                       r.getCity(),
                       r.getArea(),
                       r.getDescription()))
                   .collect(Collectors.toList()));
  }

  @Getter
  @AllArgsConstructor
  static class RestaurantSearchRs {

    private String id;

    private String name;

    private String category1;

    private String category2;

    private String category3;

    private String city;

    private String area;

    private String description;

  }
}
