package com.api.back.data;

import com.api.back.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {

  Optional<User> findUserByEmail(String email);
}
