package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;
import java.util.Objects;

/**
 * An entity representing a map in the system.
 *
 * Corresponds to the 'maps' table in the database.
 *
 * @author Alexander Kuziv
 * @since 24-04-2025
 * @version 0.0.1
 */

@Entity
@Table(name = "maps")
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Space ID on the map
     */
    @Column(name = "space_id")
    private Long spaceId;

    /**
     * Number sector on the map
     */
    @Column(name = "sector")
    private Long sector;

    /**
     * BaseLevel ID on the map
     */
    @Column(name = "base_level_id")
    private Long baseLevelId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Instant updatedAt;

    // Getters and setters

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

    public Long getSector() {
        return sector;
    }

    public void setSector(Long sector) {
        this.sector = sector;
    }

    public Long getBaseLevelId() {
        return baseLevelId;
    }

    public void setBaseLevelId(Long baseLevelId) {
        this.baseLevelId = baseLevelId;
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
        if (!(o instanceof Map map)) return false;
        return getId().equals(map.getId()) && getSpaceId().equals(map.getSpaceId()) && getSector().equals(map.getSector()) && getBaseLevelId().equals(map.getBaseLevelId()) && getCreatedAt().equals(map.getCreatedAt()) && Objects.equals(getUpdatedAt(), map.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSpaceId(), getSector(), getBaseLevelId(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "Map{" +
                "id=" + id +
                ", spaceId=" + spaceId +
                ", sector=" + sector +
                ", baseLevelId=" + baseLevelId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

