package com.marklogic.example.profiling;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by phoehne on 6/23/14.
 */
public class DynaTraceProfileDataHandler implements ProfileDataHandler {

  @Override
  public void acceptData(String profilingData) {
    Logger logger = Logger.getAnonymousLogger();

    /**
     * Do something usefull with the data.
     */
    logger.log(Level.INFO, profilingData);
  }
}
