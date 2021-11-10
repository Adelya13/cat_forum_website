package ru.kpfu.servlets.data;

public class Queries {
//User query
    public static String ADD_USER = "insert into public.user (username,email,password,avatar) values(?,?,?,4)";

    public static String FIND_USER_BY_EMAIL = "select * from public.user where email=?";

    public static String FIND_USER_BY_ID = "select * from public.user where id=?";

    public static String UPDATE_USER =
            "update public.user" +
                    " set username = ? , email = ?, avatar_path = ?" +
                    "where id=?;";

    public static String UPDATE_AVATAR =
            "update public.user" +
                    " set avatar = ?" +
                    "where id=?;";


//Tag query
    public static String ADD_TAG = "insert into tag (name) values(?)";
    public static String FIND_TAG_BY_NAME = "select * from tag where name=?";
    public static String FIND_TAGS_BY_POST_ID =
            "with _tags as(\n" +
                    "    select tag_id\n" +
                    "    from post inner join post_tag on post.id = post_tag.post_id\n" +
                    "    where post_id = ?\n" +
                    ")\n" +
                    "select id,name\n" +
                    "from tag inner join _tags on tag.id = _tags.tag_id";

//Post query
    public static String ADD_POST = "insert into post (name, author_id,text) values(?,?,?)";

    public static String ADD_POST_TAG_DEPENDENCY = "insert into post_tag (post_id,tag_id) values(?,?)";
    public static String FIND_POST_BY_PATH = "select * from post where text=?";


    public static String FIND_POSTS_BY_AUTHOR_ID =
            "select *\n" +
            "from post \n" +
            "where author_id = ?";
    public static String FIND_POSTS_BY_TAG_ID =
            "with _tag as(\n" +
                    "    select post_id\n" +
                    "    from tag inner join post_tag pt on tag.id = pt.tag_id\n" +
                    "    where tag_id = ?\n" +
                    "    )\n" +
                    "select *\n" +
                    "from post inner join _tag  t on post.id = t.post_id";

    public static String FIND_POST_BY_ID = "select * from post where id=?";

    public static String FIND_All_POSTS =
            "select *\n" +
            "from post";


//Cat query
    public static String ADD_CAT = "insert into cat (user_id,cat_name,description) values(?,?,?)";
    public static String FIND_CATS_BY_USER_ID =
            "select *\n" +
            "from cat \n" +
            "where user_id = ?";

//Comment query
    public static String CREATE_COMMENT ="insert into comment (author_id, post_id,text) values(?,?,?)";

    public static String FIND_COMMENTS_BY_POST_ID =
            "select *\n" +
                    "from comment \n" +
                    "where post_id = ?";

//File query
    public static String ADD_FILE = "insert into file (name,uuid,\"mimeType\",size) values(?,?,?,?)";

    public static String FIND_FILE_BY_ID = "select * from file where id=?";



}
