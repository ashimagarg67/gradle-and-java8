package com.cmartin.learn.mybank.api;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by cmartin on 16/07/16.
 */
public class UserDto extends ResourceDto {
    public UserDto(UUID id) {
        super(id);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", this.getId())
                .toString();
    }
}
