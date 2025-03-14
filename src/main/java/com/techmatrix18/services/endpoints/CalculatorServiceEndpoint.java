package com.techmatrix18.services.endpoints;

import com.techmatrix18.jaxb.AddRequest;
import com.techmatrix18.jaxb.AddResponse;
import com.techmatrix18.jaxb.SubtractRequest;
import com.techmatrix18.jaxb.SubtractResponse;
import com.techmatrix18.services.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CalculatorServiceEndpoint {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorServiceEndpoint(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PayloadRoot(namespace = "http://your-namespace.com", localPart = "addRequest")
    @ResponsePayload
    public AddResponse add(@RequestPayload AddRequest request) {
        AddResponse response = new AddResponse();
        response.setResult(calculatorService.add(request.getA(), request.getB()));
        return response;
    }

    @PayloadRoot(namespace = "http://your-namespace.com", localPart = "subtractRequest")
    @ResponsePayload
    public SubtractResponse subtract(@RequestPayload SubtractRequest request) {
        SubtractResponse response = new SubtractResponse();
        response.setResult(calculatorService.subtract(request.getA(), request.getB()));
        return response;
    }
}

