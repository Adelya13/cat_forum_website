package ru.kpfu.servlets.repositories.interfaces;

import ru.kpfu.servlets.entities.Tag;
import ru.kpfu.servlets.exceptions.NotFoundTagException;
import ru.kpfu.servlets.repositories.interfaces.IRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface ITagRepository  extends IRepository<Tag> {
    long addTag(Tag tag);
    long findTagByName(String name) throws NotFoundTagException;
    ArrayList<Tag> findTagByPostId(long postId);
}
