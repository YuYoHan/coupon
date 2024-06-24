create table users (
    user_id number primary key,
    user_login_id varchar2(30) not null ,
    user_pw varchar2(20) not null ,
    user_email varchar2(30) not null ,
    nickName varchar2(30) not null
);

create table post(
    post_id number primary key ,
    post_title varchar2(30) not null ,
    post_contents varchar2(3000) ,
    post_regTime DATE default sysdate,
    user_id number references users(user_id) ON DELETE CASCADE
);

create table comments(
    comment_id number primary key ,
    comment_contents varchar2(1000) not null ,
    comment_regTime Date default sysdate,
    post_id number references post(post_id) ON DELETE CASCADE,
    user_id number references users(user_id) ON DELETE CASCADE
);

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE;

CREATE OR REPLACE TRIGGER users_trigger
BEFORE INSERT ON users
FOR EACH ROW
BEGIN
    SELECT user_id_seq.NEXTVAL
    INTO :new.user_id
    FROM dual;
END;
/

CREATE SEQUENCE post_id_seq
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE;

CREATE OR REPLACE TRIGGER post_trigger
BEFORE INSERT ON post
FOR EACH ROW
BEGIN
    SELECT post_id_seq.NEXTVAL
    INTO :new.post_id
    FROM dual;
END;
/

CREATE SEQUENCE comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE;

CREATE OR REPLACE TRIGGER comments_trigger
BEFORE INSERT ON comments
FOR EACH ROW
BEGIN
    SELECT comment_id_seq.NEXTVAL
    INTO :new.comment_id
    FROM dual;
END;
/