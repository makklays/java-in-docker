package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "base_levels")
public class BaseLevel {

    /**
     * Unique user identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * base_id of a base
     */
    @Column(name = "base_id")
    private Long baseId;

    /**
     * level of a base
     */
    @Column(name = "level")
    private int level;

    /**
     * title of a base
     */
    @Column(name = "title", length = 255)
    private String title;

    /**
     * description of a base
     */
    @Column(name = "description", length = 700)
    private String description;

    /**
     * have resource 'agua'
     */
    @Column(name = "res_agua")
    private int resAgua = 100;

    /**
     * have resource 'plastic'
     */
    @Column(name = "res_plastic")
    private int resPlastic = 10;

    /**
     * have resource 'food'
     */
    @Column(name = "res_food")
    private int resFood = 100;

    /**
     * have resource 'iron'
     */
    @Column(name = "res_iron")
    private int resIron = 10;

    /**
     * adding resource 'agua' by time
     */
    @Column(name = "plus_res_agua")
    private int plusResAgua;

    /**
     * adding resource 'plastic' by time
     */
    @Column(name = "plus_res_plastic")
    private int plusResPlastic;

    /**
     * adding resource 'food' by time
     */
    @Column(name = "plus_res_food")
    private int plusResFood;

    /**
     * adding resource 'iron' by time
     */
    @Column(name = "plus_res_iron")
    private int plusResIron;

    /**
     * build time in seconds
     */
    @Column(name = "build_seconds")
    private int buildSeconds = 36000;

    /**
     * image of a base level
     */
    @Column(name = "img", length = 500)
    private String img;

    /**
     * Date and time of base creation
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
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

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public int getPlusResAgua() {
        return plusResAgua;
    }

    public void setPlusResAgua(int plusResAgua) {
        this.plusResAgua = plusResAgua;
    }

    public int getPlusResPlastic() {
        return plusResPlastic;
    }

    public void setPlusResPlastic(int plusResPlastic) {
        this.plusResPlastic = plusResPlastic;
    }

    public int getPlusResFood() {
        return plusResFood;
    }

    public void setPlusResFood(int plusResFood) {
        this.plusResFood = plusResFood;
    }

    public int getPlusResIron() {
        return plusResIron;
    }

    public void setPlusResIron(int plusResIron) {
        this.plusResIron = plusResIron;
    }

    public int getBuildSeconds() {
        return buildSeconds;
    }

    public void setBuildSeconds(int buildSeconds) {
        this.buildSeconds = buildSeconds;
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
        if (!(o instanceof BaseLevel baseLevel)) return false;
        return getLevel() == baseLevel.getLevel() && getResAgua() == baseLevel.getResAgua() && getResPlastic() == baseLevel.getResPlastic() && getResFood() == baseLevel.getResFood() && getResIron() == baseLevel.getResIron() && getPlusResAgua() == baseLevel.getPlusResAgua() && getPlusResPlastic() == baseLevel.getPlusResPlastic() && getPlusResFood() == baseLevel.getPlusResFood() && getPlusResIron() == baseLevel.getPlusResIron() && getBuildSeconds() == baseLevel.getBuildSeconds() && getId().equals(baseLevel.getId()) && getBaseId().equals(baseLevel.getBaseId()) && getTitle().equals(baseLevel.getTitle()) && getDescription().equals(baseLevel.getDescription()) && getImg().equals(baseLevel.getImg()) && getCreatedAt().equals(baseLevel.getCreatedAt()) && getUpdatedAt().equals(baseLevel.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBaseId(), getLevel(), getTitle(), getDescription(), getResAgua(), getResPlastic(), getResFood(), getResIron(), getPlusResAgua(), getPlusResPlastic(), getPlusResFood(), getPlusResIron(), getBuildSeconds(), getImg(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "BaseLevel{" +
                "id=" + id +
                ", baseId=" + baseId +
                ", level=" + level +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", resAgua=" + resAgua +
                ", resPlastic=" + resPlastic +
                ", resFood=" + resFood +
                ", resIron=" + resIron +
                ", plusResAgua=" + plusResAgua +
                ", plusResPlastic=" + plusResPlastic +
                ", plusResFood=" + plusResFood +
                ", plusResIron=" + plusResIron +
                ", buildSeconds=" + buildSeconds +
                ", img='" + img + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

