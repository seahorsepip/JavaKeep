package com.seapip.teunthomas.javakeep.repositories;

import com.seapip.teunthomas.javakeep.contexts.AccountContext;
import com.seapip.teunthomas.javakeep.dto.Account;
import com.seapip.teunthomas.javakeep.entities.Accountable;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class AccountRepository {
    private AccountContext context;
    private Argon2 argon2 = Argon2Factory.create();

    public AccountRepository(AccountContext context) {
        this.context = context;
    }

    public Account create(Account account) {
        account.setPassword(argon2.hash(2, 65536, 1, account.getPassword()));
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

    public Account getByEmail(String email) {
        Accountable accountable = context.getByEmail(email);
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

    public boolean verify(Account account, String password) {
        return argon2.verify(account.getPassword(), password);
    }
}
