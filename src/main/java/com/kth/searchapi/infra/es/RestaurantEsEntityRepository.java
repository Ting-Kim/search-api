package com.kth.searchapi.infra.es;

import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RestaurantEsEntityRepository extends ElasticsearchRepository<RestaurantEsEntity, String> {

  List<RestaurantEsEntity> findByName(String name);
}
