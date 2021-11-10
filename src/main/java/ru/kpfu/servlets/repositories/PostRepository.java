package ru.kpfu.servlets.repositories;

import ru.kpfu.servlets.data.Queries;
import ru.kpfu.servlets.entities.Post;
import ru.kpfu.servlets.entities.Tag;
import ru.kpfu.servlets.entities.User;
import ru.kpfu.servlets.exceptions.NotFoundBodyInPostException;
import ru.kpfu.servlets.exceptions.NotFoundPostException;
import ru.kpfu.servlets.repositories.interfaces.IPostRepository;
import ru.kpfu.servlets.systems.DbSystem;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostRepository implements IPostRepository {

    Connection con;

    public PostRepository() {
        DbSystem system = new DbSystem();
        con = system.getCon();
    }
    @Override
    public void createPost(Post post) throws NotFoundBodyInPostException {
        TagRepository tagRepository = new TagRepository();
        long postId = addPost(post);
        ArrayList<Tag> tags = post.getTags();
        tags.forEach(tag ->{
            long tagId = tagRepository.addTag(tag);
            createDependency(postId,tagId);
        });

    }

    @Override
    public long addPost(Post post) throws NotFoundBodyInPostException {
        try(PreparedStatement st = con.prepareStatement(Queries.ADD_POST)){

            int i=1;
            if(!post.getName().equals("")){
                st.setString(i++,post.getName());
                st.setLong(i++, post.getAuthor().getId());
                st.setString(i++,post.getPath().toString());
                st.execute();
                long id = findPostByPath(post.getPath().toString());
                return id;
            }
            else throw new NotFoundBodyInPostException();


        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void createDependency(long postId, long tagId) {
        try(PreparedStatement st = con.prepareStatement(Queries.ADD_POST_TAG_DEPENDENCY)){

            int i=1;
            st.setLong(i++,postId);
            st.setLong(i++, tagId);
            st.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long findPostByPath(String path) {
        try(PreparedStatement st = con.prepareStatement(Queries.FIND_POST_BY_PATH)){

            st.setString(1, path);
            st.execute();

            try(ResultSet result = st.getResultSet()){
                if(result.next()){
                    long id = result.getLong("id");
                    return id;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }




    @Override
    public ArrayList<Post> findPostsByAuthorId(long authorId) {
        ArrayList<Post> posts = new ArrayList<>();
        UserRepository userRepository = new UserRepository();
        TagRepository tagRepository = new TagRepository();
        try(PreparedStatement st = con.prepareStatement(Queries.FIND_POSTS_BY_AUTHOR_ID)){

            st.setLong(1, authorId);
            st.execute();

            try(ResultSet result = st.getResultSet()){

                while(result.next()){
                    long id = result.getLong("id");
                    String tittle = result.getString("name");
                    Path path = Paths.get(result.getString("text"));
                    createPostByAttributes(posts, userRepository, tagRepository, result, id, authorId, tittle,path);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public ArrayList<Post> findAllPosts() throws NotFoundPostException {
        ArrayList<Post> posts = new ArrayList<>();
        UserRepository userRepository = new UserRepository();
        TagRepository tagRepository = new TagRepository();
        try(PreparedStatement st = con.prepareStatement(Queries.FIND_All_POSTS)){

            st.execute();

            try(ResultSet result = st.getResultSet()){
                while(result.next()){
                    long id = result.getLong("id");
                    long authorId = result.getLong("author_id");
                    String tittle = result.getString("name");
                    Path path = Paths.get(result.getString("text"));
                    createPostByAttributes(posts, userRepository, tagRepository, result, id, authorId, tittle,path);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        if(posts.size()==0){
            throw new NotFoundPostException();
        }
        return posts;
    }

    @Override
    public ArrayList<Post> findPostsByTag(Tag tag) throws NotFoundPostException {
        ArrayList<Post> posts = new ArrayList<>();
        UserRepository userRepository = new UserRepository();
        TagRepository tagRepository = new TagRepository();
        try(PreparedStatement st = con.prepareStatement(Queries.FIND_POSTS_BY_TAG_ID)){

            st.setLong(1, tag.getId());
            st.execute();

            try(ResultSet result = st.getResultSet()){
                while(result.next()){
                    long id = result.getLong("id");
                    long authorId = result.getLong("author_id");
                    String tittle = result.getString("name");
                    Path path = Paths.get(result.getString("text"));
                    createPostByAttributes(posts, userRepository, tagRepository, result, id, authorId, tittle, path);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        if(posts.size()==0){
            throw new NotFoundPostException();
        }
        return posts;
    }

    @Override
    public void save(Post entity) {
        try(PreparedStatement st = con.prepareStatement(Queries.ADD_POST)){

                int i=1;
                st.setString(i++,entity.getName());
                st.setLong(i++, entity.getAuthor().getId());
                st.setString(i++,entity.getBody());
                st.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Post entity) {

    }

    @Override
    public void delete(Post entity) {

    }

    @Override
    public Optional<Post> findById(Long id) {
        TagRepository tagRepository = new TagRepository();
        UserRepository userRepository = new UserRepository();

        try(PreparedStatement st = con.prepareStatement(Queries.FIND_POST_BY_ID)){

            st.setLong(1, id);
            st.execute();

            try(ResultSet result = st.getResultSet()){
                if(result.next()){
                    String name = result.getString("name");
                    long authorId = result.getLong("author_id");
                    Path path= Paths.get(result.getString("text"));

                    Optional<User> userOptional = userRepository.findById(authorId);


                    Post post = new Post(id,path, userOptional.get(),name);
                    ArrayList<Tag> tags = tagRepository.findTagByPostId(id);
                    post.setTags(tags);
                    return Optional.of(post);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Post> findAll() {
        return null;
    }


    private void createPostByAttributes(ArrayList<Post> posts, UserRepository userRepository,
                                        TagRepository tagRepository, ResultSet result,
                                        long id, long authorId,
                                        String tittle,
                                        Path path) {
        User user = userRepository.findById(authorId).get();
        ArrayList<Tag> tags = tagRepository.findTagByPostId(id);
        Post post = new Post(id,path,user,tittle);
        post.setTags(tags);
        posts.add(post);
    }

}
