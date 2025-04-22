package com.techmatrix18.services;

import com.techmatrix18.model.Contact;
import org.springframework.data.domain.Page;
import java.util.List;

/**
 * Service for managing contact.
 * Provides methods for get a contact by id and by email. Method for get all contacts.
 * Methods for add, update, delete contact. Method for find paginated.
 *
 * @author Alexander Kuziv
 * @since 05.03.2025
 * @version 0.0.1
 */

public interface ContactService {

    Contact getContactById(Long id);
    Contact getConactByEmail(String email);
    List<Contact> getAll();

    boolean addContact(Contact contact);
    boolean updateContact(Contact contact);
    boolean deleteContact(Long id);

    Page<Contact> findPaginated(int pageNo, int pageSize);
}

