package com.techmatrix18.repositories;

import com.techmatrix18.model.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * This is MapRepository
 *
 * @company for TechMatrix18
 * @author Alexander Kuziv
 * @since 13.05.205
 * @version 0.0.1
 */

@Repository
public interface MapRepository extends JpaRepository<Map, Long> {

    /**
     * Search Map by id
     * @param id Map ID
     * @return Map, wrapped in Optional
     */
    Optional<Map> findById(Long id);

    /**
     * Get list of Map
     * @return Map, wrapped in List
     */
    List<Map> findAll();

    /**
     * Search list of Map by spaceId
     * @param spaceId Space
     * @return Map, wrapped in List
     */
    List<Map> findBySpaceIdOrderBySectorAsc(Long spaceId);

    /**
     * Search Map by spaceId and sector
     * @param spaceId Space
     * @return Map, wrapped in Optional
     */
    Optional<Map> findBySpaceIdAndSector(Long spaceId, Integer sector);

}

