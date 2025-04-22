package com.techmatrix18.model;

import jakarta.persistence.*;

/**
 * An entity representing a country in the system.
 *
 * Corresponds to the 'countries' table in the database.
 *
 * @author Alexander Kuziv
 * @since 19-02-2025
 * @version 0.0.1
 */

@Entity
@Table(name = "countries")
public class Country {

    /**
     * Unique user identifier (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * name of a country
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * capital of a country
     */
    @Column(nullable = false)
    private String capital;

    /**
     * population of a country
     */
    @Column(nullable = false)
    private int population;

    // Constructors
    public Country() {
    }

    public Country(String name, String capital, int population) {
        this.name = name;
        this.capital = capital;
        this.population = population;
    }

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

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}

