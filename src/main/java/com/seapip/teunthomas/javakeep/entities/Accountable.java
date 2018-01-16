package com.seapip.teunthomas.javakeep.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface Accountable {
    Long getId();

    String getEmail();

    String getPassword();
}
