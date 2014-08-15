package com.marklogic.example.support;

import com.marklogic.example.services.WatchUriService;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by phoehne on 6/19/14.
 */
public class AddProfilingHeader implements HttpRequestInterceptor {
  static Logger logger = Logger.getLogger("AddProfilingHeader");

  @Autowired
  WatchUriService watchUriService;

  @Override
  public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
    logger.log(Level.INFO, "Processing " + httpRequest.getRequestLine().getUri());

    try {
      if (watchUriService.isMatch(httpRequest.getRequestLine().getUri())) {
        httpRequest.addHeader("X-ML-Profile", "yes");
        logger.log(Level.INFO, String.format("Requesting profile data for %s", httpRequest.getRequestLine().getUri()));
      }
    } catch(Throwable t) {
      logger.log(Level.SEVERE, t.getMessage(), t);
    }


  }
}
