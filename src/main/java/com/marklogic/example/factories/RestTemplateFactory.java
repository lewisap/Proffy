package com.marklogic.example.factories;

import org.apache.http.HttpHost;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
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
