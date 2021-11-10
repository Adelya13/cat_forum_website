create table user(
    id serial primary key not null,
    username varchar not null,
    email varchar unique not null,
    password varchar not null,
    avatar bigint,
    Foreign Key (avatar) references file(id) on delete cascade
);

create table cat(
    id serial primary key not null,
    user_id bigint not null,
    cat_name varchar not null,
    description varchar,
    cat_avatar bigint,
    Foreign Key (cat_avatar) references file(id) on delete cascade,
    Foreign Key (user_id) references user(id) on delete cascade
);

create table comment(
    id serial primary key not null,
    author_id bigint not null,
    post_id bigint not null,
    text varchar not null,
    Foreign Key (author_id) references user(id) on delete cascade,
    Foreign Key (post_id) references post(id) on delete cascade
);
create table file(
    id serial primary key not null,
    name varchar not null,
    uuid uuid not null,
    mimeType varchar not null,
    size bigint not null
);
create table post(
    id serial primary key not null,
    name varchar(255),
    author_id bigint not null,
    text varchar not null,
    Foreign Key (author_id) references user(id) on delete cascade

);
create table post_tag(
    id serial primary key not null,
    post_id bigint not null,
    tag_id bigint not null,
    Foreign Key (post_id) references post(id) on delete cascade,
    Foreign Key (tag_id) references tag(id) on delete cascade
);
create table tag(
    id serial primary key not null,
    name varchar(255)
);