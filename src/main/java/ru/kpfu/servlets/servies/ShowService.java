package ru.kpfu.servlets.servies;

import ru.kpfu.servlets.entities.*;
import ru.kpfu.servlets.exceptions.NotFoundPostException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ShowService {

    public ShowService() {
    }

    public void showPostsByTag(Tag tag, HttpServletRequest req, PostService postService){
        ArrayList<Post> posts;
        try{
            posts = postService.findPostsByTag(tag);
            req.setAttribute("allposts",posts);
        }catch (NotFoundPostException e){
            req.setAttribute("message","Поста с таким тегом не существует");
        }
    }

    public void showAllPost(HttpServletRequest req, PostService postService){
        ArrayList<Post> posts;
        try{
            posts = postService.findAllPosts();
            req.setAttribute("allposts",posts);
        }catch (NotFoundPostException e){
            req.setAttribute("message","Такого тега не существует");
        }
    }

    public void showPostsByUserId(User user, HttpServletRequest req, PostService postService){
        ArrayList<Post> posts;
        posts = postService.findPostsByAuthorId(user.getId());
        if(posts.size() > 0){
            req.setAttribute("allposts",posts);

        }else{
            req.setAttribute("postMessage","Пока что у вас нет постов");
        }
    }

    public void showCats(User user,HttpServletRequest req, CatService catService){
        ArrayList<Cat> cats;
        cats = catService.findCatsByUserId(user.getId());
        if(cats.size() > 0){
            req.setAttribute("allcats",cats);
        }else{
            req.setAttribute("infoMessage","Пока что вы не добавили котиков");
        }
    }

    public void showComments(Post post,HttpServletRequest req, CommentService commentService){
        ArrayList<Comment> comments;
        comments = commentService.findCommentsByPostId(post.getId());
        if(comments.size() > 0){
            req.setAttribute("comments",comments);
        }
    }

}
