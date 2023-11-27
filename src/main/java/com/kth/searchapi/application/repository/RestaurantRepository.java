package com.kth.searchapi.application.repository;

import com.kth.searchapi.domain.Restaurant;
import com.kth.searchapi.infra.es.RestaurantEsEntity;
import java.util.List;

public interface RestaurantRepository {

  List<Restaurant> findByNameOrDescription(String keyword);

  List<RestaurantEsEntity> findByNameOrDescriptionPrefix(String keyword);

  void saveAll(List<Restaurant> restaurants);
}
