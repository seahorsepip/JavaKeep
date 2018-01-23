package com.seapip.teunthomas.javakeep.repositories;

import com.seapip.teunthomas.javakeep.contexts.FolderContext;
import com.seapip.teunthomas.javakeep.dto.Folder;
import com.seapip.teunthomas.javakeep.dto.Note;
import com.seapip.teunthomas.javakeep.entities.Folderable;
import com.seapip.teunthomas.javakeep.entities.Noteable;

import java.util.ArrayList;
import java.util.List;

public class FolderRepository {
    private FolderContext context;

    public FolderRepository(FolderContext context) {
        this.context = context;
    }

    public Folder create(Folder folder, Long accountId) {
        Folderable folderable = context.create(folder, accountId);
        ArrayList<Note> notes = new ArrayList<>();
        Folder newFolder = new Folder()
                .setId(folderable.getId())
                .setName(folderable.getName())
                .setNotes(notes);
        if (folderable.getNotes() != null) for (Noteable noteable : folderable.getNotes()) {
            notes.add(new Note()
                    .setId(noteable.getId())
                    .setTitle(noteable.getTitle())
            );
        }
        return newFolder;

    }

    public Folder getById(Long id, Long accountId) {
        Folderable folderable = context.getById(id, accountId);
        ArrayList<Note> notes = new ArrayList<>();
        Folder newFolder = new Folder()
                .setId(folderable.getId())
                .setName(folderable.getName())
                .setNotes(notes);
        for (Noteable noteable : folderable.getNotes()) {
            notes.add(new Note()
                    .setId(noteable.getId())
                    .setTitle(noteable.getTitle())
            );
        }
        return newFolder;
    }

    public List<Folder> getAll(Long accountId) {
        List<? extends Folderable> folderables = context.getAll(accountId);
        List<Folder> folders = new ArrayList<>();
        for (Folderable folderable : folderables) {
            folders.add(new Folder()
                    .setId(folderable.getId())
                    .setName(folderable.getName())
            );
        }
        return folders;
    }

    public void share(Long id, String email, Long accountId) {
        context.share(id, email, accountId);
    }

    /*
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
    }*/

    /*
    public void update(Note note, Long accountId) {
        context.update(note, accountId);
    }

    public void update(Note note, UUID token) {
        context.update(note, token);
    }

    public void delete(Long id, Long accountId) {
        context.delete(id, accountId);
    }
    */
}
