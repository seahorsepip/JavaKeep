package com.seapip.teunthomas.javakeep.contexts;

import com.seapip.teunthomas.javakeep.entities.Noteable;

public interface NoteContext {
    Noteable getById(Long id, Long accountId);
}
