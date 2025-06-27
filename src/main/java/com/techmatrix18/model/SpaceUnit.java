package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;
import java.util.Objects;

/**
 * An entity representing a SpaceUnit in the system.
 *
 * Corresponds to the 'space_units' table in the database.
 *
 * @author Alexander Kuziv
 * @since 22-06-2025
 * @version 0.0.1
 */

@Entity
@Table(name = "space_units")
public class SpaceUnit {

    /**
     * Unique user identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Space ID from Spaces
     */
    @Column(name = "space_id")
    private Long spaceId;

    /**
     * Unit ID from Units
     */
    @Column(name = "unit_id", insertable = false, updatable = false)
    private Long unitId;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    /**
     * time of start training new Unit
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "training_started_at")
    private Instant trainingStartedAt;

    /**
     * Is training a new Unit now ? (1 or 0)
     */
    @Column(name = "is_training")
    private String isTraining;

    /**
     * count of Unit
     */
    @Column(name = "count")
    private Integer count;

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

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Instant getTrainingStartedAt() {
        return trainingStartedAt;
    }

    public void setTrainingStartedAt(Instant trainingStartedAt) {
        this.trainingStartedAt = trainingStartedAt;
    }

    public String getIsTraining() {
        return isTraining;
    }

    public void setIsTraining(String isTraining) {
        this.isTraining = isTraining;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
        if (!(o instanceof SpaceUnit spaceUnit)) return false;
        return isTraining == spaceUnit.isTraining && count == spaceUnit.count && id.equals(spaceUnit.id) && spaceId.equals(spaceUnit.spaceId) && unitId.equals(spaceUnit.unitId) && trainingStartedAt.equals(spaceUnit.trainingStartedAt) && createdAt.equals(spaceUnit.createdAt) && updatedAt.equals(spaceUnit.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, spaceId, unitId, trainingStartedAt, isTraining, count, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "SpaceUnit{" +
                "id=" + id +
                ", spaceId=" + spaceId +
                ", unitId=" + unitId +
                ", unit=" + unit +
                ", trainingStartedAt=" + trainingStartedAt +
                ", isTraining='" + isTraining + '\'' +
                ", count=" + count +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

