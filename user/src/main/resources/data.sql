INSERT INTO TB_USER (nome, email, password) VALUES ('Wagner', 'wagner@gmail.com', '$2a$10$DruXuZiLsCxtyrqaq5Q.SuWhg4J6TVXbJD5BPA6D8ggNxLQH/X.gK' )
INSERT INTO TB_USER (nome, email, password) VALUES ('teste', 'teste@gmail.com', '$2a$10$DruXuZiLsCxtyrqaq5Q.SuWhg4J6TVXbJD5BPA6D8ggNxLQH/X.gK' )

INSERT INTO ROLE (perfil) VALUES ('ROLE_ADMIN')
INSERT INTO ROLE (perfil) VALUES ('ROLE_OPERATOR')

INSERT INTO TB_USER_ROLES (user_id, roles_id) VALUES (1, 1)
INSERT INTO TB_USER_ROLES (user_id, roles_id) VALUES (1, 2)
INSERT INTO TB_USER_ROLES (user_id, roles_id) VALUES (2, 2)