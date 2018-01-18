package com.seapip.teunthomas.javakeep.entities;

import java.util.List;

public interface Folderable {

    Long getId();

    String getName();

    List<? extends Noteable> getNotes();

    List<? extends Accountable> getAccounts();
}
