package ru.kpfu.servlets.servies.interfaces;

import ru.kpfu.servlets.entities.DbFile;
import ru.kpfu.servlets.entities.User;
import ru.kpfu.servlets.exceptions.DuplicateDataException;
import ru.kpfu.servlets.exceptions.NotFoundFileException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;

public interface IFileService {
    DbFile upload(String fileName, InputStream fileInputStream);
    void download(String fileName, OutputStream responseOutputStream)throws NotFoundFileException;

    void download(HttpServletResponse resp, HttpServletRequest req, long id) throws NotFoundFileException;
}
