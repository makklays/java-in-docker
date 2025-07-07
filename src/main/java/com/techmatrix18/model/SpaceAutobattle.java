package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "space_autobattles")
public class SpaceAutobattle {

    /**
     * Unique space autobattle identifier (primary key).
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
     * Autobattle Id
     */
    @Column(name = "autobattle_id", nullable = false)
    private Long autobattleId;

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
    private BattleStatus status = BattleStatus.WAITING; // by default WAITING

    public enum BattleStatus {
        WAITING, IN_PROGRESS, FINISHED
    }

    /**
     * Result of auto battle
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "result", length = 12, nullable = false)
    private SpaceAutobattle.BattleResult result = SpaceAutobattle.BattleResult.DRAW; // by default DRAW

    public enum BattleResult {
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

    public Long getAutobattleId() {
        return autobattleId;
    }

    public void setAutobattleId(Long autobattleId) {
        this.autobattleId = autobattleId;
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

    public BattleStatus getStatus() {
        return status;
    }

    public void setStatus(BattleStatus status) {
        this.status = status;
    }

    public BattleResult getResult() {
        return result;
    }

    public void setResult(BattleResult result) {
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
        if (!(o instanceof SpaceAutobattle that)) return false;
        return getId().equals(that.getId()) && getSpaceId().equals(that.getSpaceId()) && getAutobattleId().equals(that.getAutobattleId()) && getStartTime().equals(that.getStartTime()) && getEndTime().equals(that.getEndTime()) && getStatus() == that.getStatus() && getResult() == that.getResult() && getLogs().equals(that.getLogs()) && getCreatedAt().equals(that.getCreatedAt()) && getUpdatedAt().equals(that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSpaceId(), getAutobattleId(), getStartTime(), getEndTime(), getStatus(), getResult(), getLogs(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "SpaceAutobattle{" +
                "id=" + id +
                ", spaceId=" + spaceId +
                ", autobattleId=" + autobattleId +
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

