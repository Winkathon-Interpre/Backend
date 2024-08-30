package com.github.winkathon.interpre.domain.notification.schema;

import com.github.winkathon.interpre.common.schema.BaseSchema;
import com.github.winkathon.interpre.domain.user.schema.User;

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
