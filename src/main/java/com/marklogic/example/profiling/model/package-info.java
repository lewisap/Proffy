/**
 * Created by phoehne on 6/23/14.
 */


@javax.xml.bind.annotation.XmlSchema(location="http://marklogic.com/xdmp/profile profile.xsd",
    namespace="http://marklogic.com/xdmp/prof", elementFormDefault= XmlNsForm.QUALIFIED, xmlns = {
    @XmlNs(prefix = "prof", namespaceURI = "http://marklogic.com/xdmp/profile")
})
package com.marklogic.example.profiling.model;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;