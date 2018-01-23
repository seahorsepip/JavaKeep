package com.seapip.teunthomas.javakeep.contexts;

import com.seapip.teunthomas.javakeep.entities.Folderable;

import java.util.List;

public interface FolderContext {
    Folderable create(Folderable folderable, Long accountId);

    Folderable getById(Long id, Long accountId);

    List<? extends Folderable> getAll(Long accountId);

    void update(Folderable folderable, Long accountId);

    void delete(Long id, Long accountId);

    void share(Long id, String email, Long accountId);
}
