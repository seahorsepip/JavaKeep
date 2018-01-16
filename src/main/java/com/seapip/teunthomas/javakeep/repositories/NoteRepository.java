package com.seapip.teunthomas.javakeep.repositories;

import com.seapip.teunthomas.javakeep.contexts.NoteContext;
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

    public Note create(Noteable noteable) {
        Noteable n = context.create(noteable);
        Note note = new Note();
        note.setId(n.getId());
        note.setTitle(n.getTitle());
        note.setContent(n.getContent());
        note.setDate(n.getDate());
        return note;
    }

    public Note getById(Long id, Long accountId) {
        Noteable noteable = context.getById(id, accountId);
        Note note = new Note();
        note.setId(noteable.getId());
        note.setTitle(noteable.getTitle());
        note.setContent(noteable.getContent());
        note.setDate(noteable.getDate());
        return note;
    }

    public List<Note> getAll(Long accountId) {
        List<? extends Noteable> noteables = context.getAll(accountId);
        List<Note> notes = new ArrayList<>();
        for (Noteable noteable : noteables) {
            Note note = new Note();
            note.setId(noteable.getId());
            note.setTitle(noteable.getTitle());
            notes.add(note);
        }
        return notes;
    }

    public UUID share(Long id, Shareable.Permission permission, Long accountId) {
        return context.share(id, permission, accountId);
    }

    public SharedNote getByToken(UUID token) {
        Shareable shareable = context.getByToken(token);
        SharedNote sharedNote = new SharedNote();
        Note note = new Note();
        note.setTitle(shareable.getNote().getTitle());
        note.setContent(shareable.getNote().getContent());
        note.setDate(shareable.getNote().getDate());
        sharedNote.setNote(note);
        sharedNote.setPermission(shareable.getPermission());
        sharedNote.setToken(token);
        return sharedNote;
    }
}
