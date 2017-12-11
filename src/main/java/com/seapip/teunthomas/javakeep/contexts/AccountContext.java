package com.seapip.teunthomas.javakeep.contexts;

import com.seapip.teunthomas.javakeep.entities.Noteable;

public interface AccountContext {
    Accountable getById(Long id);
    Accountable getByEmail(String email);
    void delete(Long id);
    void update(String newEmail, String newPassword);
}
