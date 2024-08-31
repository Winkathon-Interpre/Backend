package com.github.winkathon.lingo.domain.post.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.winkathon.lingo.domain.post.schema.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    boolean existsByTitle(String title);
}
