package com.seapip.teunthomas.javakeep.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface Shareable {

    UUID getToken();

    Permission getPermission();

    Noteable getNote();

    enum Permission {
        READ,
        READ_AND_WRITE
    }
}


