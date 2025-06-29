package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "autobattles")
public class Autobattle {

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
     * Required building level TI-Centro of Map of Space
     */
    @Column(name = "req_building_level")
    private Integer reqBuildingLevel;

    /**
     * Required count of units of Space
     */
    @Column(name = "req_units_count")
    private Integer reqUnitsCount;

    /**
     * Required resources of Iron of Space
     */
    @Column(name = "req_res_iron")
    private Integer reqResIron;

    /**
     * Health Point of attack
     */
    @Column(name = "hp")
    private Integer hp;

    /**
     * Attack point of attack
     */
    @Column(name = "attack")
    private Integer attack;

    /**
     * Armor point of attack
     */
    @Column(name = "armor")
    private Integer armor;

    /**
     * Reward resource Agua
     */
    @Column(name = "reward_res_agua")
    private Integer rewardResAgua;

    /**
     * Reward resource Plastic
     */
    @Column(name = "reward_res_plastic")
    private Integer rewardResPlastic;

    /**
     * Reward resource Food
     */
    @Column(name = "reward_res_food")
    private Integer rewardResFood;

    /**
     * Reward resource Iron
     */
    @Column(name = "reward_res_iron")
    private Integer rewardResIron;

    /**
     * Result of auto battle
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "result", length = 12, nullable = false)
    private BattleResult result = BattleResult.DRAW; // by default DRAW

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

    public Integer getReqBuildingLevel() {
        return reqBuildingLevel;
    }

    public void setReqBuildingLevel(Integer reqBuildingLevel) {
        this.reqBuildingLevel = reqBuildingLevel;
    }

    public Integer getReqUnitsCount() {
        return reqUnitsCount;
    }

    public void setReqUnitsCount(Integer reqUnitsCount) {
        this.reqUnitsCount = reqUnitsCount;
    }

    public Integer getReqResIron() {
        return reqResIron;
    }

    public void setReqResIron(Integer reqResIron) {
        this.reqResIron = reqResIron;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getRewardResAgua() {
        return rewardResAgua;
    }

    public void setRewardResAgua(Integer rewardResAgua) {
        this.rewardResAgua = rewardResAgua;
    }

    public Integer getRewardResPlastic() {
        return rewardResPlastic;
    }

    public void setRewardResPlastic(Integer rewardResPlastic) {
        this.rewardResPlastic = rewardResPlastic;
    }

    public Integer getRewardResFood() {
        return rewardResFood;
    }

    public void setRewardResFood(Integer rewardResFood) {
        this.rewardResFood = rewardResFood;
    }

    public Integer getRewardResIron() {
        return rewardResIron;
    }

    public void setRewardResIron(Integer rewardResIron) {
        this.rewardResIron = rewardResIron;
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
        if (!(o instanceof Autobattle that)) return false;
        return getId().equals(that.getId()) && getSpaceId().equals(that.getSpaceId()) && getStartTime().equals(that.getStartTime()) && getEndTime().equals(that.getEndTime()) && getStatus() == that.getStatus() && getReqBuildingLevel().equals(that.getReqBuildingLevel()) && getReqUnitsCount().equals(that.getReqUnitsCount()) && getReqResIron().equals(that.getReqResIron()) && getHp().equals(that.getHp()) && getAttack().equals(that.getAttack()) && getArmor().equals(that.getArmor()) && getRewardResAgua().equals(that.getRewardResAgua()) && getRewardResPlastic().equals(that.getRewardResPlastic()) && getRewardResFood().equals(that.getRewardResFood()) && getRewardResIron().equals(that.getRewardResIron()) && getResult() == that.getResult() && getLogs().equals(that.getLogs()) && getCreatedAt().equals(that.getCreatedAt()) && getUpdatedAt().equals(that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSpaceId(), getStartTime(), getEndTime(), getStatus(), getReqBuildingLevel(), getReqUnitsCount(), getReqResIron(), getHp(), getAttack(), getArmor(), getRewardResAgua(), getRewardResPlastic(), getRewardResFood(), getRewardResIron(), getResult(), getLogs(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "Autobattle{" +
                "id=" + id +
                ", spaceId=" + spaceId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", reqBuildingLevel=" + reqBuildingLevel +
                ", reqUnitsCount=" + reqUnitsCount +
                ", reqResIron=" + reqResIron +
                ", hp=" + hp +
                ", attack=" + attack +
                ", armor=" + armor +
                ", rewardResAgua=" + rewardResAgua +
                ", rewardResPlastic=" + rewardResPlastic +
                ", rewardResFood=" + rewardResFood +
                ", rewardResIron=" + rewardResIron +
                ", result=" + result +
                ", logs='" + logs + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

