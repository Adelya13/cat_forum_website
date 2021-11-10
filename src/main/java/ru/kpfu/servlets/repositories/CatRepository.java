package ru.kpfu.servlets.repositories;

import ru.kpfu.servlets.data.Queries;
import ru.kpfu.servlets.entities.Cat;
import ru.kpfu.servlets.entities.Post;
import ru.kpfu.servlets.exceptions.DuplicateDataException;
import ru.kpfu.servlets.repositories.interfaces.ICatRepository;
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

public class CatRepository implements ICatRepository {

    Connection con;

    public CatRepository() {
        DbSystem system = new DbSystem();
        con = system.getCon();
    }



    @Override
    public ArrayList<Cat> findCatsByUserId(long id) {
        ArrayList<Cat> cats = new ArrayList<>();
        try(PreparedStatement st = con.prepareStatement(Queries.FIND_CATS_BY_USER_ID)){

            st.setLong(1, id);
            st.execute();

            try(ResultSet result = st.getResultSet()){

                while(result.next()){
                    long catid = result.getLong("id");
                    String name= result.getString("cat_name");
                    String description = result.getString("description");
                    Cat cat = new Cat(name,description,catid);
                    cats.add(cat);
                }

            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return cats;
    }
    @Override
    public void save(Cat entity) {
        try(PreparedStatement st = con.prepareStatement(Queries.ADD_CAT)){

            int i=1;
            System.out.println(entity.getOwner().getId());
            st.setLong(i++,entity.getOwner().getId());
            st.setString(i++, entity.getName());
            st.setString(i++,entity.getDescription());
            st.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Cat entity) throws DuplicateDataException {

    }

    @Override
    public void delete(Cat entity) {

    }

    @Override
    public Optional<Cat> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Cat> findAll() {
        return null;
    }



}
