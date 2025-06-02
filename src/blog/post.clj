(ns blog.post
  (:require [blog.db :as db])
  (:require [next.jdbc :as jdbc]))

(defn get-post [id]
  (jdbc/execute-one! db/ds ["SELECT * FROM posts WHERE id = ?" id]))

(defn get-posts-by-user [user]
  (jdbc/execute! db/ds ["SELECT * FROM oxygen.public.posts p
  JOIN public.users_posts up ON p.id = up.post_id
  JOIN public.users u ON u.id = up.user_id
  WHERE u.username = ?" user]))

(comment
  (get-post 1)
  (get-posts-by-user "admin")
  )
