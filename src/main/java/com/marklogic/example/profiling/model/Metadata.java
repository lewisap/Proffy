package com.marklogic.example.profiling.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by phoehne on 6/23/14.
 <prof:report xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <prof:metadata>
    <prof:overall-elapsed>PT0.009877S</prof:overall-elapsed>
    <prof:created>2014-06-19T13:09:42.661958-04:00</prof:created>
    <prof:server-version>6.0-3.2</prof:server-version>
  </prof:metadata>
  <prof:histogram>
    <prof:expression>
      ...
    </prof:expression>
  </prof:histogram>
 </prof:report>
 */
@XmlType(
    namespace = "http://marklogic.com/xdmp/profile",
    name = "report",
    propOrder = {"overallElapsed", "created", "serverVersion"}
)
public class Metadata implements Serializable {
  /**
	 *
	 */
	private static final long serialVersionUID = 1L;
  private Duration overallElapsed;
  private Date created;
  private String serverVersion;

  @XmlElement(name="overall-elapsed", namespace="http://marklogic.com/xdmp/profile")
  public Duration getOverallElapsed() {
    return overallElapsed;
  }

  public void setOverallElapsed(Duration overallElapsed) {
    this.overallElapsed = overallElapsed;
  }

  @XmlSchemaType(name="dateTime")
  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  @XmlElement(name="server-version", namespace="http://marklogic.com/xdmp/profile")
  public String getServerVersion() {
    return serverVersion;
  }

  public void setServerVersion(String serverVersion) {
    this.serverVersion = serverVersion;
  }

  @Override
  public String toString() {
    StringBuffer buff = new StringBuffer("{METADATA}");

    buff.append(" | Overall-Elapsed=" + getOverallElapsed());
    buff.append(" | Created=" + getCreated());
    buff.append(" | Server-Version=" + getServerVersion());

    return buff.toString();
  }
}
