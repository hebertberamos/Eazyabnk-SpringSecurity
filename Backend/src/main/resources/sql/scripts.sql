create table users(username varchar(50) not null primary key, password varchar(500) not null, enabled boolean not null);
create table authorities (username varchar(50) not null, authority varchar(50) not null, constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

INSERT INTO users VALUES ('user', '{noop}EazyBytes@12345', '1');
INSERT INTO users VALUES ('admin', '{bcrypt}$2a$12$lZpDHfNFf0uhcQwtATB5tupqaPjaypc97Ux.a4tLdR31NNGy1x15.', '1'); -- encrypted password: 54321

INSERT INTO authorities VALUES ('user', 'read');
INSERT INTO authorities VALUES ('admin', 'admin');

CREATE TABLE customer (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	email VARCHAR(50) NOT NULL,
    password VARCHAR(200) NOT NULL,
    role VARCHAR (45) NOT NULL
);

INSERT INTO customer (email, password, role) VALUES ('happy@example.com', '{noop}EazyBytes@12345', 'read');
INSERT INTO customer (email, password, role) VALUES ('admin@example.com', '{bcrypt}$2a$12$lZpDHfNFf0uhcQwtATB5tupqaPjaypc97Ux.a4tLdR31NNGy1x15.', 'admin');
