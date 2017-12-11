package com.seapip.teunthomas.javakeep.contexts;

import com.seapip.teunthomas.javakeep.entities.Noteable;

public interface AccountContext {
    Accountable getById(Long id, Long accountId);
}
