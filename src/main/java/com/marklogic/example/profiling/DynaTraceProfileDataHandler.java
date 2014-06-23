package com.marklogic.example.profiling;

import com.marklogic.example.profiling.model.Report;

import javax.xml.bind.JAXB;
import java.io.ByteArrayInputStream;
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
    ByteArrayInputStream bais = new ByteArrayInputStream(profilingData.getBytes());
    Report report = JAXB.unmarshal(bais, Report.class);

    assert report != null;
  }
}
