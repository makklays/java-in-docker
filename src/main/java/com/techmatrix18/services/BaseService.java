package com.techmatrix18.services;

import com.techmatrix18.model.Base;
import com.techmatrix18.repositories.BaseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BaseService {

    private BaseRepository baseRepository;

    public BaseService(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    /**
     * Find a base by id
     *
     * @param id Base ID
     * @return found base
     * @throws EntityNotFoundException if the base is not found
     */
    public Base getById(Long id) {
        return baseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The base didn't find"));
    }

    /**
     * Finds all bases
     *
     * @return found all bases
     */
    public List<Base> getAll() {
        return baseRepository.findAll();
    }

    /**
     * Finds a base by title.
     *
     * @param title Base title
     * @return found base
     * @throws NoSuchElementException if the base is not found
     */
    public Base findBaseByTitle(String title) {
        return baseRepository.findByTitle(title)
                .orElseThrow(() -> new NoSuchElementException("Base with the title '" + title + "' not found"));
    }

    /**
     * Finds a base by id and by title.
     *
     * @param id Base ID
     * @param title Base title
     * @return found base
     * @throws NoSuchElementException if the base is not found
     */
    public Base findByIdAndTitle(Long id, String title) {
        return baseRepository.findByIdAndTitle(id, title)
                .orElseThrow(() -> new NoSuchElementException("Base with the id '"+id+"' and title '"+title+"' not found"));
    }

    /**
     * Search for bases whose titles contain the specified text (case-insensitive).
     */
    public List<Base> searchBasesByTitle(String titlePart) {
        return baseRepository.searchByTitleContaining(titlePart);
    }

    /**
     * Add Base
     *
     * @return boolean
     */
    public boolean addBase(Base base) {
        Base b = baseRepository.save(base);
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
    public boolean updateBase(Base base) {
        Base b = baseRepository.save(base);
        if (!b.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete Base by BaseID
     *
     * @return boolean
     */
    public boolean deleteBase(Long id) {
        Optional<Base> base = baseRepository.findById(id);
        if (base.get().getId() != null) {
            baseRepository.delete(base.get());
            return true;
        } else {
            return false;
        }
    }
}

