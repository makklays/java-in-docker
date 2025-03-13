package com.techmatrix18.services.Impl;

import com.techmatrix18.services.HelloService;
import jakarta.jws.WebService;

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

