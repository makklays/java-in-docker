package com.techmatrix18.services.Impl;

import com.techmatrix18.services.HelloService;
import jakarta.jws.WebService;

/**
 * Implementation Web Service for showing usage of SOAP service Hello.
 *
 * @author Alexander Kuziv
 * @since 13.03.2025
 * @version 0.0.1
 */

@WebService(
        serviceName = "HelloService",
        portName = "HelloPort",
        targetNamespace = "http://service.ws.sample/",
        endpointInterface = "com.techmatrix18.HelloService")
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {

        return "Hello" + name;
    }
}

