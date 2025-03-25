package com.techmatrix18.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class SoapWebServiceConfig extends WsConfigurerAdapter {

    @Bean("calculator")
    public DefaultWsdl11Definition calculatorServiceDefinition() {
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();
        wsdlDefinition.setPortTypeName("CalculatorServicePort");
        wsdlDefinition.setLocationUri("/ws");
        wsdlDefinition.setTargetNamespace("http://your-namespace.com");
        wsdlDefinition.setSchema(calculatorServiceSchema());
        return wsdlDefinition;
    }

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

    @Bean
    public XsdSchema contactSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/xsd/contact.xsd"));
    }
}

