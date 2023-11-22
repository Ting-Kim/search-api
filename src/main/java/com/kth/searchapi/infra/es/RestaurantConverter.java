package com.kth.searchapi.infra.es;

import com.kth.searchapi.domain.Restaurant;

public class RestaurantConverter {

  public static Restaurant fromJpaEntity(RestaurantEsEntity restaurantJpaEntity) {
    return Restaurant.of(restaurantJpaEntity.getId(),
                         restaurantJpaEntity.getName(),
                         restaurantJpaEntity.getCategory1(),
                         restaurantJpaEntity.getCategory2(),
                         restaurantJpaEntity.getCategory3(),
                         restaurantJpaEntity.getCity(),
                         restaurantJpaEntity.getArea(),
                         restaurantJpaEntity.getDescription());
  }
}
