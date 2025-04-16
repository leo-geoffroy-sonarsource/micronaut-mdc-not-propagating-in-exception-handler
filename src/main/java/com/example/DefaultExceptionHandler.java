package com.example;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import org.slf4j.MDC;

@Produces
@Singleton
@Requires(classes = {Exception.class, ExceptionHandler.class})
public class DefaultExceptionHandler implements ExceptionHandler<Exception, HttpResponse<String>> {
  @Override
  public HttpResponse<String> handle(HttpRequest request, Exception e) {

    return HttpResponse.serverError("""
      {
       "controllerTraceId": "%s",
       "exceptionHandlerTraceId": "%s",
      }
      """.formatted(e.getMessage(), MDC.get("trace")));
  }
}
