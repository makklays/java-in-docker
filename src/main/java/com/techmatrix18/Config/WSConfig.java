package com.techmatrix18.Config;

import com.techmatrix18.Services.Impl.HelloServiceImpl;
import org.apache.cxf.Bus;
import javax.xml.ws.Endpoint;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WSConfig {

    /*@Autowired(required=true)
    private Bus bus;

    @Bean
    public Endpoint HelloEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new HelloServiceImpl());
        endpoint.publish("ServiceHello");

        return endpoint;
    }*/
}

