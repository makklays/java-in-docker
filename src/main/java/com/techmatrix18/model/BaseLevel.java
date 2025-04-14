package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "base_levels")
public class BaseLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "base_id")
    private Long baseId;

    @Column(name = "level")
    private int level;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "description", length = 700)
    private String description;

    @Column(name = "res_agua")
    private int resAgua = 100;

    @Column(name = "res_plastic")
    private int resPlastic = 10;

    @Column(name = "res_food")
    private int resFood = 100;

    @Column(name = "res_iron")
    private int resIron = 10;

    @Column(name = "plus_res_agua")
    private int plusResAgua;

    @Column(name = "plus_res_plastic")
    private int plusResPlastic;

    @Column(name = "plus_res_food")
    private int plusResFood;

    @Column(name = "plus_res_iron")
    private int plusResIron;

    @Column(name = "build_seconds")
    private int buildSeconds = 36000;

    @Column(name = "img", length = 255)
    private String img;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Instant updatedAt;

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

