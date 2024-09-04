package com.github.winkathon.tripee.domain.upload.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.winkathon.tripee.domain.upload.schema.Image;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {

}
