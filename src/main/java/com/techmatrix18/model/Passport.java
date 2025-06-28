package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;
import java.util.Objects;

/**
 * An entity representing a passport in the system.
 *
 * Corresponds to the 'passports' table in the database.
 *
 * @author Alexander Kuziv
 * @since 28-06-2025
 * @version 0.0.1
 */

@Entity
@Table(name = "passports")
public class Passport {

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
     * Number of Passport
     */
    @Column(name = "number")
    private String number;

    /**
     * Gave the passport of date and time
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_at", updatable = false, nullable = false)
    private Instant dateAt;

    /**
     * Who gave passport
     */
    @Column(name = "who_gave_it")
    private String whoGaveIt;

    /**
     * Date and time of base creation
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    /**
     * Date and time of base update
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Instant updatedAt;

    // getters and setters

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Instant getDateAt() {
        return dateAt;
    }

    public void setDateAt(Instant dateAt) {
        this.dateAt = dateAt;
    }

    public String getWhoGaveIt() {
        return whoGaveIt;
    }

    public void setWhoGaveIt(String whoGaveIt) {
        this.whoGaveIt = whoGaveIt;
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
        if (!(o instanceof Passport passport)) return false;
        return getId().equals(passport.getId()) && getUserId().equals(passport.getUserId()) && getNumber().equals(passport.getNumber()) && getDateAt().equals(passport.getDateAt()) && getWhoGaveIt().equals(passport.getWhoGaveIt()) && getCreatedAt().equals(passport.getCreatedAt()) && getUpdatedAt().equals(passport.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getNumber(), getDateAt(), getWhoGaveIt(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", userId=" + userId +
                ", number='" + number + '\'' +
                ", dateAt=" + dateAt +
                ", whoGaveIt='" + whoGaveIt + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

