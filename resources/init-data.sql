WITH admin AS (
    INSERT INTO users (id, login, name, email, password, bio, created_at, active)
        VALUES (gen_random_uuid(), 'admin', 'Admin', 'admin@local', 'admin', 'Admin', NOW(), TRUE)
        RETURNING id),
     tag AS (
         INSERT INTO tags (id, title, description, url, created_at)
             VALUES (gen_random_uuid(), 'clojure', 'Clojure programming language', 'clojure', NOW())
             RETURNING id),
     post AS (
         INSERT INTO posts (id, title, url, description, content, created_at, published, user_id)
             VALUES (gen_random_uuid(), 'First Post', 'first-post', 'This is the first post',
                     'This is the content of the first post',
                     NOW(), TRUE, (SELECT id from admin))
             RETURNING id)
INSERT INTO posts_tags (id, post_id, tag_id)
VALUES (gen_random_uuid(), (SELECT id FROM post), (SELECT id FROM tag));