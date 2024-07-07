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


INSERT INTO permission (description) VALUES ('ADMIN'),('MANAGER'),('COMMON_USER');

INSERT INTO users (id,user_name, full_name, password, account_non_expired, account_non_locked,credentials_non_expired, enabled)VALUES(1,'admin', 'Administrador', '{pbkdf2}f6a2dd68cfe9145226e4ab6923934f9914c565049af3f8e8bf8919794558d14e801368074a5e57e1', TRUE, TRUE, TRUE, TRUE);
