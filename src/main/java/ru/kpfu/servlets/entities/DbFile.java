package ru.kpfu.servlets.entities;

import java.util.UUID;

public class DbFile {

    private String fileName;
    private UUID uuid;
    private long size;
    private String mimeType;
    private long id;

    public DbFile() {
    }

    public DbFile(String fileName, UUID uuid, long size, String mimeType) {
        this.fileName = fileName;
        this.uuid = uuid;
        this.size = size;
        this.mimeType = mimeType;
    }

    public DbFile(String fileName, UUID uuid, long size, String mimeType, long id) {
        this.fileName = fileName;
        this.uuid = uuid;
        this.size = size;
        this.mimeType = mimeType;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
