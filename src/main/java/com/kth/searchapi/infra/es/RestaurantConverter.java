package com.kth.searchapi.infra.es;

import com.kth.searchapi.domain.Restaurant;

public class RestaurantConverter {

  public static Restaurant fromJpaEntity(RestaurantEsEntity restaurantEsEntity) {
    return Restaurant.of(restaurantEsEntity.getId(),
                         restaurantEsEntity.getName(),
                         restaurantEsEntity.getCategory1(),
                         restaurantEsEntity.getCategory2(),
                         restaurantEsEntity.getCategory3(),
                         restaurantEsEntity.getCity(),
                         restaurantEsEntity.getArea(),
                         restaurantEsEntity.getDescription());
  }

  public static RestaurantEsEntity toEsEntity(Restaurant restaurant) {
    return RestaurantEsEntity.of(restaurant.getId(),
                                 restaurant.getName(),
                                 restaurant.getCategory1(),
                                 restaurant.getCategory2(),
                                 restaurant.getCategory3(),
                                 restaurant.getCity(),
                                 restaurant.getArea(),
                                 restaurant.getDescription());
  }
}
