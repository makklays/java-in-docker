package com.techmatrix18.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

/**
 * SOAP Service for managing datas.
 *
 * @author Alexander Kuziv
 * @since 05.03.2025
 * @version 0.0.1
 */

@WebService(targetNamespace = "service.ws.sample", name = "HelloService")
public interface HelloService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(
            localName = "sayHello",
            targetNamespace = "http://service.ws.sample",
            className = "sample.ws.service.RequestSayHello")
    @WebMethod(action = "urn:sayHello")
    @ResponseWrapper(
            localName = "sayHelloResponse",
            targetNamespace = "http://service.ws.sample",
            className = "sample.ws.service.SayHelloResponse")
    String sayHello(@WebParam(name = "name", targetNamespace = "") String name);
}

