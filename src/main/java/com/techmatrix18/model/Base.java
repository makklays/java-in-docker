package com.techmatrix18.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "bases")
public class Base {

    /**
     * Unique user identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * title of a base
     */
    @NotNull
    @Column(name = "title", length = 255)
    private String title;

    /**
     * description of a base
     */
    @Column(name = "description", length = 700)
    private String description;

    /**
     * image of a base
     */
    @Column(name = "img", length = 255)
    private String img;

    /**
     *
     */
    @OneToMany(mappedBy = "base", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BaseLevel> baseLevels = new ArrayList<>();

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

    public String getImg() { return img; }

    public void setImg(String img) { this.img = img; }

    public List<BaseLevel> getBaseLevels() {
        return baseLevels;
    }

    public void setBaseLevels(List<BaseLevel> baseLevels) {
        this.baseLevels = baseLevels;
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
        if (!(o instanceof Base base)) return false;
        return getId().equals(base.getId()) && getTitle().equals(base.getTitle()) && getDescription().equals(base.getDescription()) && getImg().equals(base.getImg()) && getBaseLevels().equals(base.getBaseLevels()) && getCreatedAt().equals(base.getCreatedAt()) && getUpdatedAt().equals(base.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getImg(), getBaseLevels(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", baseLevels=" + baseLevels +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

