package com.techmatrix18.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * An entity representing a user in the system.
 *
 * Corresponds to the 'users' table in the database.
 *
 * @author Alexander Kuziv
 * @since 19-02-2025
 * @version 0.0.1
 */

@Entity
@Table(name = "users")
@Schema(description = "Entity representing a user")
public class User implements Serializable {

    /**
     * Unique user identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //(strategy = GenerationType.AUTO)
    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;

    /**
     * username of a user
     */
    @NotNull
    @Size(min=2, max=30)
    @Schema(description = "Nombre del usuario", example = "42")
    private String username;

    /**
     * User email. Must be unique.
     */
    @NotNull
    @Email(message = "{Email.MyUser.email}") // in src/main/resources/ValidationMessages.properties
    @Schema(description = "Correo del usuario", example = "42")
    private String email;

    /**
     * User mobile. Must be unique.
     */
    @NotNull
    @Column(name = "mob")
    @Schema(description = "Móvil del usuario", example = "+34 612 345 67")
    private String mob;

    /**
     * User gender.
     */
    @Column(name = "gender")
    @Schema(description = "Género del usuario", example = "male")
    private String gender;

    /**
     * User age.
     */
    @Column(name = "age")
    @Schema(description = "Edad del usuario", example = "23")
    private String age;

    /**
     * User avatar.
     */
    @Column(name = "avatar")
    @Schema(description = "Avatar del usuario", example = "avatar.png")
    private String avatar;

    /**
     * The user's hashed password.
     */
    @NotNull
    @Column(name = "password", nullable = false)
    @Schema(description = "Password del usuario", example = "qwerty")
    private String password;

    /**
     * Date and time of user creation.
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false, nullable = false)
    @Schema(description = "Fecha de creación del usuario", example = "19-02-2025 00:00:00")
    private Instant createdAt;

    /**
     * Date and time of user update.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @Schema(description = "Fecha de actualización del usuario", example = "19-02-2025 00:00:00")
    private Instant updatedAt;

    // Getters, setters and constructors

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

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(o instanceof User user)) return false;
        return getId().equals(user.getId()) && getUsername().equals(user.getUsername()) && getEmail().equals(user.getEmail()) && getMob().equals(user.getMob()) && getGender().equals(user.getGender()) && getAge().equals(user.getAge()) && getAvatar().equals(user.getAvatar()) && Objects.equals(getPassword(), user.getPassword()) && getCreatedAt().equals(user.getCreatedAt()) && Objects.equals(getUpdatedAt(), user.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getEmail(), getMob(), getGender(), getAge(), getAvatar(), getPassword(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", mob='" + mob + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

