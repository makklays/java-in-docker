package com.techmatrix18.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "autobattles")
public class Autobattle {

    /**
     * Unique autobattle identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Title of an autobattle
     */
    @NotNull(message = "Title requerido")
    @NotBlank(message = "Title requerido")
    @Column(name = "title")
    private String title;

    /**
     * Required building level TI-Centro of Map of Space
     */
    @NotNull(message = "Required nivel requerido")
    @Column(name = "req_building_level")
    private Integer reqBuildingLevel;

    /**
     * Required count of units of Space
     */
    @NotNull(message = "Required units requerido")
    @Column(name = "req_units_count")
    private Integer reqUnitsCount;

    /**
     * Required resources of Iron of Space
     */
    @NotNull(message = "Required iron requerido")
    @Column(name = "req_res_iron")
    private Integer reqResIron;

    /**
     * Health Point of attack
     */
    @NotNull(message = "HP requerido")
    @Column(name = "hp")
    private Integer hp;

    /**
     * Attack point of attack
     */
    @NotNull(message = "Attack requerido")
    @Column(name = "attack")
    private Integer attack;

    /**
     * Armor point of attack
     */
    @NotNull(message = "Armor requerido")
    @Column(name = "armor")
    private Integer armor;

    /**
     * Reward resource Agua
     */
    @NotNull(message = "Reward res agua requerido")
    @Column(name = "reward_res_agua")
    private Integer rewardResAgua;

    /**
     * Reward resource Plastic
     */
    @NotNull(message = "Reward res plastico requerido")
    @Column(name = "reward_res_plastic")
    private Integer rewardResPlastic;

    /**
     * Reward resource Food
     */
    @NotNull(message = "Reward res food requerido")
    @Column(name = "reward_res_food")
    private Integer rewardResFood;

    /**
     * Reward resource Iron
     */
    @NotNull(message = "Reward res hierro requerido")
    @Column(name = "reward_res_iron")
    private Integer rewardResIron;

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

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

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
        return getId().equals(that.getId()) && getTitle().equals(that.getTitle()) && getReqBuildingLevel().equals(that.getReqBuildingLevel()) && getReqUnitsCount().equals(that.getReqUnitsCount()) && getReqResIron().equals(that.getReqResIron()) && getHp().equals(that.getHp()) && getAttack().equals(that.getAttack()) && getArmor().equals(that.getArmor()) && getRewardResAgua().equals(that.getRewardResAgua()) && getRewardResPlastic().equals(that.getRewardResPlastic()) && getRewardResFood().equals(that.getRewardResFood()) && getRewardResIron().equals(that.getRewardResIron()) && getCreatedAt().equals(that.getCreatedAt()) && getUpdatedAt().equals(that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getReqBuildingLevel(), getReqUnitsCount(), getReqResIron(), getHp(), getAttack(), getArmor(), getRewardResAgua(), getRewardResPlastic(), getRewardResFood(), getRewardResIron(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "Autobattle{" +
                "id=" + id +
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
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

