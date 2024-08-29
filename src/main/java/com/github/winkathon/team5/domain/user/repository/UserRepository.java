package com.github.winkathon.team5.domain.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.winkathon.team5.domain.user.schema.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
