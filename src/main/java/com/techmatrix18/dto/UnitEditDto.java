package com.techmatrix18.dto;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

public class UnitEditDto {

    @NotBlank(message = "Titulo requerido")
    private String title;

    @Size(max = 3000, message = "La descripcion no debe ser más 3000 letras")
    private String description;

    @NotBlank(message = "Seleccione un typo")
    private String type;

    // res
    @NotNull(message = "Agua requerido")
    @Min(value = 0, message = "Min Agua — 0")
    @Max(value = 999999, message = "Max Agua — 999999")
    private Integer resAgua;

    @NotNull(message = "Plastico requerido")
    @Min(value = 0, message = "Min Plastico — 0")
    @Max(value = 999999, message = "Max Plastico — 999999")
    private Integer resPlastic;

    @NotNull(message = "Food requerido")
    @Min(value = 0, message = "Min Food — 0")
    @Max(value = 999999, message = "Max Food — 999999")
    private Integer resFood;

    @NotNull(message = "Iron requerido")
    @Min(value = 0, message = "Min Iron — 0")
    @Max(value = 999999, message = "Max Iron — 999999")
    private Integer resIron;

    // nivel
    @NotNull(message = "Nivel requerido")
    @Min(value = 0, message = "Min Iron — 0")
    @Max(value = 999999, message = "Max Iron — 999999")
    private Integer level;

    // hp armor attack range speed
    @NotNull(message = "HP requerido")
    @Min(value = 0, message = "Min Iron — 0")
    @Max(value = 999999, message = "Max Iron — 999999")
    private Integer hp;

    @NotNull(message = "Armor requerido")
    @Min(value = 0, message = "Min Iron — 0")
    @Max(value = 999999, message = "Max Iron — 999999")
    private Integer armor;

    @NotNull(message = "Attack requerido")
    @Min(value = 0, message = "Min Iron — 0")
    @Max(value = 999999, message = "Max Iron — 999999")
    private Integer attack;

    @NotNull(message = "Range requerido")
    @Min(value = 0, message = "Min Iron — 0")
    @Max(value = 999999, message = "Max Iron — 999999")
    private Integer range;

    @NotNull(message = "Speed requerido")
    @Min(value = 0, message = "Min Iron — 0")
    @Max(value = 999999, message = "Max Iron — 999999")
    private Integer speed;

    // seconds
    @NotNull(message = "Training (secundos) requerido")
    @Min(value = 0, message = "Min secundos — 0")
    @Max(value = 99999999, message = "Max secundos — 999999999")
    private Integer trainingSeconds;

    //@NotNull(message = "El archivo requerido")
    private MultipartFile img;

    // getters and setters

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

    public Integer getResAgua() {
        return resAgua;
    }

    public void setResAgua(Integer resAgua) {
        this.resAgua = resAgua;
    }

    public Integer getResPlastic() {
        return resPlastic;
    }

    public void setResPlastic(Integer resPlastic) {
        this.resPlastic = resPlastic;
    }

    public Integer getResFood() {
        return resFood;
    }

    public void setResFood(Integer resFood) {
        this.resFood = resFood;
    }

    public Integer getResIron() {
        return resIron;
    }

    public void setResIron(Integer resIron) {
        this.resIron = resIron;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getTrainingSeconds() {
        return trainingSeconds;
    }

    public void setTrainingSeconds(Integer trainingSeconds) {
        this.trainingSeconds = trainingSeconds;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }
}

