package com.techmatrix18.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;
import java.util.Objects;

/**
 * An entity representing a Unit in the system.
 *
 * Corresponds to the 'units' table in the database.
 *
 * @author Alexander Kuziv
 * @since 22-06-2025
 * @version 0.0.1
 */

@Entity
@Table(name = "units")
public class Unit {

    /**
     * Unique user identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * title of Unit
     */
    @NotNull
    @Column(name = "title", length = 255)
    private String title;

    /**
     * description of Unit
     */
    @Column(name = "description", length = 700)
    private String description;

    /**
     * type of Unit (type: 'biolab' or 'hangar')
     */
    @Column(name = "type", length = 255)
    private String type;

    /**
     * need agua for training Unit
     */
    @Column(name = "res_agua")
    private int resAgua = 100;

    /**
     * need plastic for training Unit
     */
    @Column(name = "res_plastic")
    private int resPlastic = 10;

    /**
     * need food for training Unit
     */
    @Column(name = "res_food")
    private int resFood = 100;

    /**
     * need iron for training Unit
     */
    @Column(name = "res_iron")
    private int resIron = 10;

    /**
     * need level of BioLab for training Unit
     */
    @Column(name = "level")
    private int level;

    /**
     * health point of Unit
     */
    @Column(name = "hp")
    private int hp;

    /**
     * armor of Unit
     */
    @Column(name = "armor")
    private int armor;

    /**
     * attack of Unit
     */
    @Column(name = "attack")
    private int attack;

    /**
     * range of Unit
     */
    @Column(name = "range")
    private int range;

    /**
     * speed of Unit
     */
    @Column(name = "speed")
    private int speed;

    /**
     * training time in seconds
     */
    @Column(name = "training_seconds")
    private int trainingSeconds = 36000;

    /**
     * image of a Unit
     */
    @Column(name = "img", length = 500)
    private String img;

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

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getResAgua() {
        return resAgua;
    }

    public void setResAgua(int resAgua) {
        this.resAgua = resAgua;
    }

    public int getResPlastic() {
        return resPlastic;
    }

    public void setResPlastic(int resPlastic) {
        this.resPlastic = resPlastic;
    }

    public int getResFood() {
        return resFood;
    }

    public void setResFood(int resFood) {
        this.resFood = resFood;
    }

    public int getResIron() {
        return resIron;
    }

    public void setResIron(int resIron) {
        this.resIron = resIron;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTrainingSeconds() {
        return trainingSeconds;
    }

    public void setTrainingSeconds(int trainingSeconds) {
        this.trainingSeconds = trainingSeconds;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
        if (!(o instanceof Unit unit)) return false;
        return getResAgua() == unit.getResAgua() && getResPlastic() == unit.getResPlastic() && getResFood() == unit.getResFood() && getResIron() == unit.getResIron() && getLevel() == unit.getLevel() && getHp() == unit.getHp() && getArmor() == unit.getArmor() && getAttack() == unit.getAttack() && getRange() == unit.getRange() && getSpeed() == unit.getSpeed() && getTrainingSeconds() == unit.getTrainingSeconds() && getId().equals(unit.getId()) && getTitle().equals(unit.getTitle()) && getDescription().equals(unit.getDescription()) && getType().equals(unit.getType()) && getImg().equals(unit.getImg()) && getCreatedAt().equals(unit.getCreatedAt()) && getUpdatedAt().equals(unit.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getType(), getResAgua(), getResPlastic(), getResFood(), getResIron(), getLevel(), getHp(), getArmor(), getAttack(), getRange(), getSpeed(), getTrainingSeconds(), getImg(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", resAgua=" + resAgua +
                ", resPlastic=" + resPlastic +
                ", resFood=" + resFood +
                ", resIron=" + resIron +
                ", level=" + level +
                ", hp=" + hp +
                ", armor=" + armor +
                ", attack=" + attack +
                ", range=" + range +
                ", speed=" + speed +
                ", trainingSeconds=" + trainingSeconds +
                ", img='" + img + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

