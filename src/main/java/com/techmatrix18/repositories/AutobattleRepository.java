package com.techmatrix18.repositories;

import com.techmatrix18.model.Autobattle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AutobattleRepository extends JpaRepository<Autobattle, Long> {
    /**
     * Search Autobattle by id
     * @param id Autobattle ID
     * @return Autobattle, wrapped in Optional
     */
    Optional<Autobattle> findById(Long id);

    /**
     * Search all Autobattles
     * @return all Autobattles
     */
    //List<Autobattle> findAllByOrderBySpaceIdAsc();

    //List<Autobattle> findBySpaceId(Long id);
    //List<Autobattle> findByAutobattleIdOrderByAutobattleAsc(Long id);

    /*@Query("SELECT COUNT(bl) FROM Autobattle bl WHERE bl.autobattle.id = :AutobattleId")
    long countByAutobattleId(Long AutobattleId);*/

    /**
     * Search Autobattle by title
     * @param title Autobattle title
     * @return Autobattle, wrapped in Optional
     */
    //Optional<Autobattle> findByTitle(String title);

    /**
     * Search Autobattle by id and title.
     * JPQL query is used.
     */
    /*@Query("SELECT a FROM Autobattle a WHERE a.id = :id AND a.title = :title")
    Optional<Autobattle> findByIdAndTitle(@Param("id") Long id, @Param("title") String title);*/

    /**
     * Search for Autobattle containing the specified text in the title (case insensitive).
     */
    /*@Query("SELECT a FROM Autobattle a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :titlePart, '%'))")
    List<Autobattle> searchByTitleContaining(@Param("titlePart") String titlePart);*/
}

