package com.github.winkathon.lingo.domain.notification.schema;

import com.github.winkathon.lingo.common.schema.BaseSchema;
import com.github.winkathon.lingo.domain.user.schema.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Notification extends BaseSchema {

    private User owner;

    private String title;
    private String content;

    private boolean read;

    private String redirectUrl;

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Notification notification))
            return false;

        return getId().equals(notification.getId());
    }
}
