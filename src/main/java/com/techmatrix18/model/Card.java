package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * An entity representing a Card in the system.
 *
 * Corresponds to the 'cards' table in the database.
 *
 * @author Alexander Kuziv
 * @since 24-04-2025
 * @version 0.0.1
 */

@Entity
@Table(name = "cards")
public class Card {

    /**
     * Unique user identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * Number of card
     */
    @Column(name = "card_number", length = 19, unique = true, nullable = false)
    private String cardNumber;

    /**
     * Name of holder
     */
    @Column(name = "cardholder_name", nullable = false)
    private String cardholderName;

    @Column(name = "expiration_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant expirationDate;

    /**
     * CVV of card
     */
    @Column(name = "cvv", length = 4, nullable = false)
    private String cvv;

    /**
     * PIN of card
     */
    @Column(name = "pin", length = 6, nullable = false)
    private String pin;

    /**
     * Balance of card
     */
    @Column(name = "balance", precision = 15, scale = 2, nullable = false)
    private BigDecimal balance;

    /**
     * Credit limit of card
     */
    @Column(name = "credit_limit", precision = 15, scale = 2, nullable = false)
    private BigDecimal creditLimit;

    /**
     * Currency of card
     */
    @Column(name = "currency", length = 3, nullable = false)
    private String currency;

    /**
     * Status of card
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10, nullable = false)
    private CardStatus status = CardStatus.EXPIRED; // by default EXPIRED

    public enum CardStatus {
        ACTIVE, BLOCKED, EXPIRED
    }
    //-------------------------

    /**
     * Name bank of card
     */
    @Column(name = "bank_name", length = 255, nullable = true)
    private String bankName;

    /**
     * Type of card
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 15, nullable = false)
    private CardType type = CardType.VISA; // by default VISA

    public enum CardType {
        VISA, MASTERCARD, AMEX, MIR
    }

    /**
     * Date of create of card
     * дата выпуска карты
     */
    @Column(name = "issue_date")
    private Instant issueDate;

    /**
     * Bonuses of card
     * бонусные баллы (если есть программа лояльности)
     */
    @Column(name = "reward_points")
    private Integer rewardPoints;

    /**
     * % deposit of card
     * процентная ставка
     */
    @Column(name = "interest_rate", precision = 5, scale = 2, nullable = false)
    private BigDecimal interestRate;

    /**
     * Is a card contactless ?
     * поддержка бесконтактной оплаты
     */
    @Column(name = "contactless")
    private Boolean contactless; // 0 or 1

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Instant updatedAt;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public Instant getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Instant issueDate) {
        this.issueDate = issueDate;
    }

    public Integer getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(Integer rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Boolean getContactless() {
        return contactless;
    }

    public void setContactless(Boolean contactless) {
        this.contactless = contactless;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        return getId().equals(card.getId()) && getUserId().equals(card.getUserId()) && getCardNumber().equals(card.getCardNumber()) && getCardholderName().equals(card.getCardholderName()) && getExpirationDate().equals(card.getExpirationDate()) && getCvv().equals(card.getCvv()) && getPin().equals(card.getPin()) && getBalance().equals(card.getBalance()) && getCreditLimit().equals(card.getCreditLimit()) && getCurrency().equals(card.getCurrency()) && getStatus() == card.getStatus() && getBankName().equals(card.getBankName()) && getType() == card.getType() && getIssueDate().equals(card.getIssueDate()) && getRewardPoints().equals(card.getRewardPoints()) && getInterestRate().equals(card.getInterestRate()) && getContactless().equals(card.getContactless()) && getCreatedAt().equals(card.getCreatedAt()) && getUpdatedAt().equals(card.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getCardNumber(), getCardholderName(), getExpirationDate(), getCvv(), getPin(), getBalance(), getCreditLimit(), getCurrency(), getStatus(), getBankName(), getType(), getIssueDate(), getRewardPoints(), getInterestRate(), getContactless(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "Card {" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardholderName='" + cardholderName + '\'' +
                ", expirationDate=" + expirationDate +
                ", cvv='" + cvv + '\'' +
                ", pin='" + pin + '\'' +
                ", balance=" + balance +
                ", creditLimit=" + creditLimit +
                ", currency='" + currency + '\'' +
                ", status=" + status +
                ", bankName='" + bankName + '\'' +
                ", type=" + type +
                ", issueDate=" + issueDate +
                ", rewardPoints=" + rewardPoints +
                ", interestRate=" + interestRate +
                ", contactless=" + contactless +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

