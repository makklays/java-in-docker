package com.techmatrix18.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;

/**
 * @author Alexander Kuziv
 * @since 19-02-2025
 * @version 0.0.1
 */

@Entity
@Table(name = "users")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=2, max=30)
    private String username;

    @NotNull
    @Email(message = "{Email.MyUser.email}") // in src/main/resources/ValidationMessages.properties
    private String email;

    @NotNull
    @Size(min=6, max=15)
    private String password;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyUser myUser)) return false;
        return getId().equals(myUser.getId()) && getUsername().equals(myUser.getUsername()) && getEmail().equals(myUser.getEmail()) && getPassword().equals(myUser.getPassword()) && createdAt.equals(myUser.createdAt) && updatedAt.equals(myUser.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getEmail(), getPassword(), createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

