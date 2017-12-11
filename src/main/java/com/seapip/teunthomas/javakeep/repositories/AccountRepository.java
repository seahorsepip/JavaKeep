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
    
    public Account getByEmail(String email) {
        Accountable accountable = context.getByEmail(email);
        Account account = new Account();
        account.setId(accountable.getId());
        return account;
    }
    
    public void delete(Long id) { 
        context.delete(id);
    }
    
    public void update(String newEmail, String newPassword) {
        context.update(newEmail, newPassword);
    }
}
