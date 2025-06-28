package com.techmatrix18.repositories;

import com.techmatrix18.model.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * This is PassportRepository
 *
 * @company for TechMatrix18
 * @author Alexander Kuziv
 * @since 19.05.205
 * @version 0.0.1
 */

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {

    /**
     * Search Passport by id
     * @param id Passport ID
     * @return Passport, wrapped in Optional
     */
    Optional<Passport> findById(Long id);

    /**
     * Get list of Map
     * @return Passport, wrapped in List
     */
    List<Passport> findAll();

    /**
     * Search Passport by number
     * @param userId User ID
     * @return Passport, wrapped in Optional
     */
    Optional<Passport> findByUserId(Long userId);

    /**
     * Search Passport by number
     * @param number Passport ID
     * @return Passport, wrapped in Optional
     */
    Optional<Passport> findByNumber(String number);
}

