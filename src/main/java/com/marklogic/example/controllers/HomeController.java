package com.marklogic.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by phoehne on 6/13/14.
 */
@Controller
public class HomeController {

  @RequestMapping(value="/")
  public String home() {
    return "home";
  }

}
