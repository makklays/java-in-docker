package com.techmatrix18.repositories;

import com.techmatrix18.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    /**
     * Search unit by id
     * @param id Unit ID
     * @return unit, wrapped in Optional
     */
    Optional<Unit> findById(Long id);

    /**
     * Search all units
     * @return all units
     */
    List<Unit> findAll();

    /**
     * Search unit by title
     * @param title Unit title
     * @return unit, wrapped in Optional
     */
    Optional<Unit> findByTitle(String title);

    /**
     * Search unit by id and title.
     * JPQL query is used.
     */
    @Query("SELECT a FROM Unit a WHERE a.id = :id AND a.title = :title")
    Optional<Unit> findByIdAndTitle(@Param("id") Long id, @Param("title") String title);

    /**
     * Search for units containing the specified text in the title (case insensitive).
     */
    @Query("SELECT a FROM Unit a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :titlePart, '%'))")
    List<Unit> searchByTitleContaining(@Param("titlePart") String titlePart);

    /**
     * Search unit by type 'biolab'.
     * JPQL query is used.
     */
    @Query("SELECT a FROM Unit a WHERE a.type = 'biolab'")
    List<Unit> findAllByTypeBiolab();

    /**
     * Search unit by type 'hangar'.
     * JPQL query is used.
     */
    @Query("SELECT a FROM Unit a WHERE a.type = 'hangar'")
    List<Unit> findAllByTypeHangar();
}
