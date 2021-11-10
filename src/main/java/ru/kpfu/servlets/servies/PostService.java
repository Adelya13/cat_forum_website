package ru.kpfu.servlets.servies;

import ru.kpfu.servlets.entities.Post;
import ru.kpfu.servlets.entities.Tag;
import ru.kpfu.servlets.exceptions.DuplicateDataException;
import ru.kpfu.servlets.exceptions.NotFoundBodyInPostException;
import ru.kpfu.servlets.exceptions.NotFoundPostException;
import ru.kpfu.servlets.repositories.PostRepository;
import ru.kpfu.servlets.servies.interfaces.IPostService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostService implements IPostService {

    private final PostRepository postRepository;

    public PostService() {
        this.postRepository = new PostRepository();
    }
    @Override
    public void save(Post entity) {
        postRepository.save(entity);
    }

    @Override
    public void update(Post entity) throws DuplicateDataException {
        postRepository.update(entity);
    }

    @Override
    public void delete(Post entity) {
        postRepository.delete(entity);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }


    @Override
    public void createPost(Post post) throws NotFoundBodyInPostException {
        postRepository.createPost(post);
    }

    @Override
    public ArrayList<Post> findPostsByAuthorId(long authorId) {
        return postRepository.findPostsByAuthorId(authorId);
    }

    @Override
    public ArrayList<Post> findAllPosts()throws NotFoundPostException {
        return postRepository.findAllPosts();
    }

    @Override
    public ArrayList<Post> findPostsByTag(Tag tag) throws NotFoundPostException{
        return postRepository.findPostsByTag(tag);
    }
}
