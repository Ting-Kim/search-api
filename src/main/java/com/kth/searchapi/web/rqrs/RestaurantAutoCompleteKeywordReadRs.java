package com.kth.searchapi.web.rqrs;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "from")
public class RestaurantAutoCompleteKeywordReadRs {

  private List<String> autocompleteKeywords;

}
