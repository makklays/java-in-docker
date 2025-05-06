package com.techmatrix18.repositories;

import com.techmatrix18.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for accessing user data (User).
 * Extends JpaRepository to get standard CRUD operations.
 * Additionally, defines a methods for searching for a user by username.
 *
 * @author Alexander Kuziv
 * @since 19.02.2025
 * @version 0.0.1
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByMob(String mob);

    // Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.mob LIKE %:mob%")
    List<User> findByPartMob(@Param("mob") String mob);
}

