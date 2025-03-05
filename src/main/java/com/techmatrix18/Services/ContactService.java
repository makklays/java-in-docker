package com.techmatrix18.Services;

import com.techmatrix18.Model.Contact;
import org.springframework.data.domain.Page;
import java.util.List;

/**
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

