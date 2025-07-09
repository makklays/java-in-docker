package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * An entity representing an order in the system.
 *
 * Corresponds to the 'orders' table in the database.
 *
 * @author Alexander Kuziv - makklays@gmail.com
 * @since 09-07-2025
 * @version 0.0.1
 */

@Entity
@Table(name = "orders")
public class Order {

    /**
     * Unique user identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * Artifact ID
     */
    @Column(name = "artifact_id")
    private Long artifactId;

    /**
     * адрес кошелька покупателя
     */
    @Column(name = "buyer_wallet_address")
    private String buyerWalletAddress;

    /**
     * адрес кошелька продавца (куда ждать оплату)
     */
    @Column(name = "merchant_wallet_address")
    private String merchantWalletAddress;

    /**
     * amount to pay
     */
    @Column(name = "amount")
    private BigDecimal amount;

    /**
     * хэш транзакции из блокчейна (после оплаты)
     */
    @Column(name = "transaction_hash")
    private String transactionHash;

    /**
     * Payment status (PENDING, CONFIRMED, FAILED)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", length = 18, nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.FAILED;

    public enum PaymentStatus {
        PENDING,    // Ожидает оплаты
        CONFIRMED,  // Оплата подтверждена
        FAILED      // Оплата не прошла / отменена
    }

    /**
     * Date and time of order creation
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    /**
     * Date and time of order confirmation
     */
    @UpdateTimestamp // ?
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "confirmed_at")
    private Instant confirmedAt;

    // setters and getters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Long artifactId) {
        this.artifactId = artifactId;
    }

    public String getBuyerWalletAddress() {
        return buyerWalletAddress;
    }

    public void setBuyerWalletAddress(String buyerWalletAddress) {
        this.buyerWalletAddress = buyerWalletAddress;
    }

    public String getMerchantWalletAddress() {
        return merchantWalletAddress;
    }

    public void setMerchantWalletAddress(String merchantWalletAddress) {
        this.merchantWalletAddress = merchantWalletAddress;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(Instant confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return getId().equals(order.getId()) && getUserId().equals(order.getUserId()) && getArtifactId().equals(order.getArtifactId()) && getBuyerWalletAddress().equals(order.getBuyerWalletAddress()) && getMerchantWalletAddress().equals(order.getMerchantWalletAddress()) && getAmount().equals(order.getAmount()) && getTransactionHash().equals(order.getTransactionHash()) && getPaymentStatus() == order.getPaymentStatus() && getCreatedAt().equals(order.getCreatedAt()) && getConfirmedAt().equals(order.getConfirmedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getArtifactId(), getBuyerWalletAddress(), getMerchantWalletAddress(), getAmount(), getTransactionHash(), getPaymentStatus(), getCreatedAt(), getConfirmedAt());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", artifactId=" + artifactId +
                ", buyerWalletAddress='" + buyerWalletAddress + '\'' +
                ", merchantWalletAddress='" + merchantWalletAddress + '\'' +
                ", amount=" + amount +
                ", transactionHash='" + transactionHash + '\'' +
                ", paymentStatus=" + paymentStatus +
                ", createdAt=" + createdAt +
                ", confirmedAt=" + confirmedAt +
                '}';
    }
}

