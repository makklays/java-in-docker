package com.techmatrix18.repositories;

import com.techmatrix18.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * This is CardRepository
 *
 * @company for TechMatrix18
 * @author Alexander Kuziv
 * @since 07.07.205
 * @version 0.0.1
 */

public interface CardRepository extends JpaRepository<Card, Long> {

    /**
     * Search Card by id
     * @param id Card ID
     * @return Card, wrapped in Optional
     */
    Optional<Card> findById(Long id);

    /**
     * Search Card by Card number
     * @param cardNumber Card Number
     * @return Card, wrapped in Optional
     */
    Optional<Card> findByCardNumber(String cardNumber);

    /**
     * Search Card by Bank name
     * @param bankName Bank Name
     * @return Card, wrapped in List
     */
    List<Card> findByBankName(String bankName);

    /**
     * Search Card by type (VISA, MASTER CARD, etc.)
     * @param type Type of card
     * @return Card, wrapped in List
     */
    List<Card> findByType(String type);

    /**
     * Search Card by status (ACTIVE, BLOCKED, EXPIRED)
     * @param status Status of card
     * @return Card, wrapped in List
     */
    List<Card> findByStatus(String status);

    /**
     * Get list of Card
     * @return Card, wrapped in List
     */
    List<Card> findAll();

    /**
     * Search Card by userId
     * @param userId Card
     * @return Card, wrapped in List
     */
    List<Card> findByUserId(Long userId);

    /**
     * Search Card by userId and status (ACTIVE, BLOCKED, EXPIRED)
     * @param userId Card
     * @param status Card
     * @return Card, wrapped in List
     */
    List<Card> findByUserIdAndStatus(Long userId, String status);
}

