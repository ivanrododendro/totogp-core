//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2013.12.24 at 03:16:03 PM CET
//

package com.totogp.framework.configuration.model.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for javaTypeType.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="javaTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="java.lang.String"/>
 *     &lt;enumeration value="java.lang.Float"/>
 *     &lt;enumeration value="java.lang.Integer"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "javaTypeType")
@XmlEnum
public enum JavaTypeType {

  @XmlEnumValue("java.lang.String") JAVA_LANG_STRING(
      "java.lang.String"), @XmlEnumValue("java.lang.Float") JAVA_LANG_FLOAT(
          "java.lang.Float"), @XmlEnumValue("java.lang.Integer") JAVA_LANG_INTEGER("java.lang.Integer");
  private final String value;

  public static JavaTypeType fromValue(String v) {
    for (final JavaTypeType c : JavaTypeType.values())
      if (c.value.equals(v)) return c;
    throw new IllegalArgumentException(v);
  }

  JavaTypeType(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

}