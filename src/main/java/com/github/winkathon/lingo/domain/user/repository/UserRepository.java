package com.github.winkathon.lingo.domain.user.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.winkathon.lingo.domain.user.schema.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUserId(String userId);

    boolean existsByUserId(String userId);
}
