ALTER TABLE comments
DROP CONSTRAINT fk_post;

ALTER TABLE comments
ADD CONSTRAINT fk_post FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE CASCADE;

ALTER TABLE post_likes
DROP CONSTRAINT fk_post_likes_post;

ALTER TABLE post_likes
ADD CONSTRAINT fk_post_likes_post FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE CASCADE;
