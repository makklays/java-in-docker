package com.techmatrix18.repositories;

import com.techmatrix18.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * This is BlogRepository
 *
 * @company for TechMatrix18
 * @author Alexander Kuziv
 * @since 01.06.205
 * @version 0.0.1
 */

public interface BlogRepository extends JpaRepository<Blog, Long> {

    /**
     * Search Blog by id
     * @param id Blog ID
     * @return Blog, wrapped in Optional
     */
    Optional<Blog> findById(Long id);

    /**
     * Get list of Map
     * @return Blog, wrapped in List
     */
    List<Blog> findAll();

    /**
     * Search list of Map by spaceId
     * @param spaceId Space
     * @return Blog, wrapped in List
     */
    List<Blog> findBySpaceIdOrderBySectorAsc(Long spaceId);

    /**
     * Search last 10 posts of Blog by SpaceID
     * @param spaceId Space ID
     * @return Blog, wrapped in List
     */
    List<Blog> findTop2BySpaceIdOrderByCreatedAtAsc(Long spaceId);

    /**
     * Search Map by spaceId and sector
     * @param spaceId Space
     * @return Blog, wrapped in Optional
     */
    List<Blog> findBySpaceIdAndSector(Long spaceId, Integer sector);
}

