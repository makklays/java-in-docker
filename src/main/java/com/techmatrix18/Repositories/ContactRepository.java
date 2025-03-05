package com.techmatrix18.Repositories;

import com.techmatrix18.Model.Contact;
import com.techmatrix18.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * @author Alexander Kuziv
 * @since 05.03.2025
 * @version 0.0.1
 */

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByName(String name);
    Optional<Contact> findByEmail(String email);
}

