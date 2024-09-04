package com.github.winkathon.tripee.domain.comment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.winkathon.tripee.domain.comment.schema.Comment;
import com.github.winkathon.tripee.domain.post.schema.Post;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByPost(Post post);

    List<Comment> findAllByComment(Comment comment);
}
