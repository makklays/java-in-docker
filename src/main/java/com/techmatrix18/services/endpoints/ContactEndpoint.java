package com.techmatrix18.services.endpoints;

import com.techmatrix18.jaxb.GetContactResponse;
import com.techmatrix18.jaxb.GetContactRequest;
import com.techmatrix18.mappers.ContactMapper;
import com.techmatrix18.model.Contact;
import com.techmatrix18.repositories.ContactRepository;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

// http://localhost:8080/ws/countries.wsdl

@Endpoint
public class ContactEndpoint {
    private static final String NAMESPACE_URI = "http://your-namespace.com";

    private final ContactRepository contactRepository;

    public ContactEndpoint(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getContactRequest")
    @ResponsePayload
    public GetContactResponse getContact(@RequestPayload GetContactRequest request) {
        GetContactResponse response = new GetContactResponse();
        Contact contact =  contactRepository.findByName(request.getName()).orElseThrow();
        response.setContact(ContactMapper.toJaxbContact(contact));
        return response;
    }


}
