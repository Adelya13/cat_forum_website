package ru.kpfu.servlets.repositories.interfaces;

import ru.kpfu.servlets.entities.Comment;
import ru.kpfu.servlets.entities.Post;

import java.util.ArrayList;

public interface ICommentRepository extends IRepository<Comment> {

    void createComment(Comment comment);
    ArrayList<Comment> findCommentsByPostId(long id);
}
