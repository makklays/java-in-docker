package com.techmatrix18.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * UserDTO
 *
 * @author Alexander Kuziv
 * @since 10-07-2025
 * @version 0.0.1
 */

@Schema(name = "UnitDto", description = "DTO para la creación de usuario")
public class UserDto {

    @NotBlank(message = "Nombre del usuario requerido")
    @Schema(description = "Nombre del usuario", example = "42")
    private String username;

    @NotBlank(message = "Сontraseña requerido")
    @Size(max = 10, message = "La contraseña no debe ser más 10 letras")
    @Size(min = 6, message = "La contraseña no debe ser menos 6 letras")
    @Schema(description = "La contraseña del usuario", example = "qwerty")
    private String password;

    public UserDto() {}

    public UserDto(String username, String passwordHash) {
        this.username = username;
        this.password = passwordHash;
    }

    //---- getters and settters ----

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        if (!(o instanceof UserDto userDto)) return false;
        return getUsername().equals(userDto.getUsername()) && getPassword().equals(userDto.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "UserDto {" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

