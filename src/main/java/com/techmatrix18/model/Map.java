package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    //@Column(name = "base_level_id")
    //private Long baseLevelId;

    /**
     *
     */
    @OneToOne
    @JoinColumn(name = "base_level_id")
    private BaseLevel baseLevel;

    /**
     *
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "build_started_at")
    private Instant buildStartedAt;

    /**
     *
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    /**
     *
     */
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

    /*public Long getBaseLevelId() {
        return baseLevelId;
    }*/

    /*public void setBaseLevelId(Long baseLevelId) {
        this.baseLevelId = baseLevelId;
    }*/

    public BaseLevel getBaseLevel() { return baseLevel; }

    public void setBaseLevel(BaseLevel baseLevel) {
        this.baseLevel = baseLevel;
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

    public Instant getBuildStartedAt() { return buildStartedAt; }

    public void setBuildStartedAt(Instant buildStartedAt) { this.buildStartedAt = buildStartedAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Map map)) return false;
        return getId().equals(map.getId()) && getSpaceId().equals(map.getSpaceId()) && getSector().equals(map.getSector()) && getBaseLevel().equals(map.getBaseLevel()) && getCreatedAt().equals(map.getCreatedAt()) && getUpdatedAt().equals(map.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSpaceId(), getSector(), getBaseLevel(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "Map{" +
                "id=" + id +
                ", spaceId=" + spaceId +
                ", sector=" + sector +
                ", baseLevel=" + baseLevel +
                ", buildStartedAt=" + buildStartedAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     *
     *
     * @param seconds
     * @return
     */
    public boolean isBuildingNow(int seconds) {
        if (buildStartedAt == null || seconds <= 0) {
            return false;
        }

        Instant endTime = buildStartedAt.plusSeconds(seconds);
        return Instant.now().isBefore(endTime);
    }

    /**
     *
     */
    public int secondsToFinish(int seconds) {
        if (buildStartedAt == null || seconds <= 0) {
            return 0;
        }

        Instant endTime = buildStartedAt.plusSeconds(seconds);
        Duration remaining = Duration.between(Instant.now(), endTime);

        // Если уже закончилось — вернуть 0
        return (int) Math.max(remaining.getSeconds(), 0);
    }
}

