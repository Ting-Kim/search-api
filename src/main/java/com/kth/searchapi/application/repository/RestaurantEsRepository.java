package com.kth.searchapi.application.repository;

import com.kth.searchapi.domain.Restaurant;
import com.kth.searchapi.infra.es.RestaurantConverter;
import com.kth.searchapi.infra.es.RestaurantEsEntityRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RestaurantEsRepository implements RestaurantRepository {

  private final RestaurantEsEntityRepository restaurantEsEntityRepository;

  @Override
  public List<Restaurant> findByName(String name) {
    return restaurantEsEntityRepository.findByName(name)
                                       .stream()
                                       .map(RestaurantConverter::fromJpaEntity)
                                       .collect(Collectors.toList());
  }
}
