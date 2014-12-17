package com.marklogic.example.services;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * About this Service.
 *
 * Use this service to track what URL prefixes are being watched.  Note that Spring
 * services are singletons.  That means that any shared resources must be thread
 * safe.
 *
 * Some design decisions are to use standard locking to protect a set containing
 * the URL prefixes.  This allows "Add" to be called multiple times for the same
 * prefix without adding any additional work.
 *
 * I'm allowing the following:  /foo/bar/baz/qux.json is the end point and there
 * are two prefixes installed "/foo/bar" and "/foo/bar/baz".  In either case
 * I should return a profiling result.
 *
 * Created by phoehne on 8/14/14.
 */
@Service("watchUriService")
public class WatchUriService {
  private Set<String> watchlist = new HashSet<>();

  /**
   * Returns the list of URI prefixes that are being watched.
   *
   * @return A list of prefixes
   */
  public List<String> get() {
    List<String> result = null;

    synchronized (watchlist) {
      result = new LinkedList<>(watchlist);
    }

    return result;
  }

  /**
   * Adds a new URL prefix to watch.
   *
   * @param uri The URI prefix, i.e. "/foo/bar"
   * @return The list of prefixes, including the one added
   */
  public List<String> put(String uri) {
    List<String> result = null;
    synchronized (watchlist) {
      watchlist.add(uri);
      result = new LinkedList<>(watchlist);
    }
    return result;
  }

  /**
   * Removes a prefix from the watch list.
   *
   * @param uri The URI prefix to remove, i.e. "/foo/bar"
   * @return The list of URI prefixes less the removed prefix
   */
  public List<String> delete(String uri) {
    List<String> result = null;
    synchronized (watchlist) {
      watchlist.remove(uri);
      result = new LinkedList<>(watchlist);
    }
    return result;
  }

  /**
   * Checks to see if a given given URI matches one fo the targeted URIs.  For
   * example, if "/foo/bar" is in the set of prefixes to watch, then
   * "/foo/bar.json?this=that&the=other will match.
   *
   * @param uri The URI being called that we want to test
   * @return True, if the uri is a match.
   */
  public boolean isMatch(String uri) {
    boolean result = false;
    synchronized (watchlist) {
      for (String targetUrl : watchlist) {
        result =  (uri.startsWith(targetUrl) || uri.equals(targetUrl) || uri.endsWith(targetUrl));
        if (result) break;
      }
    }
    return result;
  }
}
