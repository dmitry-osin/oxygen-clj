WITH admin AS (
    INSERT INTO oxygen.public.users (id, username, name, email, password, bio, created_at, active)
        VALUES (gen_random_uuid(), 'admin', 'Admin', 'admin@local', 'admin', 'Admin', NOW(), TRUE)
        RETURNING id),
     post AS (
         INSERT INTO oxygen.public.posts (id, title, url, description, content, created_at, published)
             VALUES (gen_random_uuid(), 'First Post', 'localhost:8080/posts/1', 'This is the first post',
                     'This is the content of the first post',
                     NOW(), TRUE)
             RETURNING id),
     tag AS (
         INSERT INTO oxygen.public.tags (id, title, url, created_at)
             VALUES (gen_random_uuid(), 'clojure', 'Clojure', NOW())
             RETURNING id),
     users_posts_insert AS (
         INSERT INTO oxygen.public.users_posts (id, user_id, post_id)
             VALUES (gen_random_uuid(),
                     (SELECT id from admin),
                     (SELECT id FROM post)))
INSERT INTO oxygen.public.posts_tags (id, post_id, tag_id)
VALUES (gen_random_uuid(), (SELECT id FROM post), (SELECT id FROM tag));
