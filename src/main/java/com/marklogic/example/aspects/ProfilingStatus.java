package com.marklogic.example.aspects;

/**
 * Created by phoehne on 8/19/14.
 */
public class ProfilingStatus {
  private Boolean profiling = Boolean.FALSE;
  private String currentUri;

  public Boolean getProfiling() {
    return profiling;
  }

  public void setProfiling(Boolean profiling) {
    this.profiling = profiling;
  }

  public String getCurrentUri() {
    return currentUri;
  }

  public void setCurrentUri(String currentUri) {
    this.currentUri = currentUri;
  }
}
