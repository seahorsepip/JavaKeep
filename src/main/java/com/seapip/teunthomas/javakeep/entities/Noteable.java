package com.seapip.teunthomas.javakeep.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.seapip.teunthomas.javakeep.dto.Note;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface Noteable {

    Long getId();

    String getTitle();

    String getContent();

    Date getDate();

    Accountable getAccount();

    Note.Type getType();
}
