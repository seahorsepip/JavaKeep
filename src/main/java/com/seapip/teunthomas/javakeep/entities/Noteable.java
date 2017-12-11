package com.seapip.teunthomas.javakeep.entities;

import java.util.Date;

public interface Noteable {

    Long getId();

    String getTitle();

    String getContent();

    Date getDate();

    Accountable getAccount();
}
