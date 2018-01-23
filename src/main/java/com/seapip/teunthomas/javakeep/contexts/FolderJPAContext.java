package com.seapip.teunthomas.javakeep.contexts;

import com.seapip.teunthomas.javakeep.dao.Account;
import com.seapip.teunthomas.javakeep.dao.Folder;
import com.seapip.teunthomas.javakeep.entities.Folderable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class FolderJPAContext extends JPAContext implements FolderContext {

    public FolderJPAContext(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
        accountJPAContext = new AccountJPAContext(entityManagerFactory);
    }

    private AccountJPAContext accountJPAContext;

    @Override
    public Folder create(Folderable folderable, Long accountId) {
        Folder folder = new Folder();
        folder.setName(folderable.getName());
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(accountId));
        folder.setAccounts(accounts);
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(folder);
        entityManager.getTransaction().commit();
        entityManager.close();
        return folder;
    }

    @Override
    public Folder getById(Long id, Long accountId) {
        EntityManager entityManager = getEntityManager();
        Folder folder = entityManager
                .createNamedQuery("Folder.getByAccount", Folder.class)
                .setParameter("id",id)
                .setParameter("account", new Account(accountId))
                .getSingleResult();
        entityManager.close();
        return folder;
    }

    @Override
    public List<? extends Folderable> getAll(Long accountId) {
        EntityManager entityManager = getEntityManager();
        List<Folder> folders = entityManager
                .createNamedQuery("Folder.getAll", Folder.class)
                .setParameter("account", new Account(accountId))
                .getResultList();
        entityManager.close();
        return folders;
    }

    @Override
    public void share(Long id, String email, Long accountId) {
        Folder folder = getById(id, accountId);
        Account account = accountJPAContext.getByEmail(email);
        folder.getAccounts().add(account);
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(folder);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(Folderable folderable, Long accountId) {

    }

    @Override
    public void delete(Long id, Long accountId) {

    }

    /*
    @Override
    public Folder create(Folderable noteable) {
        Note note = new Note();
        note.setTitle(noteable.getTitle());
        note.setContent(noteable.getContent());
        note.setDate(noteable.getDate());
        note.setAccount(new Account(noteable.getAccount().getId()));
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(note);
        entityManager.getTransaction().commit();
        entityManager.close();
        return note;
    }

    @Override
    public Note getById(Long id, Long accountId) {
        EntityManager entityManager = getEntityManager();
        Note note = entityManager
                .createNamedQuery("Note.getById", Note.class)
                .setParameter("id", id)
                .setParameter("accountId", accountId)
                .getSingleResult();
        entityManager.close();
        return note;
    }

    @Override
    public List<Note> getAll(Long accountId) {
        EntityManager entityManager = getEntityManager();
        List<Note> notes = entityManager
                .createNamedQuery("Note.getAll", Note.class)
                .setParameter("accountId", accountId)
                .getResultList();
        entityManager.close();
        return notes;
    }

    @Override
    public UUID share(Long id, Shareable.Permission permission, Long accountId) {
        SharedNote sharedNote = new SharedNote();
        sharedNote.setPermission(permission);
        sharedNote.setNote(getById(id, accountId));
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(sharedNote);
        entityManager.getTransaction().commit();
        entityManager.close();
        return sharedNote.getToken();
    }

    @Override
    public SharedNote getByToken(UUID token) {
        EntityManager entityManager = getEntityManager();
        SharedNote sharedNote = entityManager
                .createNamedQuery("SharedNote.getByToken", SharedNote.class)
                .setParameter("token", token)
                .getSingleResult();
        entityManager.close();
        return sharedNote;
    }

    @Override
    public void update(Noteable noteable, Long accountId) {
        Note note = getById(noteable.getId(), accountId);
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        note.setTitle(noteable.getTitle());
        note.setContent(noteable.getContent());
        note.setDate(new Date());
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(Noteable noteable, UUID token) {
        SharedNote sharedNote = getByToken(token);
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        sharedNote.getNote().setTitle(noteable.getTitle());
        sharedNote.getNote().setContent(noteable.getContent());
        sharedNote.getNote().setDate(new Date());
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(Long id, Long accountId) {
        EntityManager entityManager = getEntityManager();
        entityManager
                .createNamedQuery("Note.delete", Note.class)
                .setParameter("id", id)
                .setParameter("accountId", accountId)
                .getSingleResult();
        entityManager.close();
    }*/
}
