package com.techmatrix18.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 *
 */
public class UserDto {

    @NotBlank(message = "Nombre del usuario requerido")
    private String username;

    @NotBlank(message = "Сontraseña requerido")
    @Size(max = 10, message = "La contraseña no debe ser más 10 letras")
    @Size(min = 6, message = "La contraseña no debe ser menos 6 letras")
    private String password;

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

