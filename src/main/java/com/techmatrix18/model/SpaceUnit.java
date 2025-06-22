package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;

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
    @Column(name = "unit_id")
    private Long unitId;

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
    private int isTraining;

    /**
     * count of Unit
     */
    @Column(name = "count")
    private int count;

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
}

