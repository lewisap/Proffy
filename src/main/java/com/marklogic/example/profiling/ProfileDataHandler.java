package com.marklogic.example.profiling;

/**
 * Implement this interface to be able to receive the profiling data
 * and to be able to do something (reasonably) intelligent with it.
 * Created by phoehne on 6/23/14.
 */
public interface ProfileDataHandler {

  public void acceptData(String profilingData);
}
