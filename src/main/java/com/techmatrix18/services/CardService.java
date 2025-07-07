package com.techmatrix18.services;

import com.techmatrix18.model.Blog;
import com.techmatrix18.model.Card;
import com.techmatrix18.repositories.CardRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * This is CardService
 *
 * @company for TechMatrix18
 * @author Alexander Kuziv
 * @since 07.07.205
 * @version 0.0.1
 */

@Service
public class CardService {

    private CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * List of all cards from User ID
     *
     * @param userId
     * @return
     */
    public List<Card> getAllCardsByUserId (Long userId) {
        return cardRepository.findByUserId(userId);
    }

    /**
     * Add Card
     *
     * @return boolean
     */
    public boolean addCard(Card card) {
        Card c = cardRepository.save(card);
        if (c.getId() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Edit Card
     *
     * @return boolean
     */
    public boolean updateCard(Card card) {
        Card c = cardRepository.save(card);
        if (c.getId() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete Card by CardID
     *
     * @return boolean
     */
    public boolean deleteCard(Long id) {
        Optional<Card> card = cardRepository.findById(id);
        if (card.get().getId() != null) {
            cardRepository.delete(card.get());
            return true;
        } else {
            return false;
        }
    }
}

