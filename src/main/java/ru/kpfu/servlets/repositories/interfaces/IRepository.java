package ru.kpfu.servlets.repositories.interfaces;

import ru.kpfu.servlets.entities.User;
import ru.kpfu.servlets.exceptions.DuplicateDataException;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
    void save(T entity);
    void update(T entity) throws DuplicateDataException;
    void delete(T entity);
    Optional<T> findById(Long id);
    List<T> findAll();
}
