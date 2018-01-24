package com.seapip.teunthomas.javakeep.dao;

import javax.persistence.Entity;

@Entity
public class EncryptedNote extends Note {
    private String encryptedContent;

    public String getEncryptedContent() {
        return encryptedContent;
    }

    public void setEncryptedContent(String encryptedContent) {
        this.encryptedContent = encryptedContent;
    }

    @Override
    public com.seapip.teunthomas.javakeep.dto.Note.Type getType() {
        return com.seapip.teunthomas.javakeep.dto.Note.Type.ENCRYPTED;
    }
}
