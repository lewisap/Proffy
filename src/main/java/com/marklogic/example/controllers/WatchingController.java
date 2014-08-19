package com.marklogic.example.controllers;

import com.marklogic.example.services.WatchUriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This controller offers the following endpoints:
 *
 * /watching -> Returns the list of URI prefixes to watch
 * /watching/add?watchUri=[uri] Adds the given URI to the list of URIs to watch
 * /watching/delete?watchUri=[uri] Removes the given URI from the list of URIs to watch.
 *
 * Use GET on "/watching" and POST on the other two.
 *
 *
 * Created by phoehne on 8/14/14.
 */
@Controller
@RequestMapping("/watching")
public class WatchingController {

  static Logger logger = Logger.getLogger("WatchingLogger");

  @Autowired
  private WatchUriService watchUriService;

  public WatchUriService getWatchUraiService() {
    return watchUriService;
  }

  public void setWatchUriService(WatchUriService watchUriService) {
    this.watchUriService = watchUriService;
  }

  @RequestMapping(method=RequestMethod.GET)
  @ResponseBody public List<String> get() {
    return watchUriService.get();
  }


  @RequestMapping(value="add", method=RequestMethod.POST)
  @ResponseBody public List<String> post(@RequestParam String watchUri) {
    logger.info(String.format("Adding %s to list of watched URIs", watchUri));
    return watchUriService.put(watchUri);
  }


  @RequestMapping(value="delete", method=RequestMethod.POST)
  @ResponseBody public List<String> delete(@RequestParam String watchUri) {
    logger.info(String.format("Removing %s from the list of watched URIs", watchUri));
    return watchUriService.delete(watchUri);
  }
}
