ALTER TABLE users
    ALTER COLUMN profile_image_path
        SET DEFAULT 'http://localhost:8080/api/files/default_user_icon.jpg';
