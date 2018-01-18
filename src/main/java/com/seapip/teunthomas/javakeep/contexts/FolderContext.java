package com.seapip.teunthomas.javakeep.contexts;

import com.seapip.teunthomas.javakeep.entities.Folderable;

public interface FolderContext {
    Folderable create(Folderable folderable, Long accountId);

    Folderable getById(Long id, Long accountId);

    void update(Folderable folderable, Long accountId);

    void delete(Long id, Long accountId);

    void share(Long id, String email, Long accountId);
}
