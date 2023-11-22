package com.kth.searchapi.application.service;

import com.kth.searchapi.application.repository.RestaurantRepository;
import com.kth.searchapi.web.rqrs.RestaurantsSearchRs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantAppService {

  private final RestaurantRepository restaurantRepository;

  public RestaurantsSearchRs searchByName(String name) {
    return RestaurantsSearchRs.from(restaurantRepository.findByName(name));
  }
}
