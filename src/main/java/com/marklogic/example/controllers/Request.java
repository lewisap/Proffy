package com.marklogic.example.controllers;

import java.io.Serializable;

/**
 * Created by phoehne on 6/17/14.
 */
class Request implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 570180384323387293L;
private String ticker;

  public Request() { ; }
  public Request(String ticker) { this.ticker = ticker; }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }
}