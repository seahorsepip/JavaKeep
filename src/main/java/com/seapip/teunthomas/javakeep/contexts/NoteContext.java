package com.seapip.teunthomas.javakeep.contexts;

import com.seapip.teunthomas.javakeep.entities.Noteable;

import java.util.List;

public interface NoteContext {
    Noteable create(Noteable noteable);

    Noteable getById(Long id, Long accountId);

    List<? extends Noteable> getAll(Long accountId);

    //UUID share(Long id, Shareable.Permission permission, Long accountId);

    //Shareable getByToken(UUID token);

    void update(Noteable noteable, Long accountId);

    //void update(Noteable noteable, UUID token);

    void delete(Long id, Long accountId);
}
