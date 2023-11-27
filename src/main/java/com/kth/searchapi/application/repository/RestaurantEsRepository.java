package com.kth.searchapi.application.repository;

import com.kth.searchapi.domain.Restaurant;
import com.kth.searchapi.infra.es.RestaurantConverter;
import com.kth.searchapi.infra.es.RestaurantEsEntity;
import com.kth.searchapi.infra.es.RestaurantEsEntityRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RestaurantEsRepository implements RestaurantRepository {

  private final RestaurantEsEntityRepository restaurantEsEntityRepository;

  @Override
  public List<Restaurant> findByNameOrDescription(String keyword) {
    return restaurantEsEntityRepository.findByNameOrDescription(keyword)
                                       .stream()
                                       .map(RestaurantConverter::fromJpaEntity)
                                       .collect(Collectors.toList());
  }

  @Override
  public List<RestaurantEsEntity> findByNameOrDescriptionPrefix(String keyword) {
    PageRequest pageRequest = PageRequest.of(0, 5);
    return restaurantEsEntityRepository.findByNameOrDescriptionPrefix(keyword, pageRequest)
                                       .getContent();
  }

  @Override
  public void saveAll(List<Restaurant> restaurants) {
    restaurantEsEntityRepository.saveAll(restaurants.stream()
                                                    .map(RestaurantConverter::toEsEntity)
                                                    .collect(Collectors.toList()));
  }
}
