ALTER TABLE users_roles DROP CONSTRAINT IF EXISTS pk_users_roles;

ALTER TABLE users_roles
    ADD CONSTRAINT pk_users_roles PRIMARY KEY (user_id, role_id);