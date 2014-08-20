package com.marklogic.example.controllers;

import com.marklogic.example.aspects.Profileable;
import com.marklogic.example.models.Stocks;
import com.marklogic.example.support.AddProfilingHeader;
import com.marklogic.example.support.MultipartPerformanceMessageConverter;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * Created by phoehne on 6/16/14.
 */
@Controller("stocksController")
@RequestMapping("/stocks")
public class StocksController {;

  @Autowired
  private UsernamePasswordCredentials credentials;
  @Autowired
  private AddProfilingHeader profilingHeader;
  @Autowired
  private MultipartPerformanceMessageConverter messageConverter;

  private String apiHost = "localhost";
  private int apiPort = 8100;

  public String getApiHost() {
    return apiHost;
  }

  public void setApiHost(String apiHost) {
    this.apiHost = apiHost;
  }

  public int getApiPort() {
    return apiPort;
  }

  public void setApiPort(int apiPort) {
    this.apiPort = apiPort;
  }

  private RestTemplate makeTemplate() {
    BasicCredentialsProvider bcp = new BasicCredentialsProvider();
    bcp.setCredentials(AuthScope.ANY, credentials);

    DefaultHttpClient client = new DefaultHttpClient();
    client.setCredentialsProvider(bcp);
    client.addRequestInterceptor(profilingHeader);

    ClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
    RestTemplate restTemplate = new RestTemplate(factory);
    restTemplate.getMessageConverters().add(messageConverter);
    return restTemplate;
  }

  @Profileable
  @RequestMapping(method=RequestMethod.GET)
  public String get(Map<String, Object> model) throws Exception {
    RestTemplate restTemplate = makeTemplate();

    String rawUri = String.format("http://%s:%d/stocks/list.xml", apiHost, apiPort);

    try {
      ResponseEntity<Stocks> stocksResponse = restTemplate.getForEntity(new URI(rawUri), Stocks.class);
      model.put("stocks", stocksResponse.getBody());
    } catch(Exception e) {
      throw new Exception(e);
    }
    return "/WEB-INF/views/stocks.jsp";
  }

  @Profileable
  @RequestMapping(method=RequestMethod.POST)
  public String add(@RequestParam String ticker) throws Exception {
    RestTemplate restTemplate = makeTemplate();
    String rawUri = String.format("http://%s:%d/stocks/add", apiHost, apiPort);

    MultiValueMap<String, String> foo = new LinkedMultiValueMap<>();
    foo.add("ticker", ticker);

    restTemplate.postForLocation(new URI(rawUri), foo);

    return "redirect:/stocks";
  }
}
