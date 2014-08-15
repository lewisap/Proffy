package com.marklogic.example.factories;

import org.apache.http.HttpHost;
import org.apache.http.client.AuthCache;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;


import java.net.URI;

/**
 * This class will perform HTTP Basic authentication agains the MarkLogic
 * server.
 *
 * This is for the POC and you probably do not need this class because your
 * end points already authenticate against MakrLogic.
 *
 * Created by phoehne on 6/16/14.
 */
public class HttpComponentClientHttpRequestFactoryBasicAuth extends HttpComponentsClientHttpRequestFactory {
  HttpHost host;

  public HttpComponentClientHttpRequestFactoryBasicAuth(HttpHost host) {
    super();
    this.host = host;
  }

  protected HttpContext createHttpContext(HttpMethod method, URI uri) {
    return createHttpContext();
  }

  private HttpContext createHttpContext() {
    AuthCache authCache = new BasicAuthCache();
    BasicScheme basicAuth = new BasicScheme();
    authCache.put(host, basicAuth);

    BasicHttpContext localContext = new BasicHttpContext();
    localContext.setAttribute(ClientContext.AUTH_CACHE, authCache);

    return localContext;
  }
}
