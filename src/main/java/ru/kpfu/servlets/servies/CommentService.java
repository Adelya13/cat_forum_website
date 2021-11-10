package ru.kpfu.servlets.servies;

import ru.kpfu.servlets.entities.Comment;
import ru.kpfu.servlets.exceptions.DuplicateDataException;
import ru.kpfu.servlets.repositories.CommentRepository;
import ru.kpfu.servlets.servies.interfaces.ICommentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentService implements ICommentService {
    private CommentRepository commentRepository = new CommentRepository();
    @Override
    public void createComment(Comment comment) {
        commentRepository.createComment(comment);
    }

    @Override
    public ArrayList<Comment> findCommentsByPostId(long id) {
        return commentRepository.findCommentsByPostId(id);
    }

    @Override
    public void save(Comment entity) {
        commentRepository.save(entity);
    }

    @Override
    public void update(Comment entity) throws DuplicateDataException {
        commentRepository.update(entity);
    }

    @Override
    public void delete(Comment entity) {
        commentRepository.delete(entity);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
