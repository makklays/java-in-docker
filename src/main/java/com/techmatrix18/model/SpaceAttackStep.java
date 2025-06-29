package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "space_attack_steps")
public class SpaceAttackStep {

    /**
     * Unique user identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Space Attack Id
     */
    @Column(name = "space_attack_id", nullable = false)
    private Long spaceAttackId;

    /**
     * Step of space attack
     */
    @Column(name = "step")
    private Integer step;

    /**
     * Attacker name
     */
    @Column(name = "attacker_name")
    private String attackerName;

    /**
     * Defender name
     */
    @Column(name = "defender_name")
    private String defenderName;

    /**
     * Attack value
     */
    @Column(name = "attack_value")
    private Integer attackValue;

    /**
     * Armor value
     */
    @Column(name = "armor_value")
    private Integer armorValue;

    /**
     * Damage value
     */
    @Column(name = "damage_value")
    private Integer damageValue;

    /**
     * Defender HP after hit
     */
    @Column(name = "defender_hp_after_hit")
    private Integer defenderHpAfterHit;

    /**
     * Is critical attack ?
     */
    @Column(name = "is_critical")
    private Boolean isCritical; // 0 or 1

    /**
     * Log message of step of attack
     */
    @Column(name = "log_message")
    private String logMessage;

    /**
     * Date and time of step of space attack creation
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    /**
     * Date and time of step of space attack update
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

    public Long getSpaceAttackId() {
        return spaceAttackId;
    }

    public void setSpaceAttackId(Long spaceAttackId) {
        this.spaceAttackId = spaceAttackId;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getAttackerName() {
        return attackerName;
    }

    public void setAttackerName(String attackerName) {
        this.attackerName = attackerName;
    }

    public String getDefenderName() {
        return defenderName;
    }

    public void setDefenderName(String defenderName) {
        this.defenderName = defenderName;
    }

    public Integer getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(Integer attackValue) {
        this.attackValue = attackValue;
    }

    public Integer getArmorValue() {
        return armorValue;
    }

    public void setArmorValue(Integer armorValue) {
        this.armorValue = armorValue;
    }

    public Integer getDamageValue() {
        return damageValue;
    }

    public void setDamageValue(Integer damageValue) {
        this.damageValue = damageValue;
    }

    public Integer getDefenderHpAfterHit() {
        return defenderHpAfterHit;
    }

    public void setDefenderHpAfterHit(Integer defenderHpAfterHit) {
        this.defenderHpAfterHit = defenderHpAfterHit;
    }

    public Boolean getCritical() {
        return isCritical;
    }

    public void setCritical(Boolean critical) {
        isCritical = critical;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
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
        if (!(o instanceof SpaceAttackStep that)) return false;
        return getId().equals(that.getId()) && getSpaceAttackId().equals(that.getSpaceAttackId()) && getStep().equals(that.getStep()) && getAttackerName().equals(that.getAttackerName()) && getDefenderName().equals(that.getDefenderName()) && getAttackValue().equals(that.getAttackValue()) && getArmorValue().equals(that.getArmorValue()) && getDamageValue().equals(that.getDamageValue()) && getDefenderHpAfterHit().equals(that.getDefenderHpAfterHit()) && isCritical.equals(that.isCritical) && getLogMessage().equals(that.getLogMessage()) && getCreatedAt().equals(that.getCreatedAt()) && getUpdatedAt().equals(that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSpaceAttackId(), getStep(), getAttackerName(), getDefenderName(), getAttackValue(), getArmorValue(), getDamageValue(), getDefenderHpAfterHit(), isCritical, getLogMessage(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "SpaceAttackStep{" +
                "id=" + id +
                ", spaceAttackId=" + spaceAttackId +
                ", step=" + step +
                ", attackerName='" + attackerName + '\'' +
                ", defenderName='" + defenderName + '\'' +
                ", attackValue=" + attackValue +
                ", armorValue=" + armorValue +
                ", damageValue=" + damageValue +
                ", defenderHpAfterHit=" + defenderHpAfterHit +
                ", isCritical=" + isCritical +
                ", logMessage='" + logMessage + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

