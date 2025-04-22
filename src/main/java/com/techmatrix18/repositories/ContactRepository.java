package com.techmatrix18.repositories;

import com.techmatrix18.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository for accessing contact data (Contact).
 * Extends JpaRepository to get standard CRUD operations.
 * Additionally, defines a methods for searching for a contact by name, by email.
 *
 * @author Alexander Kuziv
 * @since 05.03.2025
 * @version 0.0.1
 */

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByName(String name);
    Optional<Contact> findByEmail(String email);
}

