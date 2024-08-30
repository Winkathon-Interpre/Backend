package com.github.winkathon.lingo.domain.notification.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.winkathon.lingo.domain.notification.schema.Notification;
import com.github.winkathon.lingo.domain.user.schema.User;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

    List<Notification> findByOwner(User owner);
}
