package com.github.winkathon.tripee.domain.notification.schema;

import com.github.winkathon.tripee.common.schema.BaseSchema;
import com.github.winkathon.tripee.domain.user.schema.User;

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

        if (!(o instanceof Notification target)) {
            return false;
        }

        return this.getId().equals(target.getId());
    }
}
