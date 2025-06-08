package com.techmatrix18.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class BaseDto {
    @NotBlank(message = "Titulo requerido")
    private String title;

    @Size(max = 700, message = "La descripcion no debe ser más 700 letras")
    private String description;

    @NotBlank(message = "Seleccione un typo")
    private String type;

    @NotNull(message = "El archivo requerido")
    private MultipartFile img;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }
}

