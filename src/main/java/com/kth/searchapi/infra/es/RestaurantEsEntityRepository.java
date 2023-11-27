package com.kth.searchapi.infra.es;

import java.util.List;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RestaurantEsEntityRepository extends ElasticsearchRepository<RestaurantEsEntity, String> {
  @Query("{\"bool\": {\"should\": [{\"match\": {\"name\": \"?0\"}}, {\"match\": {\"description\": \"?0\"}}]}}")
  List<RestaurantEsEntity> findByNameOrDescription(String name);
}
