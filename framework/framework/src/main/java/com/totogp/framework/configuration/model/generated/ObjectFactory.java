//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2013.12.24 at 03:16:03 PM CET
//

package com.totogp.framework.configuration.model.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the
 * fr.vdm.soclejee.configuration.model.generated package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _Configuration_QNAME = new QName("http://www.example.org/configuration-file/",
      "configuration");

  /**
   * Create a new ObjectFactory that can be used to create new instances of
   * schema derived classes for package:
   * fr.vdm.soclejee.configuration.model.generated
   * 
   */
  public ObjectFactory() {
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ConfigurationType
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://www.example.org/configuration-file/", name = "configuration")
  public JAXBElement<ConfigurationType> createConfiguration(ConfigurationType value) {
    return new JAXBElement<ConfigurationType>(_Configuration_QNAME, ConfigurationType.class, null, value);
  }

  /**
   * Create an instance of {@link ConfigurationType }
   * 
   */
  public ConfigurationType createConfigurationType() {
    return new ConfigurationType();
  }

  /**
   * Create an instance of {@link FileDefinitionType }
   * 
   */
  public FileDefinitionType createFileDefinitionType() {
    return new FileDefinitionType();
  }

  /**
   * Create an instance of {@link PropertyDefinitionType }
   * 
   */
  public PropertyDefinitionType createPropertyDefinitionType() {
    return new PropertyDefinitionType();
  }

}
