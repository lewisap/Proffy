package com.marklogic.example.aspects;

/**
 * Created by phoehne on 8/19/14.
 */
public class ProfilingStatusThreadLocal {
  private static final ThreadLocal<ProfilingStatus> profilingStatus = new ThreadLocal<>();

  public static void enableProfiling() {
    ProfilingStatus status = new ProfilingStatus();
    status.setProfiling(Boolean.TRUE);
    profilingStatus.set(status);
  }

  public static void cleanup() {
    if (profilingStatus.get() != null)
      profilingStatus.remove();
  }

  public static Boolean isProfiling() {
    ProfilingStatus status = profilingStatus.get();
    return (status != null && status.getProfiling());
  }

  public static String getUri() {
    String result = null;
    ProfilingStatus status = profilingStatus.get();
    if (status != null) {
      result = status.getCurrentUri();
    }
    return result;
  }

  public static void setUri(String uri) {
    ProfilingStatus status = profilingStatus.get();
    if (status != null) {
      status.setCurrentUri(uri);
    } else {
      status = new ProfilingStatus();
      status.setCurrentUri(uri);
      profilingStatus.set(status);
    }
  }
}
