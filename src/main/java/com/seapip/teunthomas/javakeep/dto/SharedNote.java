package com.seapip.teunthomas.javakeep.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.seapip.teunthomas.javakeep.entities.Shareable;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SharedNote implements Shareable {
    private UUID token;
    private Permission permission;
    private Note note;

    @Override
    public UUID getToken() {
        return token;
    }

    public SharedNote setToken(UUID token) {
        this.token = token;
        return this;
    }

    @Override
    public Permission getPermission() {
        return permission;
    }
    public SharedNote setPermission(Permission permission) {
        this.permission = permission;
        return this;
    }

    @Override
    public Note getNote() {
        return note;
    }

    public SharedNote setNote(Note note) {
        this.note = note;
        return this;
    }
}
