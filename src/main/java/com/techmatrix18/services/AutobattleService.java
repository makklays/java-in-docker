package com.techmatrix18.services;

import com.techmatrix18.model.Autobattle;
import com.techmatrix18.repositories.AutobattleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;

@Service
public class AutobattleService {

    Logger log = Logger.getLogger(BaseLevelService.class.getName());

    private AutobattleRepository autobattleRepository;

    public AutobattleService(AutobattleRepository autobattleRepository) {
        this.autobattleRepository = autobattleRepository;
    }

    /**
     * Find a Autobattle by id
     *
     * @param id Autobattle ID
     * @return found Autobattle
     * @throws EntityNotFoundException if the Autobattle is not found
     */
    public Autobattle getById(Long id) {
        return autobattleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The Autobattle didn't find"));
    }

    /**
     * Finds all Autobattles
     *
     * @return found all Autobattles
     */
    public List<Autobattle> getAll() {
        return autobattleRepository.findAll();
    }

    public List<Autobattle> getAllBySpaceId(Long spaceId) {
        return autobattleRepository.findBySpaceId(spaceId);
    }
}

