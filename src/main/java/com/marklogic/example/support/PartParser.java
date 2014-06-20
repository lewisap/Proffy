package com.marklogic.example.support;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by phoehne on 6/19/14.
 */
public class PartParser {
  private enum ParserStates { Starting, Headers, Body };

  String currentLine = null;
  ParserStates currentState = ParserStates.Starting;

  String boundary = null;
  StringBuffer accumulator;

  List<String> parts = new ArrayList<>();
  List<String> mimeTypes = new ArrayList<>();

  Pattern contentTypePattern = Pattern.compile("Content-Type: (.*)");

  public void appendFragment(String line) {
    if (currentState == ParserStates.Starting) {
      if (boundary == null) {
        boundary = line;
      } else {
        Matcher matcher = contentTypePattern.matcher(line);
        if (matcher.matches()) {
          mimeTypes.add(matcher.group(1));
        }
      }

      currentState = ParserStates.Headers;
      accumulator = new StringBuffer();
    } else {
      if (line.startsWith(boundary)) {
        parts.add(accumulator.toString());
        currentState = ParserStates.Starting;
      } else if(currentState == ParserStates.Headers) {
        if("".equals(line)) {
          currentState = ParserStates.Body;
        } else {
          Matcher matcher = contentTypePattern.matcher(line);
          if (matcher.matches()) {
            mimeTypes.add(matcher.group(1));
          }
        }
      } else if(currentState == ParserStates.Body) {
        accumulator.append(line);
      }
    }
  }

  public String partAtIndex(int index) {
    return parts.get(index);
  }

  public int partCount() {
    return parts.size();
  }

  public String contentType(int idx) {
    return mimeTypes.get(idx);
  }
}
