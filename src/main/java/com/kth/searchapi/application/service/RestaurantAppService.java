package com.kth.searchapi.application.service;

import com.kth.searchapi.application.repository.RestaurantRepository;
import com.kth.searchapi.web.rqrs.RestaurantAutoCompleteKeywordReadRs;
import com.kth.searchapi.web.rqrs.RestaurantsSearchRs;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantAppService {

  private final RestaurantRepository restaurantRepository;

  public RestaurantsSearchRs searchByNameOrDescription(String keyword) {
    return RestaurantsSearchRs.from(restaurantRepository.findByNameOrDescription(keyword));
  }

  public RestaurantAutoCompleteKeywordReadRs readAutoCompleteKeyword(String keyword) {
    return RestaurantAutoCompleteKeywordReadRs.from(
        restaurantRepository.findByNameOrDescriptionPrefix(keyword)
                            .stream()
                            .flatMap(restaurant -> Stream.of(restaurant.getName(),
                                                             restaurant.getDescription()))
                            .collect(Collectors.toList()));
  }
}
