package com.marklogic.example.aspects;

import com.marklogic.example.services.WatchUriService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by phoehne on 8/19/14.
 */
@Aspect
public class DynatraceProfilingAspect {

  @Around("@annotation(com.marklogic.example.aspects.Profileable)")
  public Object setupTeardownTLS(ProceedingJoinPoint proceedingJoinPoint) {
    Object value = null;
    Logger logger = Logger.getLogger("DyantraceProfilingAspect");

    try {
      value = proceedingJoinPoint.proceed();
      ProfilingStatusThreadLocal.cleanup();
    } catch(Throwable t) {
      logger.log(Level.WARNING, t.getMessage(), t);
    }

    return value;
  }
}
