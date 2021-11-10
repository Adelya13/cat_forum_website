package ru.kpfu.servlets.servies.interfaces;


import ru.kpfu.servlets.entities.Cat;

import java.util.ArrayList;

public interface ICatService extends IService<Cat> {

    ArrayList<Cat> findCatsByUserId(long id);

}
