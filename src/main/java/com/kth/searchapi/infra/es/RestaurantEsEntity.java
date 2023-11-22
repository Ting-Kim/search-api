package com.kth.searchapi.infra.es;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "i_restaurant", createIndex = false)
@TypeAlias("restaurant")
@Getter
public class RestaurantEsEntity {

  @Id
  private String id;

  @Field(name = "name", type = FieldType.Text)
  private String name;

  @Field(name = "category1", type = FieldType.Keyword)
  private String category1;

  @Field(name = "category2", type = FieldType.Keyword)
  private String category2;

  @Field(name = "category3", type = FieldType.Keyword)
  private String category3;

  @Field(name = "city", type = FieldType.Keyword)
  private String city;

  @Field(name = "area", type = FieldType.Keyword)
  private String area;

  @Field(name = "description", type = FieldType.Text)
  private String description;

}
