package com.marklogic.example.support;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by phoehne on 6/19/14.
 */
public class AddProfilingHeader implements HttpRequestInterceptor {
  @Override
  public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
    httpRequest.addHeader("X-ML-Report", "yes");
    Logger logger = Logger.getAnonymousLogger();
    logger.log(Level.INFO, String.format("Handling outbound intercept %s", httpRequest.getRequestLine().getUri()));
  }
}
