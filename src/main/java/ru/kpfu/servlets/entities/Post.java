package ru.kpfu.servlets.entities;

import ru.kpfu.servlets.exceptions.NotFoundBodyInPostException;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Post {
   private long id;
   private String body;
   private Path path;
   private User author;
   private long authorId;
   private String authorName;
   private String name;
   private ArrayList<Tag> tags;

    public Post(String body, User author, String tags, String name) throws NotFoundBodyInPostException {

        if(body != null){
            this.body = body;
            this.path = createFileForBody(body,author);
        }
        else{
            throw new NotFoundBodyInPostException();
        }
        this.author = author;
        this.authorName = author.getUsername();
        this.authorId = author.getId();
        this.tags = createTags(tags);
        this.name = name;
    }

    public Post(long id, Path path, User author, String name) {
        this.id = id;
        this.path = path;
        this.body = getTextBody();
        this.author = author;
        this.authorName = author.getUsername();
        this.authorId = author.getId();
        this.name = name;
    }

    private ArrayList<Tag> createTags(String tagsLine){
        Pattern tagsPattern= Pattern.compile("#([^\\s#]+)",
                Pattern.CASE_INSENSITIVE+ Pattern.DOTALL);
        Matcher tagsMatcher = tagsPattern.matcher(tagsLine);
        ArrayList<Tag> postTags = new ArrayList<>();
        while(tagsMatcher.find()){
            Tag tag = new Tag(tagsMatcher.group(1));
            postTags.add(tag);
        }
        return postTags;
    }


    private Path createFileForBody(String body, User user){
        String fileName = "";


        int num = (int) (Math.random() * 100000)+body.length();

        fileName = "\\"
                +"post_"
                +String.valueOf(user.getId())+ "_"
                +String.valueOf(num) + "_"
                + user.hashCode()
                + ".txt"  ;
        Path file = Paths.get("C:\\Users\\Adelya\\Desktop\\programming_2021\\home_work1\\src\\main\\webapp\\posts"+ fileName);

        try(OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file.toString()), StandardCharsets.UTF_8)) {
            writer.write(body);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;


    }
    public long getId() {
        return id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public String getBody() {
        return body;
    }
    public String getTextBody() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path.toString()), StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String currentLine;
            while((currentLine = reader.readLine()) != null){
                sb.append(currentLine);
            }
            return  sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }


    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", author=" + author +
                ", name='" + name + '\'' +
                ", tags=" + Arrays.toString(tags.toArray()) +
                '}';
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
