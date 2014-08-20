package com.marklogic.example.support;

import junit.framework.TestCase;
import org.junit.Test;


public class PartParserTest extends TestCase {
  String[] example1 = {"--12859316667011459246",
      "Content-Type: application/xml",
      "boundry: 12859316667011459246",
      "Content-Length: 97",
      "",
      "<Stocks>",
      "<stocks>QUX</stocks><stocks>FOO</stocks><stocks>BAR</stocks><stocks>BAZ</stocks>",
      "</Stocks>",
      "--12859316667011459246--"};

  String[] example2 = {
      "--12859316667011459246", //0
          "Content-Type: application/xml",
          "boundry: 12859316667011459246",
          "Content-Length: 97",
          "",
          "<Stocks><stocks>QUX</stocks><stocks>FOO</stocks><stocks>BAR</stocks><stocks>BAZ</stocks></Stocks>",
          "--12859316667011459246",
          "Content-Type: vnd.x-ml-profile/xml",
          "Content-Length: 95582",
          "",
          "<prof:report xsi:schemaLocation=\"http://marklogic.com/xdmp/profile profile.xsd\" xmlns:prof=\"http://marklogic.com/xdmp/profile\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">", //10
          "  <prof:metadata>",
          "    <prof:overall-elapsed>PT0.009877S</prof:overall-elapsed>",
          "    <prof:created>2014-06-19T13:09:42.661958-04:00</prof:created>",
          "    <prof:server-version>6.0-3.2</prof:server-version>",
          "  </prof:metadata>",
          "  <prof:histogram>",
          "    <prof:expression>",
          "      <prof:expr-id>17016425991390559270</prof:expr-id>",
          "      <prof:expr-source>req:get(\"controller\", \"type=xs:QName\")</prof:expr-source>",
          "      <prof:uri>/roxy/router.xqy</prof:uri>",
          "      <prof:line>29</prof:line>",
          "      <prof:column>44</prof:column>",
          "      <prof:count>1</prof:count>",
          "      <prof:shallow-time>PT0.000004S</prof:shallow-time>",
          "      <prof:deep-time>PT0.000474S</prof:deep-time>",
          "    </prof:expression>",
          "    <prof:expression>",
          "      <prof:expr-id>11784482096640480995</prof:expr-id>",
          "      <prof:expr-source>$options[self::ch:config-format][ch:formats/ch:format = $format]/ch:format</prof:expr-source>",
          "      <prof:uri>/roxy/router.xqy</prof:uri>",
          "      <prof:line>91</prof:line>",
          "      <prof:column>96</prof:column>",
          "      <prof:count>1</prof:count>",
          "      <prof:shallow-time>PT0.000008S</prof:shallow-time>",
          "      <prof:deep-time>PT0.00001S</prof:deep-time>",
          "    </prof:expression>",
          "  </prof:histogram>",
          "</prof:report>", //38
          "--12859316667011459246--"};

  @Test
  public void testAppendFragment() throws Exception {
    PartParser parser = new PartParser();
    for (String s : example1) {
      parser.appendFragment(s);
    }

    assertEquals(1, parser.partCount());

    parser = new PartParser();
    for (String s: example2) {
      parser.appendFragment(s);
    }

    assertEquals(2, parser.partCount());
  }

  @Test
  public void testPartAtIndex() throws Exception {
    PartParser parser = new PartParser();
    for (String s : example1) {
      parser.appendFragment(s);
    }

    assertEquals("<Stocks><stocks>QUX</stocks><stocks>FOO</stocks><stocks>BAR</stocks><stocks>BAZ</stocks></Stocks>",
        parser.partAtIndex(0));

    parser = new PartParser();
    for (String s : example2) {
      parser.appendFragment(s);
    }

    StringBuffer temp = new StringBuffer();
    for (int i = 10; i < 39; i++) {
      temp.append(example2[i]);
    }

    assertEquals("<Stocks><stocks>QUX</stocks><stocks>FOO</stocks><stocks>BAR</stocks><stocks>BAZ</stocks></Stocks>",
        parser.partAtIndex(0));
    assertEquals(temp.toString(), parser.partAtIndex(1));
  }

  @Test
  public void testContentTypeAtIndex() throws Exception {
    PartParser parser = new PartParser();
    for (String s : example1) {
      parser.appendFragment(s);
    }

    assertEquals("application/xml", parser.contentType(0));

    parser = new PartParser();
    for (String s: example2) {
      parser.appendFragment(s);
    }

    assertEquals("vnd.x-ml-profile/xml", parser.contentType(1));
  }

}