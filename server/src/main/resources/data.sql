CREATE TABLE users (
                       id BIGINT primary key not null,
                       user_name VARCHAR(255) DEFAULT NULL,
                       full_name VARCHAR(255) DEFAULT NULL,
                       password VARCHAR(255) DEFAULT NULL,
                       account_non_expired BIT DEFAULT NULL,
                       account_non_locked BIT DEFAULT NULL,
                       credentials_non_expired BIT DEFAULT NULL,
                       enabled BIT DEFAULT NULL,
                       primary key (id)
);

CREATE TABLE permission (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            description VARCHAR(255) DEFAULT NULL
);

CREATE TABLE user_permission (
                                 id_user BIGINT NOT NULL,
                                 id_permission BIGINT NOT NULL,
                                 PRIMARY KEY (id_user, id_permission),
                                 CONSTRAINT fk_user_permission FOREIGN KEY (id_user) REFERENCES users (id),
                                 CONSTRAINT fk_user_permission_permission FOREIGN KEY (id_permission) REFERENCES permission (id)
);

CREATE TABLE musica (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) DEFAULT NULL,
    artista VARCHAR(255) DEFAULT NULL,
    album VARCHAR(255) DEFAULT NULL,
    ano VARCHAR(255) DEFAULT NULL,
    genero VARCHAR(255) DEFAULT NULL,
    primary key (id)
);

CREATE TABLE lista_reproducao (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR(255) DEFAULT NULL,
                        descricao VARCHAR(255) DEFAULT NULL,
                        primary key (id)
);

CREATE TABLE  lista_musica (
                id_lista BIGINT NOT NULL,
                id_musica BIGINT NOT NULL,
                PRIMARY KEY (id_lista, id_musica),
                CONSTRAINT fk_lista FOREIGN KEY (id_lista) REFERENCES lista_reproducao (id),
                CONSTRAINT fk_musica FOREIGN KEY (id_musica) REFERENCES musica (id)
);


INSERT INTO permission (description) VALUES ('ADMIN'),('MANAGER'),('COMMON_USER');

INSERT INTO users (id,user_name, full_name, password, account_non_expired, account_non_locked,credentials_non_expired, enabled)
VALUES(1,'admin', 'Administrador', '{pbkdf2}f6a2dd68cfe9145226e4ab6923934f9914c565049af3f8e8bf8919794558d14e801368074a5e57e1',
       TRUE, TRUE, TRUE, TRUE);

INSERT INTO musica (titulo, artista, album, ano, genero) VALUES
                                                             ('Bohemian Rhapsody', 'Queen', 'A Night at the Opera', '1975', 'Rock'),
                                                             ('Imagine', 'John Lennon', 'Imagine', '1971', 'Rock'),
                                                             ('Smells Like Teen Spirit', 'Nirvana', 'Nevermind', '1991', 'Grunge'),
                                                             ('Billie Jean', 'Michael Jackson', 'Thriller', '1982', 'Pop'),
                                                             ('Hotel California', 'Eagles', 'Hotel California', '1976', 'Rock');

-- Inserir dados na tabela lista_reproducao
INSERT INTO lista_reproducao (nome, descricao) VALUES
                                                   ('Favoritas', 'Minha lista de músicas favoritas'),
                                                   ('Clássicos do Rock', 'Os melhores clássicos do rock'),
                                                   ('Pop Hits', 'Hits de música pop'),
                                                   ('Relax', 'Músicas para relaxar');

-- Inserir dados na tabela lista_musica
INSERT INTO lista_musica (id_lista, id_musica) VALUES
                                                   (1, 1),
                                                   (1, 2),
                                                   (2, 1),
                                                   (2, 3),
                                                   (2, 5),
                                                   (3, 4),
                                                   (4, 2),
                                                   (4, 5);

