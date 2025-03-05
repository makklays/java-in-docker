package com.techmatrix18.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=2, max=30)
    private String name;

    private String surname;

    @NotNull
    @Email(message = "{Email.MyUser.email}") // in src/main/resources/ValidationMessages.properties
    private String email;

    @NotNull
    @Size(min=6, max=700)
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Instant createdAt;

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

