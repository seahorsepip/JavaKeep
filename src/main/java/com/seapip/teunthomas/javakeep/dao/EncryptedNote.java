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
}
