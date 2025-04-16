package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.MDC;

@Controller("/non-blocking")
public class NonBlockingController {

  @Get
  public String get() {
    throw new RuntimeException(MDC.get("trace"));
  }
}
