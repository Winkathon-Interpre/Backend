package com.github.winkathon.lingo.upload.schema;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.github.winkathon.lingo.common.schema.BaseSchema;
import com.github.winkathon.lingo.domain.user.schema.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Image extends BaseSchema {

    @DBRef
    private User owner;

    private String fileName;

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Image target)) {
            return false;
        }

        return this.getId().equals(target.getId());
    }
}
