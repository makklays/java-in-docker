package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "card_operations")
public class CardOperation {

    /**
     * Unique user identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Card ID of User
     */
    @Column(name = "card_id")
    private Long cardId;

    /**
     * Operation date
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "operation_date", updatable = false, nullable = false)
    private Instant operationDate;

    /**
     * Operation type (payment, withdraw, deposit, etc.)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type", length = 20, nullable = false)
    private CardOperationType operationType = CardOperationType.PAYMENT;

    public enum CardOperationType {
        PAYMENT, WITHDRAW, DEPOSIT, TRANSFER, SALARY_PAYMENT, FEE
    }

    /**
     * Amount
     */
    @Column(precision = 19, scale = 2)
    private BigDecimal amount;

    /**
     * Currency of operation
     */
    @Column(name = "currency", length = 3, nullable = false)
    private String currency;

    /**
     * Description of operation
     */
    @Column(name = "description", length = 700)
    private String description;

    /**
     * Merchant Name
     */
    @Column(name = "merchant_name")
    private String merchantName;

    /**
     * Status of operation (completed, pending, failed)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 12, nullable = false)
    private CardOperationStatus status = CardOperationStatus.FAILED; // by default FAILED

    public enum CardOperationStatus {
        COMPLETED, PENDING, FAILED
    }

    /**
     * Date and time of card operation creation
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    /**
     * Date and time of card operation update
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Instant updatedAt;

    // setters and getters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Instant getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Instant operationDate) {
        this.operationDate = operationDate;
    }

    public CardOperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(CardOperationType operationType) {
        this.operationType = operationType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public CardOperationStatus getStatus() {
        return status;
    }

    public void setStatus(CardOperationStatus status) {
        this.status = status;
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
        if (!(o instanceof CardOperation that)) return false;
        return getId().equals(that.getId()) && getCardId().equals(that.getCardId()) && getOperationDate().equals(that.getOperationDate()) && getOperationType().equals(that.getOperationType()) && getAmount().equals(that.getAmount()) && getCurrency().equals(that.getCurrency()) && getDescription().equals(that.getDescription()) && getMerchantName().equals(that.getMerchantName()) && getStatus().equals(that.getStatus()) && getCreatedAt().equals(that.getCreatedAt()) && getUpdatedAt().equals(that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCardId(), getOperationDate(), getOperationType(), getAmount(), getCurrency(), getDescription(), getMerchantName(), getStatus(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "CardOperation{" +
                "id=" + id +
                ", cardId=" + cardId +
                ", operationDate=" + operationDate +
                ", operationType='" + operationType + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", description='" + description + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

