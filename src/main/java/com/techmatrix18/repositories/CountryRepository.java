package com.techmatrix18.repositories;

import com.techmatrix18.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository for accessing country data (Country).
 * Extends JpaRepository to get standard CRUD operations.
 * Additionally, defines a methods for searching for a country by name.
 *
 * @author Alexander Kuziv
 * @since 05.03.2025
 * @version 0.0.1
 */

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByName(String name);
}

