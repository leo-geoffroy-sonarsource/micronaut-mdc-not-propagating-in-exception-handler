package com.example;

import io.micronaut.context.propagation.slf4j.MdcPropagationContext;
import io.micronaut.core.propagation.MutablePropagatedContext;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.RequestFilter;
import io.micronaut.http.annotation.ResponseFilter;
import io.micronaut.http.annotation.ServerFilter;
import org.slf4j.MDC;

import static io.micronaut.http.annotation.Filter.MATCH_ALL_PATTERN;

@ServerFilter(MATCH_ALL_PATTERN)
public class MdcFilter {

  @RequestFilter
  public void requestFilter(MutablePropagatedContext mutablePropagatedContext) {

    String traceId = "1234";
    MDC.put("trace", traceId);

    mutablePropagatedContext.add(new MdcPropagationContext());
    System.out.println("MDC TRACE ID: " + traceId);
  }

  @ResponseFilter
  public void addTraceIdHeader(MutableHttpResponse<?> response) {
    MDC.remove("trace");
  }
}
