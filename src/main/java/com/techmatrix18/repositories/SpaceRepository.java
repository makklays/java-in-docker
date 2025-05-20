package com.techmatrix18.repositories;

import com.techmatrix18.model.Map;
import com.techmatrix18.model.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This is SpaceRepository
 *
 * @company for TechMatrix18
 * @author Alexander Kuziv
 * @since 19.05.205
 * @version 0.0.1
 */
@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {

    /**
     * Search Space by id
     * @param id Space ID
     * @return Space, wrapped in Optional
     */
    Optional<Space> findById(Long id);

    /**
     * Get list of Space
     * @return Space, wrapped in List
     */
    List<Space> findAll();

    /**
     * Get Space by userId
     * @param userId
     * @return Space, wrapped in Optional
     */
    Optional<Space> findByUserId(Long userId);

    /**
     * Search list of Space by userId
     * @param userId User
     * @return Space, wrapped in List
     */
    List<Space> findByUserIdOrderByCreatedAtAsc(Long userId);
}

