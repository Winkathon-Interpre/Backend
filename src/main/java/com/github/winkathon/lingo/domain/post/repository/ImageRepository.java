package com.github.winkathon.lingo.domain.post.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.winkathon.lingo.domain.post.schema.Image;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {

}
