package com.seapip.teunthomas.javakeep.repositories;

import com.seapip.teunthomas.javakeep.contexts.AccountContext;
import com.seapip.teunthomas.javakeep.dto.Account;
import com.seapip.teunthomas.javakeep.entities.Accountable;

public class AccountRepository {
    private AccountContext context;

    public AccountRepository(AccountContext context) {
        this.context = context;
    }

    public Account create(Account account) {
        Accountable accountable = context.create(account);
        return new Account()
                .setId(accountable.getId())
                .setEmail(accountable.getEmail());
    }

    public Account getById(Long id) {
        Accountable accountable = context.getById(id);
        return new Account()
                .setId(accountable.getId())
                .setEmail(accountable.getEmail())
                .setPassword(accountable.getPassword());
    }

    public void update(String newEmail, String newPassword) {
        context.update(newEmail, newPassword);
    }

    public void delete(Long id) {
        context.delete(id);
    }
}
