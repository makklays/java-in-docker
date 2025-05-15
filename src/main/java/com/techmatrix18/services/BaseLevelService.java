package com.techmatrix18.services;

import com.techmatrix18.controllers.web.UserViewController;
import com.techmatrix18.model.Base;
import com.techmatrix18.model.BaseLevel;
import com.techmatrix18.repositories.BaseLevelRepository;
import com.techmatrix18.repositories.BaseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BaseLevelService {

    Logger log = Logger.getLogger(BaseLevelService.class.getName());

    private BaseService baseService;
    private BaseLevelRepository baseLevelRepository;

    public BaseLevelService(BaseService baseService, BaseLevelRepository baseLevelRepository) {
        this.baseService = baseService;
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
     *
     */
    public long getCountBaseLevels(Long baseId) {
        return baseLevelRepository.countByBaseId(baseId);
    }

    /**
     *
     */
    public BaseLevel getNext(Long baseId, Integer level) {
        Base base = baseService.getById(baseId);

        List<BaseLevel> list = base.getBaseLevels();
        BaseLevel nextBaseLevel = null;

        log.info("=== list.size() ====> " + list.size());

        if ((level + 1) <= list.size()) {
            for (BaseLevel baseLevel : list) {
                if (baseLevel.getLevel() == (level + 1)) {
                    nextBaseLevel = baseLevel;
                    break;
                }
            }
        }

        return nextBaseLevel;
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
        /*Optional<BaseLevel> baseLevel = baseLevelRepository.findById(id);
        try {
            if (baseLevel.get() != null && baseLevel.get().getId() != null) {
                baseLevelRepository.delete(baseLevel.get());
                return true;
            } else {
                return false;
            }
        } catch (EntityNotFoundException ex) {
            return false;
        }*/

        BaseLevel baseLevel = baseLevelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BaseLevel with id " + id + " not found"));
        baseLevelRepository.delete(baseLevel);

        return true;
    }

    public List<BaseLevel> getAllByBaseIdOrderByLevelAsc(Long baseId) {
        return baseLevelRepository.findByBaseIdOrderByLevelAsc(baseId);
    }
}

