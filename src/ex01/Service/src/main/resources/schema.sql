DROP TABLE IF EXISTS "user";
CREATE TABLE "user" (id SERIAL PRIMARY KEY, email VARCHAR(30));
INSERT INTO "user" (email) VALUES ('aaa@gmail.com'), ('bbb@gmail.com'), ('ccc@gmail.com');
