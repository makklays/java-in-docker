package com.techmatrix18.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;
import java.util.Objects;

/**
 * An entity representing a contact in the system.
 *
 * Corresponds to the 'contact' table in the database.
 *
 * @author Alexander Kuziv
 * @since 19-02-2025
 * @version 0.0.1
 */

@Entity
@Table(name = "contact")
public class Contact {

    /**
     * Unique user identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * name of a contact
     */
    @NotNull
    @Size(min=2, max=30)
    private String name;

    /**
     * surname of a contact
     */
    private String surname;

    /**
     * User email. Must be unique.
     */
    @NotNull
    @Email(message = "{Email.MyUser.email}") // in src/main/resources/ValidationMessages.properties
    private String email;

    /**
     * description of a contact
     */
    @NotNull
    @Size(min=6, max=700)
    private String description;

    /**
     * Date and time of user creation.
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Instant createdAt;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(o instanceof Contact contact)) return false;
        return getId().equals(contact.getId()) && name.equals(contact.name) && getSurname().equals(contact.getSurname()) && getEmail().equals(contact.getEmail()) && getDescription().equals(contact.getDescription()) && getCreatedAt().equals(contact.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name, getSurname(), getEmail(), getDescription(), getCreatedAt());
    }

    @Override
    public String toString() {
        return "Contact {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

