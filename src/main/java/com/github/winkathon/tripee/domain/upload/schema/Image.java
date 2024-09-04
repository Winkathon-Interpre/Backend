package com.github.winkathon.tripee.domain.upload.schema;

import com.github.winkathon.tripee.common.schema.BaseSchema;
import com.github.winkathon.tripee.domain.user.schema.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Image extends BaseSchema {

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
