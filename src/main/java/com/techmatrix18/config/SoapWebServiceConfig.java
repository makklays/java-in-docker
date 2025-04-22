package com.techmatrix18.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Configuration class for configuring a SOAP web service.
 *
 * Enables SOAP support, registers the message dispatcher servlet,
 * configures the WSDL and loads the XML schema.
 *
 * @author Alexander Kuziv
 * @since 14-03-2025
 * @version 0.0.1
 */

@Configuration
@EnableWs
public class SoapWebServiceConfig extends WsConfigurerAdapter {

    /**
     * Configures a WSDL 1.1 description for a web service.
     *
     * @param schema XML Schema (XSD) describing the structure of data
     * @return WSDL definition object
     */
    @Bean("calculator")
    public DefaultWsdl11Definition calculatorServiceDefinition() {
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();
        wsdlDefinition.setPortTypeName("CalculatorServicePort");
        wsdlDefinition.setLocationUri("/ws");
        wsdlDefinition.setTargetNamespace("http://your-namespace.com");
        wsdlDefinition.setSchema(calculatorServiceSchema());
        return wsdlDefinition;
    }

    /**
     * Configures a WSDL 1.1 description for a web service.
     *
     * @param schema XML Schema (XSD) describing the structure of data
     * @return WSDL definition object
     */
    @Bean("contact")
    public DefaultWsdl11Definition contactServiceDefinition() {
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();
        wsdlDefinition.setPortTypeName("ContactServicePort");
        wsdlDefinition.setLocationUri("/ws");
        wsdlDefinition.setTargetNamespace("http://your-namespace.com");
        wsdlDefinition.setSchema(contactSchema());
        return wsdlDefinition;
    }

    @Bean
    public XsdSchema calculatorServiceSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/xsd/calculator.xsd"));
    }

    /**
     * Loads an XML schema (XSD) from the classpath.
     *
     * @return XSD schema object
     */
    @Bean
    public XsdSchema contactSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/xsd/contact.xsd"));
    }
}

