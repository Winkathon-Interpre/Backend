package com.github.winkathon.lingo.domain.notification.schema;

import com.github.winkathon.lingo.common.schema.BaseSchema;
import com.github.winkathon.lingo.domain.user.schema.User;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class Notification extends BaseSchema {

    private User owner;

    private String title;
    private String content;

    private boolean read;

    private String redirectUrl;
}
