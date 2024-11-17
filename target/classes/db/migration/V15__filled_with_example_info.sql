alter table comments
drop column likes_count;

INSERT INTO roles (role) VALUES
('ROLE_USER'),
('ROLE_ADMIN');

INSERT INTO users (username, email, password_hash, profile_image_path, bio) VALUES
('USER', 'techenthusiast@example.com', '$2a$12$IFjZVBV/7ePE/2B8P7Zndu.KvAZPoVWXr99i/L/.RyNTCKR9UNb/m', 'http://localhost:8080/api/files/default_user_icon.jpg', 'Lover of all things tech'),
('ADMIN', 'globetrotter@example.com', '$2a$12$EGujKV92LqEX7v8QCIPThubHocUPLMVK1RbcUzLKMf1BYOtjmGKTC', 'http://localhost:8080/api/files/default_user_icon.jpg', 'Traveling the world one step at a time');

INSERT INTO users_roles (user_id, role_id) VALUES
(1, 1),
(2, 2);

INSERT INTO post (user_id, title, image_path ,content, content_type) VALUES
(2, 'The Future of AI', 'http://localhost:8080/api/files/AI.jpg', 'Artificial intelligence is reshaping the world with its innovative capabilities.', 'IMAGE'),
(2, '5G Technology: What to Expect', 'http://localhost:8080/api/files/5G.jpg', 'The new era of connectivity is here with the power of 5G.', 'IMAGE'),
(1, 'Beauty is in the little things', 'http://localhost:8080/api/files/sunset.jpg','Jonathan Swift — "Every man desires to live long, but no man wishes to be old."', 'IMAGE'),
(1, 'Hidden Gems of the World', 'http://localhost:8080/api/files/travel.jpg', 'You only live once!', 'IMAGE');

INSERT INTO comments (post_id, user_id, content) VALUES
(1, 1, 'This is a fascinating insight into AI! Thanks for sharing.'),
(2, 1, '5G is definitely going to change how we interact with technology.'),
(3, 2, 'That''s for sure!');

INSERT INTO post_likes (user_id, post_id) VALUES
(1, 3),
(2, 1);
