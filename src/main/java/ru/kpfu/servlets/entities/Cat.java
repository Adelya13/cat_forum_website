package ru.kpfu.servlets.entities;

import java.util.Objects;

public class Cat {
    private String name;
    private String description;
    private String photoPath;
    private byte[] photoDate;
    private User owner;
    private long id;


    public Cat(String name, String description, User owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;
    }
    public Cat(String name, String description, long id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public byte[] getPhotoDate() {
        return photoDate;
    }

    public void setPhotoDate(byte[] photoDate) {
        this.photoDate = photoDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(name, cat.name) &&
                Objects.equals(description, cat.description) &&
                Objects.equals(owner, cat.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, owner);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", avatarPath='" + photoPath + '\'' +
                ", owner=" + owner +
                '}';
    }
}
