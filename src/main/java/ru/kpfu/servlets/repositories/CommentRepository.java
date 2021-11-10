package ru.kpfu.servlets.repositories;

import org.mindrot.jbcrypt.BCrypt;
import ru.kpfu.servlets.data.Queries;
import ru.kpfu.servlets.entities.Comment;
import ru.kpfu.servlets.entities.Post;
import ru.kpfu.servlets.entities.User;
import ru.kpfu.servlets.exceptions.DuplicateDataException;
import ru.kpfu.servlets.repositories.interfaces.ICommentRepository;
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

public class CommentRepository implements ICommentRepository{

    Connection con;

    public CommentRepository() {
        DbSystem system = new DbSystem();
        con = system.getCon();
    }

    @Override
    public void createComment(Comment comment) {
        try(PreparedStatement st = con.prepareStatement(Queries.CREATE_COMMENT)){
            int i =1;
            st.setLong(i++,comment.getAuthor().getId());
            st.setLong(i++,comment.getPost().getId());
            st.setString(i++,comment.getText());
            st.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Comment> findCommentsByPostId(long id) {
        ArrayList<Comment> comments = new ArrayList<>();
        UserRepository userRepository = new UserRepository();
        PostRepository postRepository = new PostRepository();

        try(PreparedStatement st = con.prepareStatement(Queries.FIND_COMMENTS_BY_POST_ID)){

            st.setLong(1, id);
            st.execute();

            try(ResultSet result = st.getResultSet()){

                while(result.next()){
                    long commentId = result.getLong("id");
                    long authorId = result.getLong("author_id");
                    String text = result.getString("text");
                    User user = userRepository.findById(authorId).get();
                    Post post = postRepository.findById(id).get();
                    Comment comment = new Comment(user,post,text,id);
                    comments.add(comment);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public void save(Comment entity) {

    }

    @Override
    public void update(Comment entity) throws DuplicateDataException {

    }

    @Override
    public void delete(Comment entity) {

    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }


}
