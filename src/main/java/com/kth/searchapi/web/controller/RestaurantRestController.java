package com.kth.searchapi.web.controller;

import com.kth.searchapi.application.service.RestaurantAppService;
import com.kth.searchapi.web.common.HttpResponseMessage;
import io.swagger.annotations.ApiOperation;
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

  @ApiOperation(value = "식당 이름, 설명으로 검색")
  @GetMapping
  public HttpResponseMessage searchByNameOrDescription(@RequestParam("keyword") String keyword) {
    return HttpResponseMessage.from(restaurantAppService.searchByNameOrDescription(keyword));
  }

  @ApiOperation(value = "자동완성 키워드 조회 API")
  @GetMapping("/autocomplete")
  public HttpResponseMessage readAutoCompleteKeyword(@RequestParam("keyword") String keyword) {
    return HttpResponseMessage.from(restaurantAppService.readAutoCompleteKeyword(keyword));
  }

  @ApiOperation(value = "연관 검색어(지역) 조회 API")
  @GetMapping("/relative-areas")
  public HttpResponseMessage readRelativeAreas(@RequestParam("keyword") String keyword) {
    return HttpResponseMessage.from(restaurantAppService.readRelativeAreas(keyword));
  }

}
