package com.techmatrix18.repositories;

import com.techmatrix18.model.BaseLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BaseLevelRepository extends JpaRepository<BaseLevel, Long> {

    /**
     * Search BaseLevel by id
     * @param id BaseLevel ID
     * @return BaseLevel, wrapped in Optional
     */
    Optional<BaseLevel> findById(Long id);

    /**
     * Search all BaseLevels
     * @return all BaseLevels
     */
    List<BaseLevel> findAll();

    List<BaseLevel> findByBaseId(Long id);

    /**
     * Search BaseLevel by title
     * @param title BaseLevel title
     * @return BaseLevel, wrapped in Optional
     */
    Optional<BaseLevel> findByTitle(String title);

    /**
     * Search baseLevel by id and title.
     * JPQL query is used.
     */
    @Query("SELECT a FROM BaseLevel a WHERE a.id = :id AND a.title = :title")
    Optional<BaseLevel> findByIdAndTitle(@Param("id") Long id, @Param("title") String title);

    /**
     * Search for baseLevel containing the specified text in the title (case insensitive).
     */
    @Query("SELECT a FROM BaseLevel a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :titlePart, '%'))")
    List<BaseLevel> searchByTitleContaining(@Param("titlePart") String titlePart);
}

