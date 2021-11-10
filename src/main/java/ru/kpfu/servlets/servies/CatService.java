package ru.kpfu.servlets.servies;

import ru.kpfu.servlets.entities.Cat;
import ru.kpfu.servlets.exceptions.DuplicateDataException;
import ru.kpfu.servlets.repositories.CatRepository;
import ru.kpfu.servlets.servies.interfaces.ICatService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CatService implements ICatService {
    private final CatRepository catRepository = new CatRepository();


    @Override
    public void save(Cat entity) {
        catRepository.save(entity);
    }

    @Override
    public void update(Cat entity) throws DuplicateDataException {
        catRepository.update(entity);
    }

    @Override
    public void delete(Cat entity) {
        catRepository.delete(entity);
    }

    @Override
    public Optional<Cat> findById(Long id) {
        return catRepository.findById(id);
    }

    @Override
    public List<Cat> findAll() {
        return catRepository.findAll();
    }

    @Override
    public ArrayList<Cat> findCatsByUserId(long id) {
        return catRepository.findCatsByUserId(id);
    }
}
