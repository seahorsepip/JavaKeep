package com.seapip.teunthomas.javakeep.repositories;

import com.seapip.teunthomas.javakeep.contexts.NoteContext;
import com.seapip.teunthomas.javakeep.dto.Note;
import com.seapip.teunthomas.javakeep.entities.Noteable;

public class NoteRepository {
    private NoteContext context;

    public NoteRepository(NoteContext context) {
        this.context = context;
    }

    public Note getById(Long id, Long accountId) {
        Noteable noteable = context.getById(id, accountId);
        Note note = new Note();
        note.setId(noteable.getId());
        note.setTitle(noteable.getTitle());
        return note;
    }
}
