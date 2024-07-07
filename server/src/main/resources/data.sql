INSERT INTO "permission" (description) VALUES
                                           ('ADMIN'),
                                           ('MANAGER'),
                                           ('COMMON_USER');

INSERT INTO "users" (user_name, full_name, password, account_non_expired, account_non_locked,
                     credentials_non_expired, enabled)
VALUES
    ('admin', 'Administrador', '{pbkdf2}f6a2dd68cfe9145226e4ab6923934f9914c565049af3f8e8bf8919794558d14e801368074a5e57e1', TRUE, TRUE, TRUE, TRUE);
