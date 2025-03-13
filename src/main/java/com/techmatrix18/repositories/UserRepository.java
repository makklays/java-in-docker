package com.techmatrix18.repositories;

import com.techmatrix18.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * @author Alexander Kuziv
 * @since 19-02-2025
 * @version 0.0.1
 */

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByUsername(String username);

}

