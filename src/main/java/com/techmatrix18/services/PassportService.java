package com.techmatrix18.services;

import com.techmatrix18.model.Passport;
import com.techmatrix18.repositories.PassportRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * This is PassportService
 *
 * @company for TechMatrix18
 * @author Alexander Kuziv
 * @since 28.06.205
 * @version 0.0.1
 */

@Service
public class PassportService {

    private PassportRepository passportRepository;

    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    public Optional<Passport> getPassportById(Long id) {
        return passportRepository.findById(id);
    }

    public List<Passport> getAllPassports() {
        return passportRepository.findAll();
    }

    public Optional<Passport> getPassportByNumber(String number) {
        return passportRepository.findByNumber(number);
    }

    public Optional<Passport> getPassportByUserId(Long userId) {
        return passportRepository.findByUserId(userId);
    }

    /**
     * Add Passport
     *
     * @return boolean
     */
    public boolean addPassport(Passport passport) {
        Passport p = passportRepository.save(passport);
        if (p.getId() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Edit Passport
     *
     * @return boolean
     */
    public boolean updatePassport(Passport passport) {
        Passport p = passportRepository.save(passport);
        if (p.getId() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete Passport by PassportID
     *
     * @return boolean
     */
    public boolean deletePassport(Long id) {
        Optional<Passport> passport = passportRepository.findById(id);
        if (passport.get().getId() != null) {
            passportRepository.delete(passport.get());
            return true;
        } else {
            return false;
        }
    }
}

