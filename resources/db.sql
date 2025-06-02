CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users
(
    id         UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    login   VARCHAR(255)             NOT NULL,
    name       VARCHAR(255)             NOT NULL,
    email      VARCHAR(255)             NOT NULL,
    password   VARCHAR(255)             NOT NULL,
    bio        TEXT                              DEFAULT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE          DEFAULT NULL,
    deleted_at TIMESTAMP WITH TIME ZONE          DEFAULT NULL,
    active     BOOLEAN                  NOT NULL DEFAULT TRUE,
    unique (email, login)
);

CREATE TABLE IF NOT EXISTS posts
(
    id          UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    title       VARCHAR(512)             NOT NULL,
    url         VARCHAR(512)             NOT NULL,
    description VARCHAR(512)             NOT NULL,
    content     TEXT                     NOT NULL,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP WITH TIME ZONE          DEFAULT NULL,
    deleted_at  TIMESTAMP WITH TIME ZONE          DEFAULT NULL,
    published   BOOLEAN                  NOT NULL DEFAULT FALSE,
    user_id     UUID                     NOT NULL,
    unique (title, url),
    CONSTRAINT fk_user_id
        FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS tags
(
    id          UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    title       VARCHAR(64)              NOT NULL,
    description VARCHAR(128),
    url         VARCHAR(64)              NOT NULL,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP WITH TIME ZONE          DEFAULT NULL,
    deleted_at  TIMESTAMP WITH TIME ZONE          DEFAULT NULL,
    unique (title, url)
);

CREATE TABLE IF NOT EXISTS posts_tags
(
    id      UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    post_id UUID NOT NULL,
    tag_id  UUID NOT NULL,
    CONSTRAINT fk_post_id
        FOREIGN KEY (post_id) REFERENCES posts (id),
    CONSTRAINT fk_tag_id
        FOREIGN KEY (tag_id) REFERENCES tags (id)
);