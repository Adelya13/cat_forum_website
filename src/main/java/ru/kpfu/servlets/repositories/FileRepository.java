package ru.kpfu.servlets.repositories;

import ru.kpfu.servlets.data.Queries;
import ru.kpfu.servlets.entities.DbFile;
import ru.kpfu.servlets.exceptions.DuplicateDataException;
import ru.kpfu.servlets.repositories.interfaces.IFileRepository;
import ru.kpfu.servlets.systems.DbSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FileRepository implements IFileRepository {

    Connection con;

    public FileRepository() {
        DbSystem system = new DbSystem();
        con = system.getCon();
    }
    public long addFile(DbFile dbFile){
        try(PreparedStatement st = con.prepareStatement(Queries.ADD_FILE)){

            int i=1;
            st.setString(i++,dbFile.getFileName());
            st.setObject(i++, dbFile.getUuid());
            System.out.println(dbFile.getMimeType());
            st.setString(i++,dbFile.getMimeType());
            st.setLong(i++,dbFile.getSize());
            st.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;

    }


    @Override
    public void save(DbFile entity) {

    }

    @Override
    public void update(DbFile entity) throws DuplicateDataException {

    }

    @Override
    public void delete(DbFile entity) {

    }

    @Override
    public Optional<DbFile> findById(Long id) {
        try(PreparedStatement st = con.prepareStatement(Queries.FIND_FILE_BY_ID)){

            st.setLong(1, id);
            st.execute();

            try(ResultSet result = st.getResultSet()){
                if(result.next()){
                    String name = result.getString("name");
                    long size = result.getLong("size");
                    UUID uuid = (UUID) result.getObject("uuid");
                    String mimeType = result.getString("mimeType");
                    DbFile dbFile = new DbFile(name,uuid,size,mimeType,id);
                    return Optional.of(dbFile);
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
    public List<DbFile> findAll() {
        return null;
    }
}
