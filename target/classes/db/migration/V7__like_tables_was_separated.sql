DROP TABLE likes;

CREATE TABLE IF NOT EXISTS post_likes (
    user_id INT,
    post_id INT,
    CONSTRAINT pk_post_likes PRIMARY KEY (user_id, post_id),
    CONSTRAINT fk_post_likes_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_post_likes_post FOREIGN KEY (post_id) REFERENCES post(id)
);

CREATE TABLE IF NOT EXISTS comment_likes (
    user_id INT,
    comment_id INT,
    CONSTRAINT pk_comment_likes PRIMARY KEY (user_id, comment_id),
    CONSTRAINT fk_comment_likes_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_comment_likes_comment FOREIGN KEY (comment_id) REFERENCES comments(id)
);