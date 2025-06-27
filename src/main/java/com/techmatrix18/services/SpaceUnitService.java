package com.techmatrix18.services;

import com.techmatrix18.model.SpaceUnit;
import com.techmatrix18.model.Unit;
import com.techmatrix18.repositories.SpaceUnitRepository;
import com.techmatrix18.repositories.UnitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceUnitService {

    private SpaceUnitRepository spaceUnitRepository;

    public SpaceUnitService(SpaceUnitRepository spaceUnitRepository) {
        this.spaceUnitRepository = spaceUnitRepository;
    }

    /**
     * Find a space unit by id
     *
     * @param id SpaceUnit ID
     * @return found space unit
     * @throws EntityNotFoundException if the space unit is not found
     */
    public SpaceUnit getById(Long id) {
        return spaceUnitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The space unit didn't find"));
    }

    /**
     * Find a space unit by spaceId and unitId
     *
     * @param spaceId Long
     * @param unitId Long
     * @return SpaceUnit on Optional
     */
    public Optional<SpaceUnit> getBySpaceIdAndUnitId(Long spaceId, Long unitId) {
        return spaceUnitRepository.findBySpaceIdAndUnitId(spaceId, unitId);
    }

    /**
     * Find all space units by spaceId
     *
     * @param spaceId Long
     * @return SpaceUnit on List
     */
    public List<SpaceUnit> getBySpaceId(Long spaceId) {
        return spaceUnitRepository.findBySpaceId(spaceId);
    }

    /**
     * Find all data space units by spaceId
     *
     * @param spaceId Long
     * @return SpaceUnit on List
     */
    public List<SpaceUnit> getAllDataBySpaceId(Long spaceId) {
        return spaceUnitRepository.findAllDataBySpaceId(spaceId);
    }

    /**
     * Add SpaceUnit
     *
     * @return boolean
     */
    public boolean addSpaceUnit(SpaceUnit spaceUnit) {
        SpaceUnit s = spaceUnitRepository.save(spaceUnit);
        if (s.getSpaceId() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Edit SpaceUnit
     *
     * @return boolean
     */
    public boolean updateSpaceUnit(SpaceUnit spaceUnit) {
        SpaceUnit s = spaceUnitRepository.save(spaceUnit);
        if (s.getSpaceId() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete SpaceUnit by SpaceUnitID
     *
     * @return boolean
     */
    public boolean deleteSpaceUnit(Long id) {
        Optional<SpaceUnit> spaceUnit = spaceUnitRepository.findById(id);
        if (spaceUnit.get().getSpaceId() != null) {
            spaceUnitRepository.delete(spaceUnit.get());
            return true;
        } else {
            return false;
        }
    }
}

