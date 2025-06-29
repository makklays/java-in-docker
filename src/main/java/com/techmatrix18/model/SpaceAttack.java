package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "space_attacks")
public class SpaceAttack {

    /**
     * Unique user identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Space Id
     */
    @Column(name = "space_id", nullable = false)
    private Long spaceId;

    /**
     * Target Space Id
     */
    @Column(name = "target_space_id", nullable = false)
    private Long targetSpaceId;

    /**
     * Start Time of auto battle
     */
    @Column(name = "start_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant startTime;

    /**
     * End Time of auto battle
     */
    @Column(name = "end_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant endTime;

    /**
     * Status of auto battle
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private SpaceAttack.SpaceAttackStatus status = SpaceAttack.SpaceAttackStatus.WAITING; // by default WAITING

    public enum SpaceAttackStatus {
        WAITING, IN_PROGRESS, FINISHED
    }

    /**
     * Result of auto battle
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "result", length = 12, nullable = false)
    private SpaceAttack.SpaceAttackResult result = SpaceAttack.SpaceAttackResult.DRAW; // by default DRAW

    public enum SpaceAttackResult {
        WIN, LOSE, DRAW
    }

    /**
     * Logs of message
     */
    @Column(name = "logs")
    private String logs;

    /**
     * Date and time of auto battle creation
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    /**
     * Date and time of auto battle update
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

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public Long getTargetSpaceId() {
        return targetSpaceId;
    }

    public void setTargetSpaceId(Long targetSpaceId) {
        this.targetSpaceId = targetSpaceId;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public SpaceAttackStatus getStatus() {
        return status;
    }

    public void setStatus(SpaceAttackStatus status) {
        this.status = status;
    }

    public SpaceAttackResult getResult() {
        return result;
    }

    public void setResult(SpaceAttackResult result) {
        this.result = result;
    }

    public String getLogs() {
        return logs;
    }

    public void setLogs(String logs) {
        this.logs = logs;
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
        if (!(o instanceof SpaceAttack that)) return false;
        return getId().equals(that.getId()) && getSpaceId().equals(that.getSpaceId()) && getTargetSpaceId().equals(that.getTargetSpaceId()) && getStartTime().equals(that.getStartTime()) && getEndTime().equals(that.getEndTime()) && getStatus() == that.getStatus() && getResult() == that.getResult() && getLogs().equals(that.getLogs()) && getCreatedAt().equals(that.getCreatedAt()) && getUpdatedAt().equals(that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSpaceId(), getTargetSpaceId(), getStartTime(), getEndTime(), getStatus(), getResult(), getLogs(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "SpaceAttack{" +
                "id=" + id +
                ", spaceId=" + spaceId +
                ", targetSpaceId=" + targetSpaceId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", result=" + result +
                ", logs='" + logs + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

