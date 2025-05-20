package com.techmatrix18.services;

import com.techmatrix18.model.Base;
import com.techmatrix18.model.Map;
import com.techmatrix18.repositories.MapRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * This is MapService
 *
 * @company for TechMatrix18
 * @author Alexander Kuziv
 * @since 13.05.205
 * @version 0.0.1
 */

@Service
public class MapService {

    private MapRepository mapRepository;

    public MapService(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    public List<Map> getAllSectors(Long spaceId) {
        return mapRepository.findBySpaceIdOrderBySectorAsc(spaceId);
    }

    // Sector la sorpresa =)
    public Optional<Map> getSector(Long spaceId, Integer sector) {
        return mapRepository.findBySpaceIdAndSector(spaceId, sector);
    }

    /**
     * Add Map
     *
     * @return boolean
     */
    public boolean addMap(Map map) {
        Map m = mapRepository.save(map);
        if (m.getId() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Edit Map
     *
     * @return boolean
     */
    public boolean updateMap(Map map) {
        Map m = mapRepository.save(map);
        if (m.getId() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete Map by MapID
     *
     * @return boolean
     */
    public boolean deleteMap(Long id) {
        Optional<Map> map = mapRepository.findById(id);
        if (map.get().getId() != null) {
            mapRepository.delete(map.get());
            return true;
        } else {
            return false;
        }
    }
}

