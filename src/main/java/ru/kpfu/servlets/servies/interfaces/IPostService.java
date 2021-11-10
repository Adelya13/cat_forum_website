package ru.kpfu.servlets.servies.interfaces;

import ru.kpfu.servlets.entities.Post;
import ru.kpfu.servlets.entities.Tag;
import ru.kpfu.servlets.exceptions.NotFoundBodyInPostException;
import ru.kpfu.servlets.exceptions.NotFoundPostException;

import java.util.ArrayList;

public interface IPostService extends IService<Post> {

    void createPost(Post post) throws NotFoundBodyInPostException;
    ArrayList<Post> findPostsByAuthorId(long authorId);
    ArrayList<Post> findAllPosts() throws NotFoundPostException;
    ArrayList<Post> findPostsByTag(Tag tag) throws NotFoundPostException;
}
