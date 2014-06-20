package com.marklogic.example.models;

import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by phoehne on 6/16/14.
 */
@XmlRootElement(name="Stocks")
public class Stocks {
  List<String> stocks;

  public Stocks() {
    stocks = new LinkedList<>();
  }

  public List<String> getStocks() {
    return stocks;
  }

  public void setStocks(List<String> stocks) {
    this.stocks = stocks;
  }
}
