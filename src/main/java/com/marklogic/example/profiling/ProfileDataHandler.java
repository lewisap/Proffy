package com.marklogic.example.profiling;

import com.marklogic.example.profiling.model.Report;
import com.marklogic.example.profiling.model.Expression;

/**
 * Implement this interface to be able to receive the profiling data
 * and to be able to do something (reasonably) intelligent with it.
 * Created by phoehne on 6/23/14.
 */
public interface ProfileDataHandler {

  public Report acceptData(String profilingData);

  public String printMetadata(Report report);

  // Dynatrace needs to hook into a method, so create this to juse
  // call expression.toString as the return
  public String printExpression(Expression expression);
}
