package ru.kpfu.servlets.entities;

import java.util.Objects;

public class Comment {

    private long id;
    private User author;
    private Post post;
    private String text;
    private String authorName;
    private long likeCnt =0;


    public Comment(User author, Post post, String text) {
        this.author = author;
        this.post = post;
        this.text = text;
    }

    public Comment(User author, Post post, String text, long id) {
        this.author = author;
        this.authorName = author.getUsername();
        this.post = post;
        this.text = text;
        this.id = id;
    }



    public String getAuthorName() {
        return authorName;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(long likeCnt) {
        this.likeCnt = likeCnt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
