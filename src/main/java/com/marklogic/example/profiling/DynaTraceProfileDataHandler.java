package com.marklogic.example.profiling;

import com.marklogic.example.profiling.model.Report;
import com.marklogic.example.profiling.model.Expression;

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
  public Report acceptData(String profilingData) {
    Logger logger = Logger.getAnonymousLogger();

    /**
     * Do something usefull with the data.
     */
    logger.log(Level.INFO, profilingData);
    ByteArrayInputStream bais = new ByteArrayInputStream(profilingData.getBytes());
    Report report = JAXB.unmarshal(bais, Report.class);
    logger.log(Level.INFO, report.toString());

    //getProfilingReportData(report);

    //assert report != null;

    return report;
  }

  /*
    the following methods are simply for Dynatrace to hook into
    and allow us to display pertinent information from the
    profiling data coming from MarkLogic
  */
  @Override
  public String printMetadata(Report report) {
    return report.getMetadata().toString();
  }

  @Override
  public String printExpression(Expression expression) {
    return expression.toString();
  }
}
