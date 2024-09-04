package com.github.winkathon.tripee.domain.post.schema;

import com.github.winkathon.tripee.common.schema.BaseSchema;
import com.github.winkathon.tripee.domain.upload.schema.Image;
import com.github.winkathon.tripee.domain.user.schema.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post extends BaseSchema {

    private User owner;

    private String title;
    private String content;
    private String introduce;

    private boolean paid;
    private int price;

    private Image backgroundImage;

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Post target)) {
            return false;
        }

        return this.getId().equals(target.getId());
    }
}
