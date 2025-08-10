package com.techmatrix18.repositories;

import com.techmatrix18.model.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<UserDocument, String> {
    //
}

