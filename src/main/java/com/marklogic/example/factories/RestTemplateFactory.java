package com.marklogic.example.factories;

import org.apache.http.HttpHost;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * The POC uses this factory to create a proper REST template that will authenticate against
 * the target host.
 *
 * Because this is essentially providing authentication, and you probably already do that,
 * you will not need this class.
 *
 * Created by phoehne on 6/16/14.
 */
@Component
public class RestTemplateFactory implements FactoryBean<RestTemplate>, InitializingBean {
  private RestTemplate restTemplate;

  @Override
  public RestTemplate getObject() {
    return restTemplate;
  }

  @Override
  public Class<?> getObjectType() {
    return RestTemplate.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    HttpHost host = new HttpHost("localhost", 8100, "http");
    restTemplate = new RestTemplate(new HttpComponentClientHttpRequestFactoryBasicAuth(host));
  }
}
