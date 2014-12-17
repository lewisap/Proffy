package com.marklogic.example.profiling.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by phoehne on 6/23/14.
 *
  <prof:report xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <prof:metadata>
      <prof:overall-elapsed>PT0.009877S</prof:overall-elapsed>
      <prof:created>2014-06-19T13:09:42.661958-04:00</prof:created>
      <prof:server-version>6.0-3.2</prof:server-version>
    </prof:metadata>
    <prof:histogram>
      <prof:expression>
        <prof:expr-id>17016425991390559270</prof:expr-id>
        <prof:expr-source>req:get("controller", "type=xs:QName")</prof:expr-source>
        <prof:uri>/roxy/router.xqy</prof:uri>
        <prof:line>29</prof:line>
        <prof:column>44</prof:column>
        <prof:count>1</prof:count>
        <prof:shallow-time>PT0.000004S</prof:shallow-time>
        <prof:deep-time>PT0.000474S</prof:deep-time>
      </prof:expression>
      <prof:expression>
        <prof:expr-id>11784482096640480995</prof:expr-id>
        <prof:expr-source>$options[self::ch:config-format][ch:formats/ch:format = $format]/ch:format</prof:expr-source>
        <prof:uri>/roxy/router.xqy</prof:uri>
        <prof:line>91</prof:line>
        <prof:column>96</prof:column>
        <prof:count>1</prof:count>
        <prof:shallow-time>PT0.000008S</prof:shallow-time>
        <prof:deep-time>PT0.00001S</prof:deep-time>
      </prof:expression>
    </prof:histogram>
  </prof:report>
 */


@XmlRootElement(
    namespace = "http://marklogic.com/xdmp/profile",
    name = "report"
)
public class Report implements Serializable {
  /**
	 *
	 */
	private static final long serialVersionUID = -4467047476271369924L;
  private Metadata metadata;
  private Histogram histogram;

  @XmlElement(name = "metadata", namespace = "http://marklogic.com/xdmp/profile")
  public Metadata getMetadata() {
    return metadata;
  }

  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }

  @XmlElement(name = "histogram", namespace = "http://marklogic.com/xdmp/profile")
  public Histogram getHistogram() {
    return histogram;
  }

  public void setHistogram(Histogram histogram) {
    this.histogram = histogram;
  }

  @Override
  public String toString() {
    StringBuffer buff = new StringBuffer("\nPROFILING DATA");

    buff.append(getMetadata().toString());
    for (Expression expression: getHistogram().getExpression()) {
      buff.append(expression.toString());
    }

    return buff.toString();
  }
}
