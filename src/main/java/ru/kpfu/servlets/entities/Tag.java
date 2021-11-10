package ru.kpfu.servlets.entities;

public class Tag {
    private long id;
    private String tagName;

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public Tag(long id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public long getId() {
        return id;
    }

}
