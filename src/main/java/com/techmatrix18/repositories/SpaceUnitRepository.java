package com.techmatrix18.repositories;

import com.techmatrix18.model.SpaceUnit;
import com.techmatrix18.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpaceUnitRepository extends JpaRepository<SpaceUnit, Long> {
    /**
     * Search spaceUnit by id
     * @param id SpaceUnit ID
     * @return spaceUnit, wrapped in Optional
     */
    Optional<SpaceUnit> findById(Long id);

    /**
     * Search spaceUnit by Space ID
     * @param spaceId Long
     * @return spaceUnit, wrapped in List
     */
    List<SpaceUnit> findBySpaceId(Long spaceId);

    /**
     * Search spaceUnit by Unit ID
     * @param unitId Long
     * @return spaceUnit, wrapped in Optional
     */
    Optional<SpaceUnit> findByUnitId(Long unitId);

    /**
     * Search spaceUnit by spaceId and unitId
     * @param spaceId Long  SpaceId
     * @param unitId Long  UnitId
     * @return spaceUnit, wrapped in Optional
     */
    Optional<SpaceUnit> findBySpaceIdAndUnitId(Long spaceId, Long unitId);

    /**
     * Search space units by space id.
     * JPQL query is used.
     */
    //@Query("SELECT su.*, u.title, u.img FROM SpaceUnit su LEFT JOIN Unit u JOIN su.unit_id = u.id WHERE su.space_id = :id ")
    @Query("SELECT su FROM SpaceUnit su LEFT JOIN su.unit u WHERE su.spaceId = :id")
    List<SpaceUnit> findAllDataBySpaceId(@Param("id") Long id);
}

