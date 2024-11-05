ALTER TABLE users
DROP COLUMN role_id;

CREATE TABLE users_roles(
    user_id INT,
    role_id INT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles(id)
);