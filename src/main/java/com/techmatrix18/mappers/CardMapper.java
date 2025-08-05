package com.techmatrix18.mappers;

import com.techmatrix18.dto.CardDto;
import com.techmatrix18.model.Card;

/**
 * Class Card Mapper
 *
 * @author Alexander Kuziv
 * @since 05-08-2025
 * @version 0.0.1
 */

public class CardMapper {

    public static CardDto toDto(Card card) {
        if (card == null) {
            return null;
        }
        CardDto dto = new CardDto();
        dto.setId(card.getId());
        dto.setCardNumber(card.getCardNumber());
        dto.setCardholderName(card.getCardholderName());
        dto.setExpirationDate(card.getExpirationDate());
        dto.setCvv(card.getCvv());
        dto.setBalance(card.getBalance());
        dto.setCurrency(card.getCurrency());
        return dto;
    }

    public static Card toCard(CardDto cardDto) {
        if (cardDto == null) {
            return null;
        }
        Card card = new Card();
        card.setId(cardDto.getId());
        card.setCardNumber(cardDto.getCardNumber());
        card.setCardholderName(cardDto.getCardholderName());
        card.setExpirationDate(cardDto.getExpirationDate());
        card.setCvv(cardDto.getCvv());
        card.setBalance(cardDto.getBalance());
        card.setCurrency(cardDto.getCurrency());
        return card;
    }
}

