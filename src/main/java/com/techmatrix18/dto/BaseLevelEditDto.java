package com.techmatrix18.dto;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

public class BaseLevelEditDto {

    @NotNull(message = "ID requerido")
    private Long id;

    @NotBlank(message = "Titulo requerido")
    private String title;

    @Size(max = 700, message = "La descripcion no debe ser más 700 letras")
    private String description;

    @NotNull(message = "Elije nivel")
    private Integer level;

    // res
    @NotNull(message = "Agua requerido")
    @Min(value = 0, message = "Min Agua — 0")
    @Max(value = 999999, message = "Max Agua — 999999")
    private Long resAgua;

    @NotNull(message = "Plastico requerido")
    @Min(value = 0, message = "Min Plastico — 0")
    @Max(value = 999999, message = "Max Plastico — 999999")
    private Long resPlastic;

    @NotNull(message = "Food requerido")
    @Min(value = 0, message = "Min Food — 0")
    @Max(value = 999999, message = "Max Food — 999999")
    private Long resFood;

    @NotNull(message = "Iron requerido")
    @Min(value = 0, message = "Min Iron — 0")
    @Max(value = 999999, message = "Max Iron — 999999")
    private Long resIron;

    // plus
    @NotNull(message = "Plus Agua requerido")
    @Min(value = 0, message = "Plus Min Agua — 0")
    @Max(value = 999999, message = "Plus Max Agua — 999999")
    private Long plusResAgua;

    @NotNull(message = "Plus Plastico requerido")
    @Min(value = 0, message = "Plus Min Plastico — 0")
    @Max(value = 999999, message = "Plus Max Plastico — 999999")
    private Long plusResPlastic;

    @NotNull(message = "Plus Food requerido")
    @Min(value = 0, message = "Plus Min Food — 0")
    @Max(value = 999999, message = "Plus Max Food — 999999")
    private Long plusResFood;

    @NotNull(message = "Plus Iron requerido")
    @Min(value = 0, message = "Plus Min Iron — 0")
    @Max(value = 999999, message = "Plus Max Iron — 999999")
    private Long plusResIron;

    // seconds
    @NotNull(message = "Building (secundos) requerido")
    @Min(value = 0, message = "Min secundos — 0")
    @Max(value = 99999999, message = "Max secundos — 999999999")
    private Long buildSeconds;

    @NotNull(message = "Файл обязателен")
    private MultipartFile img;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public Long getResAgua() {
        return resAgua;
    }

    public void setResAgua(Long resAgua) {
        this.resAgua = resAgua;
    }

    public Long getResPlastic() {
        return resPlastic;
    }

    public void setResPlastic(Long resPlastic) {
        this.resPlastic = resPlastic;
    }

    public Long getResFood() {
        return resFood;
    }

    public void setResFood(Long resFood) {
        this.resFood = resFood;
    }

    public Long getResIron() {
        return resIron;
    }

    public void setResIron(Long resIron) {
        this.resIron = resIron;
    }

    public Long getPlusResAgua() {
        return plusResAgua;
    }

    public void setPlusResAgua(Long plusResAgua) {
        this.plusResAgua = plusResAgua;
    }

    public Long getPlusResPlastic() {
        return plusResPlastic;
    }

    public void setPlusResPlastic(Long plusResPlastic) {
        this.plusResPlastic = plusResPlastic;
    }

    public Long getPlusResFood() {
        return plusResFood;
    }

    public void setPlusResFood(Long plusResFood) {
        this.plusResFood = plusResFood;
    }

    public Long getPlusResIron() {
        return plusResIron;
    }

    public void setPlusResIron(Long plusResIron) {
        this.plusResIron = plusResIron;
    }

    public Long getBuildSeconds() {
        return buildSeconds;
    }

    public void setBuildSeconds(Long buildSeconds) {
        this.buildSeconds = buildSeconds;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "BaseLevelEditDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", level=" + level +
                ", resAgua=" + resAgua +
                ", resPlastic=" + resPlastic +
                ", resFood=" + resFood +
                ", resIron=" + resIron +
                ", plusResAgua=" + plusResAgua +
                ", plusResPlastic=" + plusResPlastic +
                ", plusResFood=" + plusResFood +
                ", plusResIron=" + plusResIron +
                ", buildSeconds=" + buildSeconds +
                ", img=" + img +
                '}';
    }
}

