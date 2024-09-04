package com.github.winkathon.tripee.domain.post.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.winkathon.tripee.domain.post.schema.Post;
import com.github.winkathon.tripee.domain.user.schema.User;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findAllByOwner(User owner);

    List<Post> findAllByTitleContaining(String title);

    List<Post> findAllByContentContaining(String content);

    boolean existsByTitle(String title);
}
