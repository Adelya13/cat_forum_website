package ru.kpfu.servlets.repositories;

import ru.kpfu.servlets.data.Queries;
import ru.kpfu.servlets.entities.Tag;
import ru.kpfu.servlets.exceptions.NotFoundTagException;
import ru.kpfu.servlets.repositories.interfaces.ITagRepository;
import ru.kpfu.servlets.systems.DbSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TagRepository implements ITagRepository{


    Connection con;

    public TagRepository() {
        DbSystem system = new DbSystem();
        con = system.getCon();
    }
    @Override
    public long addTag(Tag tag) {
        long id;
        try {
             id = findTagByName(tag.getTagName());
             return id;
        }catch (NotFoundTagException e1){
            try(PreparedStatement st = con.prepareStatement(Queries.ADD_TAG)){

                st.setString(1,tag.getTagName());
                st.execute();
                id = findTagByName(tag.getTagName());
                return id;

            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public long findTagByName(String name) throws NotFoundTagException{
        try(PreparedStatement st = con.prepareStatement(Queries.FIND_TAG_BY_NAME)){

            st.setString(1, name);
            st.execute();

            try(ResultSet result = st.getResultSet()){
                if(result.next()){
                    long id =  result.getLong("id");
                    return id;
                }
                else throw new NotFoundTagException();
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
    public ArrayList<Tag> findTagByPostId(long postId) {
        try(PreparedStatement st = con.prepareStatement(Queries.FIND_TAGS_BY_POST_ID)){

            st.setLong(1, postId);
            st.execute();

            try(ResultSet result = st.getResultSet()){
                ArrayList<Tag> tags = new ArrayList<>();
                while(result.next()){
                    long id = result.getLong("id");
                    String name = result.getString("name");
                    Tag tag = new Tag(id,name);
                    tags.add(tag);
                }
                return tags;
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void save(Tag entity) {
        try(PreparedStatement st = con.prepareStatement(Queries.ADD_TAG)){

            st.setString(1,entity.getTagName());
            st.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Tag entity) {

    }

    @Override
    public void delete(Tag entity) {

    }

    @Override
    public Optional<Tag> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Tag> findAll() {
        return null;
    }
}
