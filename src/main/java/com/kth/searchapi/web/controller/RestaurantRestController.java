package com.kth.searchapi.web.controller;

import com.kth.searchapi.application.service.RestaurantAppService;
import com.kth.searchapi.web.common.HttpResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantRestController {

  private final RestaurantAppService restaurantAppService;

  @GetMapping
  public HttpResponseMessage searchByName(@RequestParam("name") String name) {
    return HttpResponseMessage.from(restaurantAppService.searchByName(name));
  }
}
