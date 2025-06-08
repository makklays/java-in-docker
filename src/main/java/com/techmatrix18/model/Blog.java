package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "blogs")
public class Blog {

    /**
     * Unique user identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Space ID on the Map
     */
    @Column(name = "space_id")
    private Long spaceId;

    /**
     * Number Sector on the Map
     */
    @Column(name = "sector")
    private Long sector;

    /**
     * Day on the colon
     */
    @Column(name = "day")
    private Integer day;

    /**
     * Action on the colon
     */
    @Column(name = "action", nullable = false)
    private String action;

    /**
     * Title of action on the colon
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Post of action on the colon
     */
    @Column(name = "post")
    private String post;

    /**
     * Author of post at the Blog of colon
     */
    @Column(name = "author", nullable = false)
    private String author;

    /**
     * Date and time of base creation
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

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

    public Long getSector() {
        return sector;
    }

    public void setSector(Long sector) {
        this.sector = sector;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
        if (!(o instanceof Blog blog)) return false;
        return getId().equals(blog.getId()) && getSpaceId().equals(blog.getSpaceId()) && getSector().equals(blog.getSector()) && getDay().equals(blog.getDay()) && getAction().equals(blog.getAction()) && getTitle().equals(blog.getTitle()) && getPost().equals(blog.getPost()) && getAuthor().equals(blog.getAuthor()) && Objects.equals(getCreatedAt(), blog.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSpaceId(), getSector(), getDay(), getAction(), getTitle(), getPost(), getAuthor(), getCreatedAt());
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", spaceId=" + spaceId +
                ", sector=" + sector +
                ", day=" + day +
                ", action='" + action + '\'' +
                ", title='" + title + '\'' +
                ", post='" + post + '\'' +
                ", author='" + author + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

