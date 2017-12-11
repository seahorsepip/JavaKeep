package com.seapip.teunthomas.javakeep.repositories;

import com.seapip.teunthomas.javakeep.contexts.NoteContext;
import com.seapip.teunthomas.javakeep.dto.Note;
import com.seapip.teunthomas.javakeep.entities.Noteable;

public class AccountRepository {
    private AccountContext context;

    public AccountRepository(AccountContext context) {
        this.context = context;
    }

    public Account getById(Long id) {
        Accountable accountable = context.getById(id);
        Account account = new Account();
        account.setId(accountable.getId());
        return account;
    }
    
    
}
