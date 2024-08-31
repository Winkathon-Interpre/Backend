package com.github.winkathon.lingo.domain.post.schema;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.github.winkathon.lingo.common.schema.BaseSchema;
import com.github.winkathon.lingo.domain.upload.schema.Image;
import com.github.winkathon.lingo.domain.user.schema.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post extends BaseSchema {

    @DBRef
    private User owner;

    private String title;
    private String content;
    private String introduce;

    private boolean paid;
    private int price;

    @DBRef
    private Image backgroundImage;

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Post target)) {
            return false;
        }

        return this.getId().equals(target.getId());
    }
}
