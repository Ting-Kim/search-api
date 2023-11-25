package com.kth.searchapi.application.service;

import com.kth.searchapi.application.repository.RestaurantRepository;
import com.kth.searchapi.domain.Restaurant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantAdminService {

  private final RestaurantRepository restaurantRepository;

  public void addRestaurantDocuments(List<Restaurant> restaurants)  {
    restaurantRepository.saveAll(restaurants);
  }
}
