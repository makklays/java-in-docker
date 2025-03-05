package com.techmatrix18.Services.Impl;

import com.techmatrix18.Model.Contact;
import com.techmatrix18.Repositories.ContactRepository;
import com.techmatrix18.Services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
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
}
