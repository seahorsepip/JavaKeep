package com.seapip.teunthomas.javakeep.contexts;

import com.seapip.teunthomas.javakeep.entities.Noteable;
import com.seapip.teunthomas.javakeep.entities.Shareable;

import java.util.List;
import java.util.UUID;

public interface NoteContext {
    Noteable create(Noteable noteable);

    Noteable getById(Long id, Long accountId);

    List<? extends Noteable> getAll(Long accountId);

    UUID share(Long id, Shareable.Permission permission, Long accountId);

    Shareable getByToken(UUID token);
}
