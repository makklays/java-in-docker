package com.techmatrix18.services;

import com.techmatrix18.model.BaseLevel;
import com.techmatrix18.repositories.BaseLevelRepository;
import com.techmatrix18.repositories.BaseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BaseLevelService {

    private BaseRepository baseRepository;
    private BaseLevelRepository baseLevelRepository;

    public BaseLevelService(BaseRepository baseRepository, BaseLevelRepository baseLevelRepository) {
        this.baseRepository = baseRepository;
        this.baseLevelRepository = baseLevelRepository;
    }

    /**
     * Find a BaseLevel by id
     *
     * @param id BaseLevel ID
     * @return found BaseLevel
     * @throws EntityNotFoundException if the BaseLevel is not found
     */
    public BaseLevel getById(Long id) {
        return baseLevelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The BaseLevel didn't find"));
    }

    /**
     * Finds all BaseLevels
     *
     * @return found all BaseLevels
     */
    public List<BaseLevel> getAll() {
        return baseLevelRepository.findAll();
    }

    public List<BaseLevel> getAllByBaseId(Long baseId) {
        return baseLevelRepository.findByBaseId(baseId);
    }
    /**
     * Add BaseLevel
     *
     * @return boolean
     */
    public boolean addBaseLevel(BaseLevel baseLevel) {
        BaseLevel b = baseLevelRepository.save(baseLevel);
        if (!b.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Edit BaseLevel
     *
     * @return boolean
     */
    public boolean updateBaseLevel(BaseLevel baseLevel) {
        BaseLevel b = baseLevelRepository.save(baseLevel);
        if (!b.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete BaseLevel by BaseLevelID
     *
     * @return boolean
     */
    public boolean deleteBaseLevel(Long id) {
        Optional<BaseLevel> baseLevel = baseLevelRepository.findById(id);
        if (baseLevel.get().getId() != null) {
            baseLevelRepository.delete(baseLevel.get());
            return true;
        } else {
            return false;
        }
    }
}

