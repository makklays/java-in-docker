package com.techmatrix18.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserDocument {
    @Id
    private String id;
    private String username;
    private int age;
    private String email;

    public UserDocument() {}

    public UserDocument(String username, int age, String email) {
        this.username = username;
        this.age = age;
        this.email = email;
    }

    // геттеры и сеттеры
    public String getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

