package com.marklogic.example.profiling.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;

/**
 * Created by phoehne on 6/23/14.
 *
 <prof:report xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
   <prof:metadata>
     ...
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
       ...
     </prof:expression>
   </prof:histogram>
 </prof:report>

 */
@XmlType(
    name = "expression",
    namespace = "http://marklogic.com/xdmp/profile"
)
public class Expression {
  private String expressionId;
  private String expressionSource;
  private String uri;
  private int line;
  private int column;
  private int count;
  private Duration shallowTime;
  private Duration deepTime;

  @XmlElement(name="expr-id", namespace = "http://marklogic.com/xdmp/profile")
  public String getExpressionId() {
    return expressionId;
  }

  public void setExpressionId(String expressionId) {
    this.expressionId = expressionId;
  }

  @XmlElement(name="expr-source", namespace = "http://marklogic.com/xdmp/profile")
  public String getExpressionSource() {
    return expressionSource;
  }

  public void setExpressionSource(String expressionSource) {
    this.expressionSource = expressionSource;
  }

  @XmlElement(namespace = "http://marklogic.com/xdmp/profile")
  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  @XmlElement(namespace = "http://marklogic.com/xdmp/profile")
  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  @XmlElement(namespace = "http://marklogic.com/xdmp/profile")
  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  @XmlElement(namespace = "http://marklogic.com/xdmp/profile")
  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  @XmlElement(name="shallow-time", namespace = "http://marklogic.com/xdmp/profile")
  public Duration getShallowTime() {
    return shallowTime;
  }

  public void setShallowTime(Duration shallowTime) {
    this.shallowTime = shallowTime;
  }

  @XmlElement(name="deep-time", namespace = "http://marklogic.com/xdmp/profile")
  public Duration getDeepTime() {
    return deepTime;
  }

  public void setDeepTime(Duration deepTime) {
    this.deepTime = deepTime;
  }
}
