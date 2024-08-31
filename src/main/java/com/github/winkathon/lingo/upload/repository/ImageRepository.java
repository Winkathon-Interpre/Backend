package com.github.winkathon.lingo.upload.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.winkathon.lingo.upload.schema.Image;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {

}
