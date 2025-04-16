package com.example;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.specification.RequestSpecification;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

@MicronautTest
class TestMdc {

  @Inject
  private RequestSpecification spec;


  @Test
  void testNonBlockingPropagatingTrace(){
    spec.given()
      .contentType("application/json")
      .get("/non-blocking")
      .then()
      .body("controllerTraceId", equalTo("1234"))
      .body("exceptionHandlerTraceId", equalTo("1234"));
  }

  @Test
  void testBlockingNotPropagatingMDCTrace(){
    spec.given()
      .contentType("application/json")
      .get("/blocking")
      .then()
      .body("controllerTraceId", equalTo("1234"))
      //MDC trace id is not propagated to the exception handler
      .body("exceptionHandlerTraceId", equalTo("null"));
  }
}
