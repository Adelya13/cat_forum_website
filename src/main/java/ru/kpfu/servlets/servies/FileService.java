package ru.kpfu.servlets.servies;

import ru.kpfu.servlets.entities.DbFile;
import ru.kpfu.servlets.entities.User;
import ru.kpfu.servlets.exceptions.DuplicateDataException;
import ru.kpfu.servlets.exceptions.NotFoundFileException;
import ru.kpfu.servlets.repositories.FileRepository;
import ru.kpfu.servlets.servies.interfaces.IFileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileService implements IFileService{

    private final String storagePath ="C:\\Users\\Adelya\\Desktop\\programming_2021\\home_work1\\src\\main\\webapp\\photo\\user_avatars\\";
    private final FileRepository fileRepository = new FileRepository();



    @Override
    public DbFile upload(String fileName, InputStream fileInputStream) {
        try {
            UUID uuid = UUID.randomUUID();
            Path filePath = Paths.get(storagePath, uuid.toString()+'.'+getFileExtension(fileName, "."));
            System.out.println(filePath.toString());
            Files.copy(fileInputStream, filePath);

            String mimeType = Files.probeContentType(filePath);
            long size = Files.size(filePath);

            DbFile dbFile = new DbFile();
            dbFile.setFileName(fileName);
            dbFile.setUuid(uuid);
            dbFile.setSize(size);
            dbFile.setMimeType(mimeType);
            fileRepository.addFile(dbFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void download(String fileName, OutputStream responseOutputStream) throws NotFoundFileException {

    }

    protected String getFileExtension(String fileName, String delimiter) {
        String extension = null;

        int i = fileName.lastIndexOf(delimiter);
        if (i >= 0) {
            extension = fileName.substring(i+1);
        }
        return extension;

    }

    @Override
    public void download(HttpServletResponse resp, HttpServletRequest req, long id) throws NotFoundFileException {

       try {
           DbFile dbFile = fileRepository.findById(id).orElse(null);
           if (dbFile == null) {
               throw new NotFoundFileException();
           }
           req.setAttribute("avatarPath",dbFile.getFileName());

           resp.setContentType(dbFile.getMimeType());
           Path filePath = Paths.get(storagePath, dbFile.getUuid().toString() +'.'+getFileExtension(dbFile.getMimeType(),"/"));

           Files.copy(filePath, resp.getOutputStream());


       } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
