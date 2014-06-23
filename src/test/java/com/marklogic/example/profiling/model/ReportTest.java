package com.marklogic.example.profiling.model;

import org.junit.Test;

import javax.xml.bind.JAXB;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class ReportTest {
  private String testString = "<prof:report xmlns:prof=\"http://marklogic.com/xdmp/profile\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
      "    <prof:metadata>\n" +
      "      <prof:overall-elapsed>PT0.009877S</prof:overall-elapsed>\n" +
      "      <prof:created>2014-06-19T13:09:42.661958-04:00</prof:created>\n" +
      "      <prof:server-version>6.0-3.2</prof:server-version>\n" +
      "    </prof:metadata>\n" +
      "    <prof:histogram>\n" +
      "      <prof:expression>\n" +
      "        <prof:expr-id>17016425991390559270</prof:expr-id>\n" +
      "        <prof:expr-source>req:get(\"controller\", \"type=xs:QName\")</prof:expr-source>\n" +
      "        <prof:uri>/roxy/router.xqy</prof:uri>\n" +
      "        <prof:line>29</prof:line>\n" +
      "        <prof:column>44</prof:column>\n" +
      "        <prof:count>1</prof:count>\n" +
      "        <prof:shallow-time>PT0.000004S</prof:shallow-time>\n" +
      "        <prof:deep-time>PT0.000474S</prof:deep-time>\n" +
      "      </prof:expression>\n" +
      "      <prof:expression>\n" +
      "        <prof:expr-id>11784482096640480995</prof:expr-id>\n" +
      "        <prof:expr-source>$options[self::ch:config-format][ch:formats/ch:format = $format]/ch:format</prof:expr-source>\n" +
      "        <prof:uri>/roxy/router.xqy</prof:uri>\n" +
      "        <prof:line>91</prof:line>\n" +
      "        <prof:column>96</prof:column>\n" +
      "        <prof:count>1</prof:count>\n" +
      "        <prof:shallow-time>PT0.000008S</prof:shallow-time>\n" +
      "        <prof:deep-time>PT0.00001S</prof:deep-time>\n" +
      "      </prof:expression>\n" +
      "    </prof:histogram>\n" +
      "  </prof:report>";

  @Test
  public void testUnmarshall() {
    ByteArrayInputStream bais = new ByteArrayInputStream(testString.getBytes());
    Report r = JAXB.unmarshal(bais, Report.class);

    assertNotNull(r);
    assertNotNull(r.getMetadata());
    assertNotNull(r.getMetadata().getOverallElapsed());
    assertNotNull(r.getMetadata().getCreated());
    assertNotNull(r.getMetadata().getServerVersion());

    assertEquals(2, r.getHistogram().getExpression().size());
    assertEquals(44, r.getHistogram().getExpression().get(0).getColumn());
    assertEquals(96, r.getHistogram().getExpression().get(1).getColumn());
  }
}