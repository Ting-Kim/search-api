package com.kth.searchapi.infra.es;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RestaurantEsEntityRepository extends ElasticsearchRepository<RestaurantEsEntity, String> {
  @Query("{\"bool\": {\"should\": [{\"match\": {\"name\": \"?0\"}}, {\"match\": {\"description\": \"?0\"}}]}}")
  List<RestaurantEsEntity> findByNameOrDescription(String keyword);

  @Query("{\"bool\": {\"should\": [{\"prefix\": {\"name\": {\"value\": \"?0\"}}}, {\"prefix\": {\"description\": {\"value\": \"?0\"}}}]}}")
  Page<RestaurantEsEntity> findByNameOrDescriptionPrefix(String keyword, PageRequest pageRequest);
}
