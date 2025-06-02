(ns blog.post
  (:require [blog.db :as db]
            [next.jdbc :as jdbc]))

(def posts-by-user-query "SELECT * FROM posts p
  JOIN public.users u ON p.user_id = u.id
  WHERE u.username = ? LIMIT ?")

(def find-posts-query "SELECT * FROM posts LIMIT ?")

(def find-post-by-id-query "SELECT * FROM posts WHERE id = ?")

(def find-user-query "SELECT id FROM users WHERE username = ?")

(def insert-post "INSERT INTO posts (title, url, description, content, published, user_id)
                 VALUES (?, ?, ?, ?, ?, ?) RETURNING id")

(def insert-tag "INSERT INTO tags (title, url) VALUES (?, ?) RETURNING id")

(def link-user-post "INSERT INTO users_posts (user_id, post_id) VALUES (?, ?)")

(def link-tag-post "INSERT INTO posts_tags (tag_id, post_id) VALUES (?, ?)")

(defn find-post
  ([id] (jdbc/execute-one! db/ds [find-post-by-id-query id]))
  ([tx id] (jdbc/execute-one! tx [find-post-by-id-query id]))
  )

(defn find-posts-by-user-query
  ([user limit] (jdbc/execute! db/ds [posts-by-user-query user limit]))
  ([tx user limit] (jdbc/execute! tx [posts-by-user-query user limit]))
  )

(defn find-posts
  ([limit] (jdbc/execute! db/ds [find-posts-query limit]))
  ([tx limit] (jdbc/execute! tx [find-posts-query limit]))
  )

(defn find-user
  ([username] (jdbc/execute-one! db/ds [find-user-query username]))
  ([tx username] (jdbc/execute-one! tx [find-user-query username]))
  )

(defn save-post [post tags user-name]
  (jdbc/with-transaction [tx db/ds]
                         (let [user (find-user tx user-name)
                               user-id (:users/id user)]
                           (when user-id
                             (let [result (jdbc/execute-one! tx [insert-post
                                                                 (:title post)
                                                                 (:url post)
                                                                 (:description post)
                                                                 (:content post)
                                                                 (or (:published post) false)])
                                   post-id (:posts/id result)]
                               (doseq [tag tags]
                                 (-> (jdbc/execute-one! tx [insert-tag {:title tag :url tag}]))
                                 )
                               (jdbc/execute-one! tx [link-user-post user-id post-id])
                               result)))))

(comment
  (find-post "0d4d455e-6a2e-45a3-8d45-5e6a2e45a342")
  (find-posts-by-user-query "admin" 5)
  (find-posts 10)
  (find-user "admin")
  (save-post {:title       "Test Post"
              :url         "test-post"
              :description "A test post"
              :content     "This is a test post content"
              :published   true}
             "admin")
  )