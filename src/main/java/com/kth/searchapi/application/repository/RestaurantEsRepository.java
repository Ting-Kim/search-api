package com.kth.searchapi.application.repository;

import com.kth.searchapi.domain.Restaurant;
import com.kth.searchapi.infra.es.RestaurantConverter;
import com.kth.searchapi.infra.es.RestaurantEsEntity;
import com.kth.searchapi.infra.es.RestaurantEsEntityRepository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RestaurantEsRepository implements RestaurantRepository {

  private final RestaurantEsEntityRepository restaurantEsEntityRepository;
  private final ElasticsearchRestTemplate elasticsearchRestTemplate;

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

  @Override
  public List<String> findAreasByAggregatingKeyword(String keyword) {
    String aggregationNames = "area_counts";

    NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
        .addAggregation(
            AggregationBuilders.terms(aggregationNames)
                               .size(10)
                               .field("area")
                               .order(BucketOrder.count(false)))
        .withMaxResults(0)
        .withQuery(
            QueryBuilders.matchQuery("name", keyword))
        .withQuery(
            QueryBuilders.matchQuery("description", keyword))
        .build();

    SearchHits<RestaurantEsEntity> search = elasticsearchRestTemplate.search(searchQuery,
                                                                             RestaurantEsEntity.class);

    Terms areaCounts = Objects.requireNonNull(search.getAggregations())
                              .get(aggregationNames);

    return areaCounts.getBuckets()
                     .stream()
                     .map(Bucket::getKeyAsString)
                     .collect(Collectors.toList());
  }
}
