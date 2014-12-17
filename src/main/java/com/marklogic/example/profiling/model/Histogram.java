package com.marklogic.example.profiling.model;

import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by phoehne on 6/23/14.
 *
 *
 <prof:report xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
   <prof:metadata>
     ...
   </prof:metadata>
   <prof:histogram>
     <prof:expression>
       ...
     </prof:expression>
     <prof:expression>
       ...
     </prof:expression>
   </prof:histogram>
 </prof:report>
 */
@XmlType(name = "histogram", namespace = "http://marklogic.com/xdmp/profile")
public class Histogram {
  private List<Expression> expressions;

  public List<Expression> getExpression() {
    return expressions;
  }

  public void setExpression(List<Expression> expressions) {
    this.expressions = expressions;
  }
}
