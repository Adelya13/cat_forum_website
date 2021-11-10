package ru.kpfu.servlets.servies.interfaces;

import ru.kpfu.servlets.entities.Comment;

import java.util.ArrayList;

public interface ICommentService extends IService<Comment> {
    void createComment(Comment comment);
    ArrayList<Comment> findCommentsByPostId(long id);
}
