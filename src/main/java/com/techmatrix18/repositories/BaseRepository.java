package com.techmatrix18.repositories;

import com.techmatrix18.model.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaseRepository extends JpaRepository<Base, Long> {

    /**
     * Search base by id
     * @param id Base ID
     * @return base, wrapped in Optional
     */
    Optional<Base> findById(Long id);

    /**
     * Search base by title
     * @param title Base title
     * @return base, wrapped in Optional
     */
    Optional<Base> findByTitle(String title);

    /**
     * Search base by id and title.
     * JPQL query is used.
     */
    @Query("SELECT a FROM Base a WHERE a.id = :id AND a.title = :title")
    Optional<Base> findByIdAndTitle(@Param("id") Long id, @Param("title") String title);

    /**
     * Search for bases containing the specified text in the title (case insensitive).
     */
    @Query("SELECT a FROM Base a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :titlePart, '%'))")
    List<Base> searchByTitleContaining(@Param("titlePart") String titlePart);
}
