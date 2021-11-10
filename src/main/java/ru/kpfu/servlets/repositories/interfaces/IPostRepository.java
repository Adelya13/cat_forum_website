package ru.kpfu.servlets.repositories.interfaces;

import ru.kpfu.servlets.entities.Post;
import ru.kpfu.servlets.entities.Tag;
import ru.kpfu.servlets.exceptions.NotFoundBodyInPostException;
import ru.kpfu.servlets.exceptions.NotFoundPostException;

import java.util.ArrayList;
import java.util.Optional;

public interface IPostRepository extends IRepository<Post> {

    void createPost(Post post) throws NotFoundBodyInPostException;
    long addPost(Post post) throws NotFoundBodyInPostException;
    void createDependency(long postId, long tagId);
    long findPostByPath(String path);
    ArrayList<Post> findPostsByAuthorId(long authorId);
    ArrayList<Post> findAllPosts() throws NotFoundPostException;
    ArrayList<Post> findPostsByTag(Tag tag) ;
}
