package ru.kpfu.servlets.repositories.interfaces;

import ru.kpfu.servlets.entities.DbFile;
import ru.kpfu.servlets.entities.User;

public interface IFileRepository extends IRepository<DbFile> {
    long addFile(DbFile dbFile);
}
