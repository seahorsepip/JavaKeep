package com.seapip.teunthomas.javakeep.dao;

public class EncryptedNote extends Note {
    private String encryptedContent;

    public String getEncryptedContent() {
        return encryptedContent;
    }

    public void setEncryptedContent(String encryptedContent) {
        this.encryptedContent = encryptedContent;
    }
}
