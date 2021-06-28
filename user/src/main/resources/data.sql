INSERT INTO USER (nome, email, password) VALUES ('Wagner', 'wagner@gmail.com', '$2a$10$BuzMHAmeCxUG/MV2X3k8Uu33XOwtL5qRZPxoXO4YHVuC3y872Zry6' )
INSERT INTO USER (nome, email, password) VALUES ('teste', 'teste@gmail.com', '$2a$10$BuzMHAmeCxUG/MV2X3k8Uu33XOwtL5qRZPxoXO4YHVuC3y872Zry6' )

INSERT INTO ROLE (perfil) VALUES ('ROLE_ADMIN')
INSERT INTO ROLE (perfil) VALUES ('ROLE_OPERATOR')

INSERT INTO USER_ROLES (user_id, roles_id) VALUES (1, 1)
INSERT INTO USER_ROLES (user_id, roles_id) VALUES (1, 2)
INSERT INTO USER_ROLES (user_id, roles_id) VALUES (2, 2)