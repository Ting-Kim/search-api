package com.kth.searchapi.application.repository;

import com.kth.searchapi.domain.Restaurant;
import java.util.List;

public interface RestaurantRepository {

  List<Restaurant> findByNameOrDescription(String keyword);

  void saveAll(List<Restaurant> restaurants);
}
