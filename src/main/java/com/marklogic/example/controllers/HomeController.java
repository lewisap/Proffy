package com.marklogic.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This class is just for the POC.  You will not need this class in the production application.
 *
 * Created by phoehne on 6/13/14.
 */
@Controller
public class HomeController {

  @RequestMapping(value="/")
  public String home() {
    return "home";
  }

}
