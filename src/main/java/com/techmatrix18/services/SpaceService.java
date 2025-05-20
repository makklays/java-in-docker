package com.techmatrix18.services;

import com.techmatrix18.model.Space;
import com.techmatrix18.repositories.SpaceRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * This is SpaceService
 *
 * @company for TechMatrix18
 * @author Alexander Kuziv
 * @since 19.05.205
 * @version 0.0.1
 */

@Service
public class SpaceService {

    private SpaceRepository spaceRepository;

    public SpaceService(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    /**
     * Get all spaces
     *
     * @param userId
     * @return
     */
    public List<Space> getAllSpaces(Long userId) {
        return spaceRepository.findByUserIdOrderByCreatedAtAsc(userId);
    }

    /**
     * Get Space by userId
     *
     * @param userId
     * @return Optional<Space>
     */
    public Optional<Space> getSpaceByUserId(Long userId) {
        return spaceRepository.findByUserId(userId);
    }

    /**
     * Add Space
     *
     * @return boolean
     */
    public boolean addSpace(Space space) {
        Space s = spaceRepository.save(space);
        if (s.getId() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Edit Space
     *
     * @return boolean
     */
    public boolean updateSpace(Space space) {
        Space s = spaceRepository.save(space);
        if (s.getId() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete Space by SpaceID
     *
     * @return boolean
     */
    public boolean deleteSpace(Long id) {
        Optional<Space> space = spaceRepository.findById(id);
        if (space.get().getId() != null) {
            spaceRepository.delete(space.get());
            return true;
        } else {
            return false;
        }
    }
}

