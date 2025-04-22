package com.techmatrix18.services.Impl;

import com.techmatrix18.model.Contact;
import com.techmatrix18.repositories.ContactRepository;
import com.techmatrix18.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Contact for managing contacts of service Contact.
 *
 * @author Alexander Kuziv
 * @since 05.03.2025
 * @version 0.0.1
 */

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> getAll() {
        List<Contact> list = new ArrayList<>();
        contactRepository.findAll().forEach(list::add);

        return list;
    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id).get();
    }

    @Override
    public Contact getConactByEmail(String email) {
        return contactRepository.findByEmail(email).get();
    }

    @Override
    public boolean addContact(Contact contact) {
        Contact b = contactRepository.save(contact);
        if (!b.getName().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateContact(Contact contact) {
        Contact b = contactRepository.save(contact);
        if (!b.getName().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteContact(Long id) {
        Contact contact = contactRepository.getById(id);
        if (!contact.getName().isEmpty()) {
            contactRepository.delete(contact);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Page<Contact> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return contactRepository.findAll(pageable);
    }
}

