package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "space_artifacts")
public class SpaceArtifact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Space ID
     */
    @Column(name = "space_id")
    private Long spaceId;

    /**
     * Artifact ID
     */
    @Column(name = "artifact_id")
    private Long artifactId;

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

    public Long getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Long artifactId) {
        this.artifactId = artifactId;
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
        if (!(o instanceof SpaceArtifact that)) return false;
        return getId().equals(that.getId()) && getSpaceId().equals(that.getSpaceId()) && getArtifactId().equals(that.getArtifactId()) && getCreatedAt().equals(that.getCreatedAt()) && getUpdatedAt().equals(that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSpaceId(), getArtifactId(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "SpaceArtifact{" +
                "id=" + id +
                ", spaceId=" + spaceId +
                ", artifactId=" + artifactId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

