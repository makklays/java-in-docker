package com.techmatrix18.services;

import com.techmatrix18.model.Unit;
import com.techmatrix18.repositories.UnitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UnitService {
    private UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    /**
     * Find a base by id
     *
     * @param id Base ID
     * @return found base
     * @throws EntityNotFoundException if the base is not found
     */
    public Unit getById(Long id) {
        return unitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The unit didn't find"));
    }

    /**
     * Finds all units
     *
     * @return found all units
     */
    public List<Unit> getAll() {
        return unitRepository.findAllByOrderByTypeAscLevelAsc();
    }

    /**
     * Finds all units from BioLab
     *
     * @return found all units from BioLab
     */
    public List<Unit> getAllFromBiolab() {
        return unitRepository.findAllByTypeBiolab();
    }

    /**
     * Finds all units from Hangar
     *
     * @return found all units from Hangar
     */
    public List<Unit> getAllFromHangar() {
        return unitRepository.findAllByTypeHangar();
    }

    /**
     * Finds a unit by title.
     *
     * @param title Unit title
     * @return found unit
     * @throws NoSuchElementException if the unit is not found
     */
    public Unit findUnitByTitle(String title) {
        return unitRepository.findByTitle(title)
                .orElseThrow(() -> new NoSuchElementException("Unit with the title '" + title + "' not found"));
    }

    /**
     * Finds a unit by id and by title.
     *
     * @param id Unit ID
     * @param title Unit title
     * @return found unit
     * @throws NoSuchElementException if the unit is not found
     */
    public Unit findByIdAndTitle(Long id, String title) {
        return unitRepository.findByIdAndTitle(id, title)
                .orElseThrow(() -> new NoSuchElementException("Unit with the id '"+id+"' and title '"+title+"' not found"));
    }

    /**
     * Search for units whose titles contain the specified text (case-insensitive).
     */
    public List<Unit> searchUnitsByTitle(String titlePart) {
        return unitRepository.searchByTitleContaining(titlePart);
    }

    /**
     * Add Base
     *
     * @return boolean
     */
    public boolean addUnit(Unit unit) {
        Unit b = unitRepository.save(unit);
        if (!b.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Edit Base
     *
     * @return boolean
     */
    public boolean updateUnit(Unit unit) {
        Unit b = unitRepository.save(unit);
        if (!b.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete Unit by UnitID
     *
     * @return boolean
     */
    public boolean deleteUnit(Long id) {
        Optional<Unit> unit = unitRepository.findById(id);
        if (unit.get().getId() != null) {
            unitRepository.delete(unit.get());
            return true;
        } else {
            return false;
        }
    }
}
