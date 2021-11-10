package ru.kpfu.servlets.servies;

import ru.kpfu.servlets.data.Queries;
import ru.kpfu.servlets.exceptions.NotFoundTagException;
import ru.kpfu.servlets.repositories.TagRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TagService {
    private final TagRepository tagRepository = new TagRepository();


    public TagService() {
    }

    public long findTagByName(String name) throws NotFoundTagException {
        return tagRepository.findTagByName(name);
    }




}
