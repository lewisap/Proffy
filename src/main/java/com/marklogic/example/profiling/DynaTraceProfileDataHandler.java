package com.marklogic.example.profiling;

import com.marklogic.example.profiling.model.Report;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.xml.bind.JAXB;
import java.io.ByteArrayInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class needs to be filled in with the necessary plumbing to do something
 * with the response data.
 *
 * Created by phoehne on 6/23/14.
 */
public class DynaTraceProfileDataHandler implements ProfileDataHandler {


  @PostConstruct
  public void startDynatrace() {
  }

  @PreDestroy
  public void shutdownDynatrace() {
  }

  @Override
  public void acceptData(String profilingData) {
    Logger logger = Logger.getAnonymousLogger();

    /**
     * Do something usefull with the data.
     */
    logger.log(Level.INFO, profilingData);
    ByteArrayInputStream bais = new ByteArrayInputStream(profilingData.getBytes());
    Report report = JAXB.unmarshal(bais, Report.class);

    assert report != null;
  }
}
