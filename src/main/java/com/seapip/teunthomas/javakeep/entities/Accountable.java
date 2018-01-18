package com.seapip.teunthomas.javakeep.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface Accountable {
    Long getId();

    String getEmail();

    String getPassword();

    List<? extends Folderable> getFolders();
}
