package com.techmatrix18.mappers;


import com.techmatrix18.model.Contact;

public class ContactMapper {

    public static com.techmatrix18.jaxb.Contact toJaxbContact(Contact contact) {
        if (contact == null) {
            return null;
        }
        com.techmatrix18.jaxb.Contact jaxbContact = new com.techmatrix18.jaxb.Contact();
        jaxbContact.setName(contact.getName());
        jaxbContact.setId(contact.getId());
        jaxbContact.setEmail(contact.getEmail());
        jaxbContact.setSurename(contact.getSurname());
        jaxbContact.setDescription(contact.getDescription());
        return jaxbContact;
    }

    public static Contact toModelContact(com.techmatrix18.jaxb.Contact jaxbContact) {
        if (jaxbContact == null) {
            return null;
        }
        Contact modelContact = new Contact();
        modelContact.setName(jaxbContact.getName());
        modelContact.setId(jaxbContact.getId());
        modelContact.setEmail(jaxbContact.getEmail());
        modelContact.setSurname(jaxbContact.getSurename());
        modelContact.setDescription(jaxbContact.getDescription());
        return modelContact;
    }
}
