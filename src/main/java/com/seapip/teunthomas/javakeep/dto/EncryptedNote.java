package com.seapip.teunthomas.javakeep.dto;

public class EncryptedNote extends Note {

    private String encryptedContent;

    public String getEncryptedContent() {
        return encryptedContent;
    }

    public EncryptedNote setEncryptedContent(String encryptedContent) {
        this.encryptedContent = encryptedContent;
        return this;
    }

    @Override
    public Type getType() {
        return Type.ENCRYPTED;
    }
}
