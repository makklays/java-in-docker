package com.techmatrix18.controllers.web;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is MongoDB
 * (example with mongodb)
 *
 * @company for TechMatrix18
 * @author Alexander Kuziv
 * @since 10-08-2025
 * @version 0.0.1
 */

@RestController
public class MongoTestController {

    @PostMapping("/mongo-db/users/add")
    public void addUser(@RequestParam String username, @RequestParam String age, @RequestParam String email) {

        // Создаем клиент для подключения к MongoDB по умолчанию (localhost:27017)
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {

            // Получаем базу данных (если нет — будет создана при вставке данных)
            MongoDatabase database = mongoClient.getDatabase("mydb");

            // Получаем коллекцию (аналог таблицы)
            MongoCollection<Document> collection = database.getCollection("users");

            // Создаем документ (запись)
            Document doc = new Document("name", username)
                    .append("age", age)
                    .append("email", email);

            // Вставляем документ в коллекцию
            collection.insertOne(doc);

            System.out.println("Документ вставлен!");
        }
    }
}

