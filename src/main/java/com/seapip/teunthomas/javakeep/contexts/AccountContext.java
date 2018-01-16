package com.seapip.teunthomas.javakeep.contexts;

import com.seapip.teunthomas.javakeep.entities.Accountable;

public interface AccountContext {
    Accountable create(Accountable accountable);

    Accountable getById(Long id);

    Accountable getByEmail(String email);

    void delete(Long id);

    void update(String newEmail, String newPassword);
}
