package com.kth.searchapi.web.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "from")
public class HttpResponseMessage {

  private Object content;

}
