package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "artifacts")
public class Artifact {

    /**
     * Unique user identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Title of an artifact
     */
    @Column(name = "title")
    private String title;

    /**
     * Description of an artifact
     */
    @Column(name = "description", length = 2000)
    private String description;

    /**
     * Price amount of an artifact
     */
    @Column(name = "amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal amount;

    /**
     * Adding plus agua to space
     */
    @Column(name = "plus_res_agua")
    private int plusResAgua;

    /**
     * Adding plus plastic to space
     */
    @Column(name = "plus_res_plastic")
    private int plusResPlastic;

    /**
     * Adding plus food to space
     */
    @Column(name = "plus_res_food")
    private int plusResFood;

    /**
     * Adding plus iron to space
     */
    @Column(name = "plus_res_iron")
    private int plusResIron;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artifact artifact)) return false;
        return getPlusResAgua() == artifact.getPlusResAgua() && getPlusResPlastic() == artifact.getPlusResPlastic() && getPlusResFood() == artifact.getPlusResFood() && getPlusResIron() == artifact.getPlusResIron() && getId().equals(artifact.getId()) && getTitle().equals(artifact.getTitle()) && getDescription().equals(artifact.getDescription()) && getAmount().equals(artifact.getAmount()) && getCreatedAt().equals(artifact.getCreatedAt()) && updatedAt.equals(artifact.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getAmount(), getPlusResAgua(), getPlusResPlastic(), getPlusResFood(), getPlusResIron(), getCreatedAt(), updatedAt);
    }

    @Override
    public String toString() {
        return "Artifact{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", plusResAgua=" + plusResAgua +
                ", plusResPlastic=" + plusResPlastic +
                ", plusResFood=" + plusResFood +
                ", plusResIron=" + plusResIron +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

