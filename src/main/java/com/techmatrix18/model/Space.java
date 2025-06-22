package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * An entity representing a space in the system.
 *
 * Corresponds to the 'spaces' table in the database.
 *
 * @author Alexander Kuziv
 * @since 24-04-2025
 * @version 0.0.1
 */

@Entity
@Table(name = "spaces")
public class Space {

    /**
     * Unique user identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * Title of space
     */
    @Column(name = "title")
    private String title;

    /**
     * Count agua on space
     */
    @Column(name = "res_agua")
    private int resAgua;

    /**
     * Count plastic on space
     */
    @Column(name = "res_plastic")
    private int resPlastic;

    /**
     * Count food on space
     */
    @Column(name = "res_food")
    private int resFood;

    /**
     * Count iron on space
     */
    @Column(name = "res_iron")
    private int resIron;

    /**
     * Adding agua per time on space
     */
    @Column(name = "do_res_agua")
    private int doResAgua;

    /**
     * Adding plastic per time on space
     */
    @Column(name = "do_res_plastic")
    private int doResPlastic;

    /**
     * Adding food per time on space
     */
    @Column(name = "do_res_food")
    private int doResFood;

    /**
     * Adding iron per time on space
     */
    @Column(name = "do_res_iron")
    private int doResIron;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getDoResAgua() {
        return doResAgua;
    }

    public void setDoResAgua(int doResAgua) {
        this.doResAgua = doResAgua;
    }

    public int getDoResPlastic() {
        return doResPlastic;
    }

    public void setDoResPlastic(int doResPlastic) {
        this.doResPlastic = doResPlastic;
    }

    public int getDoResFood() {
        return doResFood;
    }

    public void setDoResFood(int doResFood) {
        this.doResFood = doResFood;
    }

    public int getDoResIron() {
        return doResIron;
    }

    public void setDoResIron(int doResIron) {
        this.doResIron = doResIron;
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
        if (!(o instanceof Space space)) return false;
        return getResAgua() == space.getResAgua() && getResPlastic() == space.getResPlastic() && getResFood() == space.getResFood() && getResIron() == space.getResIron() && getDoResAgua() == space.getDoResAgua() && getDoResPlastic() == space.getDoResPlastic() && getDoResFood() == space.getDoResFood() && getDoResIron() == space.getDoResIron() && getId().equals(space.getId()) && Objects.equals(getUserId(), space.getUserId()) && getTitle().equals(space.getTitle()) && getCreatedAt().equals(space.getCreatedAt()) && getUpdatedAt().equals(space.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getTitle(), getResAgua(), getResPlastic(), getResFood(), getResIron(), getDoResAgua(), getDoResPlastic(), getDoResFood(), getDoResIron(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "Space{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", resAgua=" + resAgua +
                ", resPlastic=" + resPlastic +
                ", resFood=" + resFood +
                ", resIron=" + resIron +
                ", doResAgua=" + doResAgua +
                ", doResPlastic=" + doResPlastic +
                ", doResFood=" + doResFood +
                ", doResIron=" + doResIron +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     *
     */
    public Long getDias() {
        LocalDate createdDate = createdAt.atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.DAYS.between(createdDate, LocalDate.now());
    }
}

