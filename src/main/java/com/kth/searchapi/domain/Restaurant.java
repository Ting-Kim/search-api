package com.kth.searchapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class Restaurant {

  private String id;

  private String name;

  private String category1;

  private String category2;

  private String category3;

  private String city;

  private String area;

  private String description;

}
