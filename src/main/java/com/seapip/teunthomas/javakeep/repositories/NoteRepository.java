package com.seapip.teunthomas.javakeep.repositories;

import com.seapip.teunthomas.javakeep.contexts.NoteContext;
import com.seapip.teunthomas.javakeep.dto.Account;
import com.seapip.teunthomas.javakeep.dto.EncryptedNote;
import com.seapip.teunthomas.javakeep.dto.Note;
import com.seapip.teunthomas.javakeep.entities.Noteable;
import org.jasypt.util.text.BasicTextEncryptor;

import java.util.ArrayList;
import java.util.List;

public class NoteRepository {
    private NoteContext context;

    public NoteRepository(NoteContext context) {
        this.context = context;
    }

    public Noteable create(Note note, Long accountId, String password) {
        note.setAccount(new Account(accountId));
        Noteable noteable = context.create(password.equals("") ? note : encrypt(note, password));
        return new Note()
                .setId(noteable.getId())
                .setTitle(noteable.getTitle())
                .setContent(noteable.getContent())
                .setDate(noteable.getDate());
    }

    public Note getById(Long id, Long accountId, String password) {
        Noteable noteable = context.getById(id, accountId);
        return new Note()
                .setId(noteable.getId())
                .setTitle(noteable.getTitle())
                .setContent(noteable.getType() == Note.Type.ENCRYPTED ? decrypt((com.seapip.teunthomas.javakeep.dao.EncryptedNote) noteable, password) : noteable.getContent())
                .setDate(noteable.getDate());
    }

    public List<Note> getAll(Long accountId) {
        List<? extends Noteable> noteables = context.getAll(accountId);
        List<Note> notes = new ArrayList<>();
        for (Noteable noteable : noteables) {
            notes.add((noteable.getType() == Note.Type.ENCRYPTED ? new EncryptedNote() : new Note())
                    .setId(noteable.getId())
                    .setTitle(noteable.getTitle())
            );
        }
        return notes;
    }

    public void update(Note note, Long accountId, String password) {
        if (password.equals("")) context.update(note, accountId);
        else context.update(encrypt(note, password), accountId);
    }

    public void delete(Long id, Long accountId) {
        context.delete(id, accountId);
    }

    private EncryptedNote encrypt(Note note, String password) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(password);
        return (EncryptedNote) new EncryptedNote()
                .setEncryptedContent(textEncryptor.encrypt(note.getContent()))
                .setId(note.getId())
                .setTitle(note.getTitle())
                .setAccount(note.getAccount());
    }

    private String decrypt(com.seapip.teunthomas.javakeep.dao.EncryptedNote encryptedNote, String password) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(password);
        return textEncryptor.decrypt(encryptedNote.getEncryptedContent());
    }
}
