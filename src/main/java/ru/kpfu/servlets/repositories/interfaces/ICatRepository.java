package ru.kpfu.servlets.repositories.interfaces;

import ru.kpfu.servlets.entities.Cat;

import java.util.ArrayList;

public interface ICatRepository extends IRepository<Cat> {
    ArrayList<Cat> findCatsByUserId(long id);

}
