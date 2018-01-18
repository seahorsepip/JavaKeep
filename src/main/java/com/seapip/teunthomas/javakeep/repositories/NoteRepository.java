package com.seapip.teunthomas.javakeep.repositories;

import com.seapip.teunthomas.javakeep.contexts.NoteContext;
import com.seapip.teunthomas.javakeep.dto.Account;
import com.seapip.teunthomas.javakeep.dto.Note;
import com.seapip.teunthomas.javakeep.dto.SharedNote;
import com.seapip.teunthomas.javakeep.entities.Noteable;
import com.seapip.teunthomas.javakeep.entities.Shareable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NoteRepository {
    private NoteContext context;

    public NoteRepository(NoteContext context) {
        this.context = context;
    }

    public Note create(Note note, Long accountId) {
        note.setAccount(new Account(accountId));
        Noteable noteable = context.create(note);
        return new Note()
                .setId(noteable.getId())
                .setTitle(noteable.getTitle())
                .setContent(noteable.getContent())
                .setDate(noteable.getDate());
    }

    public Note getById(Long id, Long accountId) {
        Noteable noteable = context.getById(id, accountId);
        return new Note()
                .setId(noteable.getId())
                .setTitle(noteable.getTitle())
                .setContent(noteable.getContent())
                .setDate(noteable.getDate());
    }

    public List<Note> getAll(Long accountId) {
        List<? extends Noteable> noteables = context.getAll(accountId);
        List<Note> notes = new ArrayList<>();
        for (Noteable noteable : noteables) {
            notes.add(new Note()
                    .setId(noteable.getId())
                    .setTitle(noteable.getTitle())
            );
        }
        return notes;
    }

    public UUID share(Long id, Shareable.Permission permission, Long accountId) {
        return context.share(id, permission, accountId);
    }

    public SharedNote getByToken(UUID token) {
        Shareable shareable = context.getByToken(token);
        return new SharedNote()
                .setNote(new Note()
                        .setTitle(shareable.getNote().getTitle())
                        .setContent(shareable.getNote().getContent())
                        .setDate(shareable.getNote().getDate())
                )
                .setPermission(shareable.getPermission())
                .setToken(token);
    }

    public void update(Note note, Long accountId) {
        context.update(note, accountId);
    }

    public void update(Note note, UUID token) {
        context.update(note, token);
    }

    public void delete(Long id, Long accountId) {
        context.delete(id, accountId);
    }
}
